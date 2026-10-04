package docfileanh;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ghifileanh {

    // path: file đích, tfile: định dạng ("jpg", "png"), bfile: dữ liệu ảnh
    public static void saveFile(File path, String tfile, byte[] bfile) {
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bfile));
            ImageIO.write(img, tfile, path);
        } catch (IOException ex) {
            Logger.getLogger(ghifileanh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        // Lấy dữ liệu ảnh gốc để có mảng byte mà ghi
        byte[] data = Files.readAllBytes(new File("anh.jpg").toPath());
        saveFile(new File("anh_copy.jpg"), "jpg", data);
        System.out.println("Đã ghi ra anh_copy.jpg");
    }
}