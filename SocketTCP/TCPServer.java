import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234); // gán port
        System.out.println("Server dang cho ket noi tren port 1234...");

        Socket clientSocket = serverSocket.accept(); // cho nhan ket noi
        System.out.println("Client da ket noi: " + clientSocket.getInetAddress());

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String line;
        while ((line = in.readLine()) != null) {
            System.out.println("Nhan tu client: " + line);
            out.println("Server da nhan: " + line); // gui nguoc lai (echo)
            if (line.equalsIgnoreCase("exit")) break;
        }

        clientSocket.close();
        serverSocket.close();
    }
}