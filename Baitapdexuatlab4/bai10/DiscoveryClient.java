package bai10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class DiscoveryClient {
    private static final int DISCOVERY_PORT = 5009;
    private static final int TIMEOUT_MS = 2000;

    public static void main(String[] args) throws IOException {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);
            socket.setSoTimeout(TIMEOUT_MS);

            byte[] request = "DISCOVER_SERVICE".getBytes(StandardCharsets.UTF_8);
            // Tren localhost, broadcast van di qua duoc; tren LAN that can dung dia
            // chi broadcast cua mang con (vi du 192.168.1.255) neu 255.255.255.255 bi chan.
            InetAddress broadcast = InetAddress.getByName("255.255.255.255");
            socket.send(new DatagramPacket(request, request.length, broadcast, DISCOVERY_PORT));
            System.out.println("Da gui DISCOVER_SERVICE broadcast, dang cho phan hoi...");

            Set<String> seen = new HashSet<>();
            byte[] buffer = new byte[1024];
            long deadline = System.currentTimeMillis() + TIMEOUT_MS;

            while (System.currentTimeMillis() < deadline) {
                try {
                    DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                    socket.receive(response);
                    String text = new String(response.getData(), response.getOffset(),
                            response.getLength(), StandardCharsets.UTF_8).trim();
                    // Loai bo phan hoi trung dua tren dia chi nguon + noi dung
                    String key = response.getAddress().getHostAddress() + ":" + text;
                    if (seen.add(key)) {
                        System.out.println("Tim thay: " + text + " tu " + response.getAddress());
                        connectToService(response.getAddress(), text);
                    }
                } catch (SocketTimeoutException e) {
                    break;
                }
            }
            if (seen.isEmpty()) {
                System.out.println("Khong tim thay server nao trong " + TIMEOUT_MS + " ms");
            }
        }
    }

    private static void connectToService(InetAddress address, String serviceInfo) {
        // serviceInfo dang: SERVICE ten_dich_vu tcp_port phien_ban
        String[] parts = serviceInfo.split("\\s+");
        if (parts.length < 3) return;
        try {
            int tcpPort = Integer.parseInt(parts[2]);
            try (Socket socket = new Socket(address, tcpPort);
                 BufferedReader in = new BufferedReader(new InputStreamReader(
                         socket.getInputStream(), StandardCharsets.UTF_8))) {
                System.out.println("TCP service tra loi: " + in.readLine());
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Khong ket noi duoc TCP service: " + e.getMessage());
        }
    }
}
