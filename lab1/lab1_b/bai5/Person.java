package bai5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class Person {
    // Dùng chung 1 Scanner cho cả Person và Student để tránh lỗi đọc bàn phím
    protected static final Scanner SC = new Scanner(System.in);
    protected static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private String ten;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String diaChi;

    public Person() {
        this.ten = "";
        this.gioiTinh = "Nam";
        this.ngaySinh = LocalDate.of(2000, 1, 1);
        this.diaChi = "";
    }

    public Person(String ten, String gioiTinh, LocalDate ngaySinh, String diaChi) {
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public String getTen() { return ten; }
    public String getGioiTinh() { return gioiTinh; }
    public LocalDate getNgaySinh() { return ngaySinh; }
    public String getDiaChi() { return diaChi; }

    public void setTen(String ten) { this.ten = ten; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    // Nhập thông tin từ bàn phím
    public void inputInfo() {
        ten = docChuoiKhongRong("Nhập tên: ");
        gioiTinh = docGioiTinh();
        ngaySinh = docNgaySinh();
        diaChi = docChuoiKhongRong("Nhập địa chỉ: ");
    }

    // Hiển thị thông tin
    public void printInfo() {
        System.out.println("Tên: " + ten);
        System.out.println("Giới tính: " + gioiTinh);
        System.out.println("Ngày sinh: " + ngaySinh.format(FMT));
        System.out.println("Địa chỉ: " + diaChi);
    }

    // ===== Các hàm hỗ trợ nhập liệu (Student dùng lại) =====
    protected static String docChuoiKhongRong(String msg) {
        while (true) {
            System.out.print(msg);
            String s = SC.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Không được để trống!");
        }
    }

    protected static double docSoThuc(String msg, double min, double max) {
        while (true) {
            System.out.print(msg);
            try {
                double d = Double.parseDouble(SC.nextLine().trim().replace(',', '.'));
                if (d >= min && d <= max) {
                    return d;
                }
                System.out.println("Giá trị phải từ " + min + " đến " + max);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    private static String docGioiTinh() {
        while (true) {
            System.out.print("Giới tính (Nam/Nữ): ");
            String s = SC.nextLine().trim();
            if (s.equalsIgnoreCase("nam")) {
                return "Nam";
            }
            if (s.equalsIgnoreCase("nữ") || s.equalsIgnoreCase("nu")) {
                return "Nữ";
            }
            System.out.println("Chỉ nhận Nam hoặc Nữ!");
        }
    }

    private static LocalDate docNgaySinh() {
        while (true) {
            System.out.print("Ngày sinh (dd/MM/yyyy): ");
            String s = SC.nextLine().trim();
            try {
                LocalDate d = LocalDate.parse(s, FMT);
                if (d.isAfter(LocalDate.now())) {
                    System.out.println("Ngày sinh không được ở tương lai!");
                } else {
                    return d;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ, ví dụ đúng: 25/12/2004");
            }
        }
    }
}
