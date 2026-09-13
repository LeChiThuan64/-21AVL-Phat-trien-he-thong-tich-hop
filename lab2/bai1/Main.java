package lab2.bai1;

public class Main {
    public static void main(String[] args) {
        // 1. Tao hai san pham
        SanPham sp1 = new SanPham("SP01", "Ban phim co", 500000, 10);
        SanPham sp2 = new SanPham("SP02", "Chuot khong day", 200000, 20);

        System.out.println("===== THONG TIN BAN DAU =====");
        sp1.hienThiThongTin();
        sp2.hienThiThongTin();

        // 2. Nhap them hang cho sp1
        System.out.println("\n===== NHAP HANG CHO SP1 =====");
        sp1.nhapHang(5);
        sp1.hienThiThongTin();

        // 3. Ban hang thanh cong (sp2)
        System.out.println("\n===== BAN HANG THANH CONG (SP2) =====");
        sp2.hienThiThongTin(); // truoc khi ban
        sp2.banHang(5);
        sp2.hienThiThongTin(); // sau khi ban

        // 4. Ban so luong lon hon ton kho (sp2)
        System.out.println("\n===== THU BAN VUOT QUA TON KHO (SP2) =====");
        sp2.hienThiThongTin(); // truoc khi ban
        boolean ketQua = sp2.banHang(1000);
        System.out.println("Ket qua ban hang: " + ketQua);
        sp2.hienThiThongTin(); // sau khi ban (khong doi vi that bai)
    }
}