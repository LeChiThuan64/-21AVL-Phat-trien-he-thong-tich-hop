package bai4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Bai 4 - May tinh tu xa.
 * Giao thuc: CALC <toan_tu> <so_1> <so_2>
 * Vi du:     CALC + 100 200  ->  OK 300
 */
public class CalcServer {
    private static final int PORT = 5002;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Calc server dang lang nghe tai cong " + PORT);
            while (true) {
                try (Socket socket = server.accept()) {
                    serve(socket);
                } catch (IOException e) {
                    System.err.println("Loi phien client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Khong mo duoc server: " + e.getMessage());
        }
    }

    static void serve(Socket socket) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(
                     socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
            String request;
            while ((request = in.readLine()) != null) {
                String response = process(request);
                out.println(response);
                if (request.trim().equalsIgnoreCase("QUIT")) break;
            }
        }
    }

    static String process(String request) {
        String trimmed = request.trim();
        if (trimmed.equalsIgnoreCase("QUIT")) return "OK BYE";
        if (!trimmed.regionMatches(true, 0, "CALC ", 0, 5)) {
            return "ERR INVALID_FORMAT";
        }
        String remainder = trimmed.substring(5).trim();
        if (remainder.isEmpty()) return "ERR INVALID_FORMAT";
        String[] parts = remainder.split("\\s+");
        if (parts.length != 3) return "ERR INVALID_FORMAT";

        String operator = parts[0];
        double a;
        double b;
        try {
            a = Double.parseDouble(parts[1]);
            b = Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            return "ERR INVALID_NUMBER";
        }

        switch (operator) {
            case "+":
                return "OK " + formatResult(a + b);
            case "-":
                return "OK " + formatResult(a - b);
            case "*":
                return "OK " + formatResult(a * b);
            case "/":
                if (b == 0) return "ERR DIVIDE_BY_ZERO";
                return "OK " + formatResult(a / b);
            default:
                return "ERR UNSUPPORTED_OPERATOR";
        }
    }

    private static String formatResult(double value) {
        if (!Double.isInfinite(value) && value == Math.floor(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}
