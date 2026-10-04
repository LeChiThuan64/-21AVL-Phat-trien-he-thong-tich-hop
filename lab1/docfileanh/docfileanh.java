package docfileanh;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class docfileanh {

    public static byte[] readFile(File path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            byte[] buf = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            fis.close();
            return bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(docfileanh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        byte[] data = readFile(new File("anh.jpg"));
        if (data != null) {
            System.out.println("Đọc được " + data.length + " byte");
        } else {
            System.out.println("Không đọc được file ảnh");
        }
    }
}
// Cách chạy
// Chuẩn bị ảnh: chép một ảnh vào thư mục gốc lab1 (cùng cấp với thư mục 
//     docfileanh, bai1.docx, Main.java...) và đặt tên anh.jpg. Không bỏ vào 
//     trong thư mục docfileanh.
// Chạy: ở dòng 24 có chữ nhỏ Run | Debug ngay trên hàm main, bấm Run.
// Xem kết quả: Terminal phía dưới in Đã ghi ra anh_copy.jpg, và trong 
// thư mục lab1 xuất hiện thêm file anh_copy.jpg.