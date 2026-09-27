package bai6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

/** Gui 1000 datagram kich thuoc co dinh qua UDP, lap 5 vong, do tong thoi gian va so phan hoi. */
public class UdpBenchmarkClient {
    private static final int MESSAGE_COUNT = 1000;
    private static final int ROUNDS = 5;
    private static final int TIMEOUT_MS = 200;

    public static void main(String[] args) throws IOException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5005;
        InetAddress server = InetAddress.getByName(host);
        byte[] payload = "0123456789012345678901234567890123456789"
                .getBytes(StandardCharsets.UTF_8);

        for (int round = 1; round <= ROUNDS; round++) {
            try (DatagramSocket socket = new DatagramSocket()) {
                socket.setSoTimeout(TIMEOUT_MS);
                int received = 0;
                byte[] buffer = new byte[4096];
                long start = System.nanoTime();
                for (int i = 0; i < MESSAGE_COUNT; i++) {
                    socket.send(new DatagramPacket(payload, payload.length, server, port));
                    try {
                        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                        socket.receive(response);
                        received++;
                    } catch (SocketTimeoutException e) {
                        // Coi day la mot goi tin bi mat hoac phan hoi cham, bo qua va tiep tuc
                    }
                }
                long elapsedMs = (System.nanoTime() - start) / 1_000_000;
                System.out.printf("UDP vong %d: %d/%d phan hoi, %d ms%n",
                        round, received, MESSAGE_COUNT, elapsedMs);
            }
        }
    }
}
