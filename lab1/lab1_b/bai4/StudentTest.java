package bai4;

public class StudentTest {
    public static void main(String[] args) {
        Student s = new Student();
        System.out.println("--- Nhập thông tin sinh viên ---");
        s.inputInfo();
        System.out.println("\n--- Thông tin sinh viên ---");
        s.printInfo();
        System.out.println(s.coHocBong()
                ? "=> Sinh viên được học bổng"
                : "=> Sinh viên không được học bổng");
    }
}
