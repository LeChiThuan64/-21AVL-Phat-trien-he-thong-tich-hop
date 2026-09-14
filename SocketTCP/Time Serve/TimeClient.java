import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1235);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String result = in.readLine();
        System.out.println("Nhan tu server: " + result);

        socket.close();
    }
}