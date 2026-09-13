public class Main {
    public static void main(String[] args) {

        // Tạo hai sinh viên
        SinhVien sv1 = new SinhVien("Nguyen Van A", 2004, "TP.HCM",
                "SV001", "Cong nghe thong tin", 8.7);
        SinhVien sv2 = new SinhVien("Tran Thi B", 2003, "Ha Noi",
                "SV002", "Ky thuat phan mem", 6.5);

        // Tạo hai giảng viên
        GiangVien gv1 = new GiangVien("Le Van C", 1980, "Da Nang",
                "GV001", "Mang may tinh", 6000000, 3.5);
        GiangVien gv2 = new GiangVien("Pham Thi D", 1975, "Can Tho",
                "GV002", "Co so du lieu", 6000000, 4.0);

        System.out.println("===== THONG TIN SINH VIEN =====");
        sv1.hienThiThongTin();
        System.out.println();
        sv2.hienThiThongTin();

        System.out.println("\n===== THONG TIN GIANG VIEN =====");
        gv1.hienThiThongTin();
        System.out.println();
        gv2.hienThiThongTin();
    }
}