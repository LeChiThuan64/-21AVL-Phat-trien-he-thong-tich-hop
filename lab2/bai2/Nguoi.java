import java.time.LocalDate;

// Lớp cha: chứa thông tin chung của một người (sinh viên hoặc giảng viên)
public class Nguoi {
    private String hoTen;
    private int namSinh;
    private String diaChi;

    // Constructor đầy đủ tham số
    public Nguoi(String hoTen, int namSinh, String diaChi) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
    }

    // Getter và Setter
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    // Tính tuổi = năm hiện tại - năm sinh
    public int tinhTuoi() {
        int namHienTai = LocalDate.now().getYear();
        return namHienTai - namSinh;
    }

    // Hiển thị thông tin chung
    public void hienThiThongTin() {
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Nam sinh: " + namSinh + " (Tuoi: " + tinhTuoi() + ")");
        System.out.println("Dia chi: " + diaChi);
    }
}