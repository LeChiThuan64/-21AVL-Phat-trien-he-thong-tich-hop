package bai5;

import java.time.LocalDate;

public class Student extends Person {
    private String maSV;
    private double diemTB;
    private String email;

    public Student() {
        super();
        this.maSV = "";
        this.diemTB = 0.0;
        this.email = "";
    }

    public Student(String ten, String gioiTinh, LocalDate ngaySinh, String diaChi,
                   String maSV, double diemTB, String email) {
        super(ten, gioiTinh, ngaySinh, diaChi);
        this.maSV = maSV;
        setDiemTB(diemTB);
        setEmail(email);
    }

    public String getMaSV() { return maSV; }
    public double getDiemTB() { return diemTB; }
    public String getEmail() { return email; }

    public void setMaSV(String maSV) { this.maSV = maSV; }

    public void setDiemTB(double diemTB) {
        if (diemTB < 0.0 || diemTB > 10.0) {
            throw new IllegalArgumentException("Điểm trung bình phải từ 0.0 đến 10.0");
        }
        this.diemTB = diemTB;
    }

    public void setEmail(String email) {
        if (!emailHopLe(email)) {
            throw new IllegalArgumentException("Email phải chứa @ và không có khoảng trắng");
        }
        this.email = email;
    }

    // Email phải chứa ký tự @ và không tồn tại khoảng trắng
    public static boolean emailHopLe(String e) {
        return e != null && e.contains("@") && !e.matches(".*\\s.*");
    }

    // Có học bổng khi điểm trung bình trên 8.0
    public boolean coHocBong() {
        return diemTB > 8.0;
    }

    @Override
    public void inputInfo() {
        super.inputInfo();
        maSV = docChuoiKhongRong("Nhập mã SV: ");
        diemTB = docSoThuc("Nhập điểm trung bình (0.0 - 10.0): ", 0.0, 10.0);
        while (true) {
            System.out.print("Nhập email: ");
            String s = SC.nextLine().trim();
            if (emailHopLe(s)) {
                email = s;
                break;
            }
            System.out.println("Email phải chứa @ và không có khoảng trắng!");
        }
    }

    @Override
    public void printInfo() {
        System.out.println("Mã SV: " + maSV);
        super.printInfo();
        System.out.printf("Điểm trung bình: %.2f%n", diemTB);
        System.out.println("Email: " + email);
        System.out.println("Học bổng: " + (coHocBong() ? "Có" : "Không"));
    }
}
