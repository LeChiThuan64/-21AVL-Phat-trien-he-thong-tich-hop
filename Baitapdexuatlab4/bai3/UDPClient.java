import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 6000;
        InetAddress serverAddr = InetAddress.getByName(host);

        try (DatagramSocket socket = new DatagramSocket();
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            // Timeout de phat hien khi server khong con phan hoi (UDP khong tu bao mat ket noi)
            socket.setSoTimeout(3000);
            System.out.println("UDP Client. Nhap lenh: DATE / TIME / DATETIME / QUIT (de thoat client)");

            String userInput;
            while ((userInput = console.readLine()) != null) {
                if (userInput.trim().equalsIgnoreCase("QUIT")) {
                    System.out.println("Thoat client (khong gui gi den server).");
                    break;
                }

                byte[] sendData = userInput.getBytes();
                DatagramPacket request = new DatagramPacket(sendData, sendData.length, serverAddr, port);
                socket.send(request);

                byte[] buffer = new byte[1024];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                try {
                    socket.receive(reply);
                    String response = new String(reply.getData(), 0, reply.getLength());
                    System.out.println("Server: " + response);
                } catch (SocketTimeoutException e) {
                    System.out.println(">> Khong nhan duoc phan hoi (timeout). Server co the da dung.");
                }
            }
        }
    }
}
