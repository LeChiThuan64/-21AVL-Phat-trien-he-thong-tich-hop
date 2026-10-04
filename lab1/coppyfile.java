import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class coppyfile {

    public boolean copyFile(String source, String dest) throws FileNotFoundException, IOException {
        // file nguồn
        File sourceFile = new File(source);
        // file đích
        File destFile = new File(dest);
        // kiểm tra file nguồn có tồn tại không
        if (sourceFile.exists()) {
            // luồng đọc file
            FileInputStream fis = new FileInputStream(sourceFile);
            // luồng ghi file
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] arr = new byte[1024];
            int len;
            while ((len = fis.read(arr)) != -1) {
                fos.write(arr, 0, len);
                fos.flush();
            }
            fis.close();
            fos.close();
            System.out.println("copy thành công");
            return true;
        } else {
            System.out.println("file nguồn không tồn tại");
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        coppyfile c = new coppyfile();
        c.copyFile("a.txt", "b.txt");
    }
}