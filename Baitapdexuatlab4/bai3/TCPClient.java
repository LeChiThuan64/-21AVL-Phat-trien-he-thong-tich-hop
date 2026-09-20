import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Da ket noi toi server " + host + ":" + port);
            System.out.println("Nhap lenh: DATE / TIME / DATETIME / QUIT");

            String userInput;
            while ((userInput = console.readLine()) != null) {
                out.println(userInput);

                if (userInput.trim().equalsIgnoreCase("QUIT")) {
                    String resp = in.readLine();
                    System.out.println("Server: " + resp);
                    break;
                }

                String response = in.readLine();
                if (response == null) {
                    // Server dong socket dot ngot -> readLine tra ve null (EOF)
                    System.out.println(">> Mat ket noi: server da dung hoat dong.");
                    break;
                }
                System.out.println("Server: " + response);
            }
        } catch (ConnectException e) {
            System.out.println("Khong the ket noi den server: " + e.getMessage());
        }
    }
}
