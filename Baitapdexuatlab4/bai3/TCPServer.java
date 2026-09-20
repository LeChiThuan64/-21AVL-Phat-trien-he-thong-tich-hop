import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TCPServer {
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd MM yyyy");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH mm ss");
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern("dd MM yyyy HH mm ss");

    public static void main(String[] args) throws IOException {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TCP Server dang lang nghe tren port " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client ket noi: " + clientSocket.getRemoteSocketAddress());
                    handleClient(clientSocket);
                    System.out.println("Client ngat ket noi.");
                } catch (IOException e) {
                    System.out.println("Loi xu ly client: " + e.getMessage());
                }
            }
        }
    }

    private static void handleClient(Socket socket) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String line;
            while ((line = in.readLine()) != null) {
                String cmd = line.trim().toUpperCase();
                LocalDateTime now = LocalDateTime.now();

                switch (cmd) {
                    case "DATE":
                        out.println(now.format(DATE_FMT));
                        break;
                    case "TIME":
                        out.println(now.format(TIME_FMT));
                        break;
                    case "DATETIME":
                        out.println(now.format(DATETIME_FMT));
                        break;
                    case "QUIT":
                        out.println("BYE");
                        return; // dong ket noi
                    default:
                        out.println("ERROR: Lenh khong hop le");
                }
            }
        }
    }
}
