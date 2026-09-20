import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UDPServer {
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd MM yyyy");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH mm ss");
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern("dd MM yyyy HH mm ss");

    public static void main(String[] args) throws Exception {
        int port = 6000;
        byte[] buffer = new byte[1024];

        // UDP la connectionless -> khong co khai niem QUIT cho phien ket noi
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("UDP Server dang lang nghe tren port " + port);

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String cmd = new String(request.getData(), 0, request.getLength()).trim().toUpperCase();
                InetAddress clientAddr = request.getAddress();
                int clientPort = request.getPort();

                LocalDateTime now = LocalDateTime.now();
                String response;
                switch (cmd) {
                    case "DATE":
                        response = now.format(DATE_FMT);
                        break;
                    case "TIME":
                        response = now.format(TIME_FMT);
                        break;
                    case "DATETIME":
                        response = now.format(DATETIME_FMT);
                        break;
                    default:
                        response = "ERROR: Lenh khong hop le";
                }

                byte[] respData = response.getBytes();
                DatagramPacket reply = new DatagramPacket(respData, respData.length, clientAddr, clientPort);
                socket.send(reply);
            }
        }
    }
}
