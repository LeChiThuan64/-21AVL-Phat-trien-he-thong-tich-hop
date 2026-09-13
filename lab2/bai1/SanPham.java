package lab2.bai1;

public class SanPham {
    // Thuoc tinh private - dong goi du lieu
    private String maSanPham;
    private String tenSanPham;
    private double donGia;
    private int soLuong;

    // Constructor day du tham so
    public SanPham(String maSanPham, String tenSanPham, double donGia, int soLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    // Getter (khong can setter vi bai yeu cau kiem soat qua phuong thuc nghiep vu)
    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public double getDonGia() {
        return donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    // Tinh thanh tien = don gia * so luong
    public double tinhThanhTien() {
        return donGia * soLuong;
    }

    // Nhap them hang vao kho
    public void nhapHang(int soLuongNhap) {
        if (soLuongNhap <= 0) {
            System.out.println("So luong nhap phai lon hon 0!");
            return;
        }
        soLuong = soLuong + soLuongNhap;
        System.out.println("Nhap hang thanh cong. So luong nhap: " + soLuongNhap);
    }

    // Ban hang - tra ve true neu ban thanh cong, false neu khong du hang
    public boolean banHang(int soLuongBan) {
        if (soLuongBan <= 0) {
            System.out.println("So luong ban phai lon hon 0!");
            return false;
        }

        if (soLuongBan > soLuong) {
            System.out.println("Khong du hang de ban! Ton kho hien tai: " + soLuong);
            return false;
        }

        // Du dieu kien -> cap nhat so luong
        soLuong = soLuong - soLuongBan;
        System.out.println("Ban hang thanh cong: " + soLuongBan + " san pham.");
        return true;
    }

    // Hien thi thong tin san pham
    public void hienThiThongTin() {
        System.out.println("Ma SP: " + maSanPham
                + " | Ten: " + tenSanPham
                + " | Don gia: " + donGia
                + " | So luong: " + soLuong
                + " | Thanh tien: " + tinhThanhTien());
    }
}