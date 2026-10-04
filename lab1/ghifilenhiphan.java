import java.io.*;
import java.util.ArrayList;

class MonHoc {
    private String tenMonHoc;
    private int tinChi;
    private double diem;

    public MonHoc(String tenMonHoc, int tinChi, double diem) {
        this.tenMonHoc = tenMonHoc;
        this.tinChi = tinChi;
        this.diem = diem;
    }

    public String getTenMonHoc() { return tenMonHoc; }
    public int getTinChi() { return tinChi; }
    public double getDiem() { return diem; }
}

class SinhVien {
    private String mssv;
    private String ten;
    private int tuoi;
    private ArrayList<MonHoc> listMH;

    public SinhVien(String mssv, String ten, int tuoi, ArrayList<MonHoc> listMH) {
        this.mssv = mssv;
        this.ten = ten;
        this.tuoi = tuoi;
        this.listMH = listMH;
    }

    public String getMssv() { return mssv; }
    public String getTen() { return ten; }
    public int getTuoi() { return tuoi; }
    public ArrayList<MonHoc> getListMH() { return listMH; }
}

public class ghifilenhiphan {

    // GHI file nhị phân
    public static void saveSV(String src, ArrayList<SinhVien> listSV) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(src)));
        dos.writeInt(listSV.size());
        for (SinhVien sv : listSV) {
            dos.writeUTF(sv.getMssv());
            dos.writeUTF(sv.getTen());
            dos.writeInt(sv.getTuoi());
            dos.writeInt(sv.getListMH().size());
            for (MonHoc mh : sv.getListMH()) {
                dos.writeUTF(mh.getTenMonHoc());
                dos.writeInt(mh.getTinChi());
                dos.writeDouble(mh.getDiem());
            }
        }
        dos.flush();
        dos.close();
    }

    // ĐỌC lại file nhị phân để kiểm tra (đọc đúng thứ tự đã ghi)
    public static void readSV(String src) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(new File(src)));
        int soSV = dis.readInt();
        for (int i = 0; i < soSV; i++) {
            String mssv = dis.readUTF();
            String ten = dis.readUTF();
            int tuoi = dis.readInt();
            System.out.println("SV: " + mssv + " - " + ten + " - " + tuoi + " tuổi");
            int soMH = dis.readInt();
            for (int j = 0; j < soMH; j++) {
                String tenMH = dis.readUTF();
                int tinChi = dis.readInt();
                double diem = dis.readDouble();
                System.out.println("   Môn: " + tenMH + " - " + tinChi + " tín chỉ - " + diem + " điểm");
            }
        }
        dis.close();
    }

    public static void main(String[] args) throws IOException {
        MonHoc mh = new MonHoc("ltcb", 3, 6.7);
        MonHoc mh1 = new MonHoc("ltw", 3, 6.7);
        MonHoc mh2 = new MonHoc("tkhdt", 3, 6.7);

        ArrayList<MonHoc> listMH = new ArrayList<>();
        listMH.add(mh2);
        listMH.add(mh1);
        listMH.add(mh);

        ArrayList<SinhVien> listSV = new ArrayList<>();
        SinhVien sv = new SinhVien("11329078", "nguyen van A", 23, listMH);
        SinhVien sv1 = new SinhVien("11329079", "nguyen Van B", 23, listMH);
        listSV.add(sv);
        listSV.add(sv1);

        saveSV("sinhvien.dat", listSV);
        System.out.println("Đã ghi file xong, đọc lại:");
        readSV("sinhvien.dat");
    }
}
// Mở ghifilenhiphan.java, bấm Run phía trên hàm main. File sinhvien.dat sẽ xuất hiện 
// trong thư mục project, và Terminal in ra danh sách đọc lại được.