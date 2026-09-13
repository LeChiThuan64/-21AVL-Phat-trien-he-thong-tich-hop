// Lớp SinhVien kế thừa từ lớp Nguoi
public class SinhVien extends Nguoi {
    private String maSinhVien;
    private String nganhHoc;
    private double diemTrungBinh;

    // Constructor: gọi constructor lớp cha bằng super()
    public SinhVien(String hoTen, int namSinh, String diaChi,
                     String maSinhVien, String nganhHoc, double diemTrungBinh) {
        super(hoTen, namSinh, diaChi); // gọi constructor của Nguoi
        this.maSinhVien = maSinhVien;
        this.nganhHoc = nganhHoc;
        this.diemTrungBinh = diemTrungBinh;
    }

    // Getter và Setter riêng
    public String getMaSinhVien() {
        return maSinhVien;
    }

    public String getNganhHoc() {
        return nganhHoc;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    // Xếp loại dựa trên điểm trung bình
    public String xepLoai() {
        if (diemTrungBinh >= 8.5) {
            return "Gioi";
        } else if (diemTrungBinh >= 7.0) {
            return "Kha";
        } else if (diemTrungBinh >= 5.0) {
            return "Trung binh";
        } else {
            return "Yeu";
        }
    }

    // Ghi đè phương thức hienThiThongTin() của lớp cha
    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin(); // hiển thị thông tin chung trước
        System.out.println("Ma sinh vien: " + maSinhVien);
        System.out.println("Nganh hoc: " + nganhHoc);
        System.out.println("Diem trung binh: " + diemTrungBinh);
        System.out.println("Xep loai: " + xepLoai());
    }
}