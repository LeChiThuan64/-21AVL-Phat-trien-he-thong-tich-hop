package docfilenhiphan;

import java.util.ArrayList;

public class SinhVien {
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

    // Đề gọi sv.toString() nên cần có hàm này
    @Override
    public String toString() {
        String kq = "SV: " + mssv + " - " + ten + " - " + tuoi + " tuổi";
        for (MonHoc mh : listMH) {
            kq += "\n    Môn: " + mh.toString();
        }
        return kq;
    }
}