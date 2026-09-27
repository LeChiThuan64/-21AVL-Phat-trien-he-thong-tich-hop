package bai9;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Bai 9 - Multicast thong bao.
 * Dia chi 239.255.0.1 thuoc pham vi quan tri cuc bo (site-local, 239.0.0.0/8).
 */
public class MulticastSender {
    private static final String GROUP_ADDRESS = "239.255.0.1";
    private static final int PORT = 5008;

    public static void main(String[] args) throws IOException {
        String message = args.length > 0 ? args[0] : "Thong bao tu sender";
        InetAddress group = InetAddress.getByName(GROUP_ADDRESS);
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] data = message.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(data, data.length, group, PORT);
            socket.send(packet);
            System.out.println("Da gui toi " + GROUP_ADDRESS + ":" + PORT + " -> " + message);
        }
    }
}
