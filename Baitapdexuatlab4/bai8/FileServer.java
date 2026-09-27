package bai8;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Bai 8 - Truyen file TCP an toan.
 * Giao thuc (dung DataInputStream/DataOutputStream):
 *   1. writeUTF(tenFile)
 *   2. writeLong(kichThuoc)
 *   3. writeUTF(sha256Hex)
 *   4. write(du_lieu_thuc)
 * Server tra ve writeUTF("OK <tenFile>") hoac writeUTF("ERR HASH_MISMATCH").
 */
public class FileServer {
    private static final int PORT = 5007;
    private static final Path UPLOAD_DIR = Paths.get("upload");

    public static void main(String[] args) throws IOException {
        Files.createDirectories(UPLOAD_DIR);
        ExecutorService pool = Executors.newCachedThreadPool();
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("File server tai cong " + PORT);
            while (true) {
                Socket socket = server.accept();
                pool.submit(() -> handle(socket));
            }
        }
    }

    private static void handle(Socket socket) {
        try (socket;
             DataInputStream in = new DataInputStream(
                     new BufferedInputStream(socket.getInputStream()));
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            String rawName = in.readUTF();
            // Loai bo moi thanh phan thu muc do client gui, chi giu ten file
            String safeName = Paths.get(rawName).getFileName().toString();
            long size = in.readLong();
            String expectedHash = in.readUTF();

            if (size < 0) {
                out.writeUTF("ERR INVALID_SIZE");
                return;
            }

            byte[] data = new byte[(int) size];
            in.readFully(data);

            String actualHash = sha256Hex(data);
            Path target = UPLOAD_DIR.resolve(safeName);
            Files.write(target, data);

            if (actualHash.equalsIgnoreCase(expectedHash)) {
                out.writeUTF("OK " + safeName);
            } else {
                out.writeUTF("ERR HASH_MISMATCH");
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Loi phien: " + e.getMessage());
        }
    }

    private static String sha256Hex(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
