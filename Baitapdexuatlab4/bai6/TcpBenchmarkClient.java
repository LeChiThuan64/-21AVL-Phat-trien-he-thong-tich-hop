package bai6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/** Gui 1000 thong diep kich thuoc co dinh qua TCP, lap 5 vong, do tong thoi gian. */
public class TcpBenchmarkClient {
    private static final int MESSAGE_COUNT = 1000;
    private static final int ROUNDS = 5;
    private static final String PAYLOAD = "0123456789012345678901234567890123456789"; // 40 byte

    public static void main(String[] args) throws IOException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5004;

        for (int round = 1; round <= ROUNDS; round++) {
            try (Socket socket = new Socket(host, port);
                 BufferedReader in = new BufferedReader(new InputStreamReader(
                         socket.getInputStream(), StandardCharsets.UTF_8));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(
                         socket.getOutputStream(), StandardCharsets.UTF_8), true)) {

                int received = 0;
                long start = System.nanoTime();
                for (int i = 0; i < MESSAGE_COUNT; i++) {
                    out.println(PAYLOAD);
                    String response = in.readLine();
                    if (response != null) received++;
                }
                long elapsedMs = (System.nanoTime() - start) / 1_000_000;
                System.out.printf("TCP vong %d: %d/%d phan hoi, %d ms%n",
                        round, received, MESSAGE_COUNT, elapsedMs);
            }
        }
    }
}
