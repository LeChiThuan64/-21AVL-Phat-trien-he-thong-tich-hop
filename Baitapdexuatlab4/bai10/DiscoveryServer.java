package bai10;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Bai 10 - UDP discovery ket hop TCP service.
 * Client broadcast DISCOVER_SERVICE qua UDP; server tra ve
 * "SERVICE <ten_dich_vu> <tcp_port> <phien_ban>" cho dia chi nguon cua goi tin.
 */
public class DiscoveryServer {
    private static final int DISCOVERY_PORT = 5009;
    private static final int TCP_SERVICE_PORT = 5010;
    private static final String SERVICE_NAME = "LabService";
    private static final String VERSION = "1.0";

    public static void main(String[] args) throws IOException {
        Thread tcpThread = new Thread(DiscoveryServer::runTcpService);
        tcpThread.setDaemon(true);
        tcpThread.start();

        try (DatagramSocket socket = new DatagramSocket(DISCOVERY_PORT)) {
            socket.setBroadcast(true);
            System.out.println("Discovery server lang nghe UDP tai cong " + DISCOVERY_PORT);
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                String message = new String(request.getData(), request.getOffset(),
                        request.getLength(), StandardCharsets.UTF_8).trim();
                if (message.equalsIgnoreCase("DISCOVER_SERVICE")) {
                    String reply = "SERVICE " + SERVICE_NAME + " " + TCP_SERVICE_PORT + " " + VERSION;
                    byte[] data = reply.getBytes(StandardCharsets.UTF_8);
                    socket.send(new DatagramPacket(data, data.length,
                            request.getAddress(), request.getPort()));
                    System.out.println("Tra loi discovery cho " + request.getAddress());
                }
            }
        }
    }

    private static void runTcpService() {
        try (ServerSocket server = new ServerSocket(TCP_SERVICE_PORT)) {
            System.out.println("TCP service lang nghe tai cong " + TCP_SERVICE_PORT);
            while (true) {
                try (Socket socket = server.accept();
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(
                             socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
                    out.println("OK " + SERVICE_NAME + " " + VERSION);
                }
            }
        } catch (IOException e) {
            System.err.println("Loi TCP service: " + e.getMessage());
        }
    }
}
