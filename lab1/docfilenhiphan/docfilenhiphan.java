package docfilenhiphan;
import java.io.*;
import java.util.ArrayList;

public class docfilenhiphan {

    public static void loadSV(String src) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(new File(src)));
        int size = dis.readInt();
        ArrayList<SinhVien> listSV = new ArrayList<SinhVien>();
        for (int i = 0; i < size; i++) {
            String mssv = dis.readUTF();
            String name = dis.readUTF();
            int age = dis.readInt();
            int sizemh = dis.readInt();
            ArrayList<MonHoc> listMH = new ArrayList<MonHoc>();
            for (int j = 0; j < sizemh; j++) {
                String tenMonHoc = dis.readUTF();
                int tinChi = dis.readInt();
                double diem = dis.readDouble();
                MonHoc mh1 = new MonHoc(tenMonHoc, tinChi, diem);
                listMH.add(mh1);
            }
            listSV.add(new SinhVien(mssv, name, age, listMH));
        }
        for (SinhVien sv : listSV) {
            System.out.println(sv.toString());
        }
        dis.close();
    }

    public static void main(String[] args) throws IOException {
        loadSV("sinhvien.dat");
    }
}
// Mở ghifilenhiphan.java và bấm Run trước. Việc này tạo ra file sinhvien.dat.
// Mở docfilenhiphan.java và bấm Run. Terminal sẽ in danh sách sinh viên và môn học đọc từ file.