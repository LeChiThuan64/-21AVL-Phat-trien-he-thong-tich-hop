package bai8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class FileClient {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Cach dung: java bai8.FileClient <duong_dan_file> [host] [port]");
            return;
        }
        Path filePath = Paths.get(args[0]);
        String host = args.length > 1 ? args[1] : "localhost";
        int port = args.length > 2 ? Integer.parseInt(args[2]) : 5007;

        byte[] data = Files.readAllBytes(filePath);
        String hash = sha256Hex(data);
        String fileName = filePath.getFileName().toString();

        try (Socket socket = new Socket(host, port);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            out.writeUTF(fileName);
            out.writeLong(data.length);
            out.writeUTF(hash);
            out.write(data);
            out.flush();

            String response = in.readUTF();
            System.out.println("Server: " + response);
        }
    }

    private static String sha256Hex(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
