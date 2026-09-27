package bai5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Bai 5 - Chat TCP nhieu client.
 * Lenh: NICKNAME dau tien, sau do USERS, MSG <noi_dung>, QUIT.
 */
public class ChatServer {
    private static final int PORT = 5003;
    private static final int MAX_CLIENTS = 50;

    // Cau truc thread-safe de quan ly danh sach nguoi gui: nickname -> writer
    private static final Map<String, PrintWriter> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENTS);
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Chat server dang lang nghe tai cong " + PORT);
            while (true) {
                Socket socket = server.accept();
                pool.submit(() -> handleClient(socket));
            }
        } catch (IOException e) {
            System.err.println("Loi server: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }

    private static void handleClient(Socket socket) {
        String nickname = null;
        try (socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(
                     socket.getOutputStream(), StandardCharsets.UTF_8), true)) {

            out.println("NHAP NICKNAME");
            String candidate = in.readLine();
            while (candidate == null || candidate.trim().isEmpty()
                    || clients.containsKey(candidate.trim())) {
                if (candidate == null) return;
                out.println("ERR NICKNAME_TAKEN_OR_INVALID");
                candidate = in.readLine();
            }
            nickname = candidate.trim();
            clients.put(nickname, out);
            out.println("OK WELCOME " + nickname);
            broadcast("* " + nickname + " da tham gia", null);

            String line;
            while ((line = in.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.equalsIgnoreCase("QUIT")) {
                    out.println("OK BYE");
                    break;
                } else if (trimmed.equalsIgnoreCase("USERS")) {
                    out.println("OK " + String.join(",", clients.keySet()));
                } else if (trimmed.regionMatches(true, 0, "MSG ", 0, 4)) {
                    String content = trimmed.substring(4);
                    broadcast(nickname + ": " + content, nickname);
                    out.println("OK SENT");
                } else {
                    out.println("ERR UNKNOWN_COMMAND");
                }
            }
        } catch (IOException e) {
            System.err.println("Loi client " + nickname + ": " + e.getMessage());
        } finally {
            // Khi mot client ngat bat thuong, server loai client do va tiep tuc hoat dong
            if (nickname != null) {
                clients.remove(nickname);
                broadcast("* " + nickname + " da roi phong", null);
                System.out.println(nickname + " da ngat ket noi");
            }
        }
    }

    private static void broadcast(String message, String excludeNickname) {
        for (Map.Entry<String, PrintWriter> entry : clients.entrySet()) {
            if (excludeNickname != null && entry.getKey().equals(excludeNickname)) continue;
            entry.getValue().println("MSG " + message);
        }
    }
}
