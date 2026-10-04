
import java.io.File;

public class timkiemfile {

    public void findFile(String source, String key) {
        File file = new File(source);
        if (file.exists()) {
            if (file.isFile()) {
                if (file.getName().endsWith(key)) {
                    System.out.println(file.getAbsolutePath());
                }
            }
            File[] listFile = file.listFiles();
            if (listFile != null) {
                for (File f : listFile) {
                    findFile(f.getAbsolutePath(), key);
                }
            }
        } else {
            System.out.println("source không tồn tại");
        }
    }

    public static void main(String[] args) {
        timkiemfile m = new timkiemfile();
        // Đổi đường dẫn và đuôi file cho phù hợp với máy của bạn
        m.findFile("D:/Documents", ".txt");
    }
}