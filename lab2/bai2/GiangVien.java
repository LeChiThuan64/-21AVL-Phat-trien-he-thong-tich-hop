// Lớp GiangVien kế thừa từ lớp Nguoi
public class GiangVien extends Nguoi {
    private String maGiangVien;
    private String chuyenMon;
    private double luongCoBan;
    private double heSoLuong;

    // Constructor: gọi constructor lớp cha bằng super()
    public GiangVien(String hoTen, int namSinh, String diaChi,
                      String maGiangVien, String chuyenMon,
                      double luongCoBan, double heSoLuong) {
        super(hoTen, namSinh, diaChi); // gọi constructor của Nguoi
        this.maGiangVien = maGiangVien;
        this.chuyenMon = chuyenMon;
        this.luongCoBan = luongCoBan;
        this.heSoLuong = heSoLuong;
    }

    // Getter và Setter riêng
    public String getMaGiangVien() {
        return maGiangVien;
    }

    public String getChuyenMon() {
        return chuyenMon;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    // Tính lương = lương cơ bản * hệ số lương
    public double tinhLuong() {
        return luongCoBan * heSoLuong;
    }

    // Ghi đè phương thức hienThiThongTin() của lớp cha
    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin(); // hiển thị thông tin chung trước
        System.out.println("Ma giang vien: " + maGiangVien);
        System.out.println("Chuyen mon: " + chuyenMon);
        System.out.println("Luong co ban: " + luongCoBan);
        System.out.println("He so luong: " + heSoLuong);
        System.out.println("Luong thuc nhan: " + tinhLuong());
    }
}