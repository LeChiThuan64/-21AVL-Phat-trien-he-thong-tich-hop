package bai6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/** Bai 6 - server echo UDP dung de do thoi gian, doi chieu voi TCP. */
public class UdpEchoServer {
    private static final int PORT = 5005;

    public static void main(String[] args) {
        byte[] buffer = new byte[4096];
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("UDP echo server (bai6) tai cong " + PORT);
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                DatagramPacket response = new DatagramPacket(
                        request.getData(), request.getOffset(), request.getLength(),
                        request.getAddress(), request.getPort());
                socket.send(response);
            }
        } catch (IOException e) {
            System.err.println("Loi server: " + e.getMessage());
        }
    }
}
