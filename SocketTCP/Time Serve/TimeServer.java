import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1235); // dung port khac voi vi du Echo
        System.out.println("Time Server dang cho ket noi tren port 1235...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client da ket noi: " + clientSocket.getInetAddress());

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("Gio hien tai cua server: " + new Date());

        clientSocket.close();
        serverSocket.close();
    }
}