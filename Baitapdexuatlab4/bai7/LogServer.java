package bai7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * Bai 7 - Server luu nhat ky tin nhan.
 * Client gui HELLO <clientId> truoc, sau do moi dong duoc ghi vao
 * data/logs/<clientId>.txt kem timestamp va dia chi remote.
 */
public class LogServer {
    private static final int PORT = 5006;
    private static final Pattern VALID_ID = Pattern.compile("[A-Za-z0-9_-]+");
    private static final Path LOG_DIR = Paths.get("data", "logs");

    public static void main(String[] args) throws IOException {
        Files.createDirectories(LOG_DIR);
        ExecutorService pool = Executors.newCachedThreadPool();
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Log server tai cong " + PORT);
            while (true) {
                Socket socket = server.accept();
                pool.submit(() -> handle(socket));
            }
        }
    }

    private static void handle(Socket socket) {
        String remote = String.valueOf(socket.getRemoteSocketAddress());
        try (socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(
                     socket.getOutputStream(), StandardCharsets.UTF_8), true)) {

            String hello = in.readLine();
            if (hello == null || !hello.regionMatches(true, 0, "HELLO ", 0, 6)) {
                out.println("ERR EXPECTED_HELLO");
                return;
            }
            String clientId = hello.substring(6).trim();
            if (!VALID_ID.matcher(clientId).matches()) {
                out.println("ERR INVALID_CLIENT_ID");
                return;
            }
            out.println("OK HELLO " + clientId);
            Path logFile = LOG_DIR.resolve(clientId + ".txt");

            String line;
            while ((line = in.readLine()) != null) {
                if (line.trim().equalsIgnoreCase("QUIT")) {
                    out.println("OK BYE");
                    break;
                }
                String entry = LocalDateTime.now() + " | " + remote + " | " + line;
                Files.writeString(logFile, entry + System.lineSeparator(),
                        StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
                out.println("OK LOGGED");
            }
        } catch (IOException e) {
            System.err.println("Loi phien " + remote + ": " + e.getMessage());
        }
    }
}
