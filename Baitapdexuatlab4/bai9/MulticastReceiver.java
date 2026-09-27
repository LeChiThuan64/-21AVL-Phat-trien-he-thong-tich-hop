package bai9;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;

/**
 * Bai 9 - Receiver tham gia nhom multicast, ro roi nhom truoc khi dong socket
 * (thuc hien qua shutdown hook vi vong lap receive() la vo han).
 * Chay it nhat hai tien trinh receiver de kiem thu nhieu thanh vien trong nhom.
 */
public class MulticastReceiver {
    private static final String GROUP_ADDRESS = "239.255.0.1";
    private static final int PORT = 5008;

    public static void main(String[] args) throws IOException {
        InetAddress group = InetAddress.getByName(GROUP_ADDRESS);
        NetworkInterface netIf = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        InetSocketAddress groupAddress = new InetSocketAddress(group, PORT);

        MulticastSocket socket = new MulticastSocket(PORT);
        socket.joinGroup(groupAddress, netIf);
        System.out.println("Da tham gia nhom " + GROUP_ADDRESS + ":" + PORT
                + " qua " + netIf.getDisplayName());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                socket.leaveGroup(groupAddress, netIf);
                System.out.println("Da roi nhom multicast");
            } catch (IOException ignored) {
                // Bo qua loi khi dang thoat chuong trinh
            } finally {
                socket.close();
            }
        }));

        byte[] buffer = new byte[4096];
        while (!socket.isClosed()) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String text = new String(packet.getData(), packet.getOffset(),
                        packet.getLength(), StandardCharsets.UTF_8);
                System.out.println("Nhan tu " + packet.getAddress() + ": " + text);
            } catch (IOException e) {
                if (!socket.isClosed()) {
                    System.err.println("Loi nhan: " + e.getMessage());
                }
            }
        }
    }
}
