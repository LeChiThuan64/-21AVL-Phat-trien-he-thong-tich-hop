import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234); // ket noi den server

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String msg;
        while (true) {
            System.out.print("Nhap tin nhan (exit de thoat): ");
            msg = stdIn.readLine();
            out.println(msg);
            System.out.println(in.readLine());
            if (msg.equalsIgnoreCase("exit")) break;
        }

        socket.close();
    }
}