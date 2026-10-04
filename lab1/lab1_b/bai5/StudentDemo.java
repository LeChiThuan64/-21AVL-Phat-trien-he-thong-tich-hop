import java.util.ArrayList;
import java.util.List;

public class StudentDemo {
    public static void main(String[] args) {
        int n = docSoLuong();
        List<Student> ds = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Nhập sinh viên thứ " + i + " ---");
            Student s = new Student();
            s.inputInfo();
            ds.add(s);
        }

        System.out.println("\n===== DANH SÁCH TẤT CẢ SINH VIÊN =====");
        for (int i = 0; i < ds.size(); i++) {
            System.out.println("\nSinh viên " + (i + 1) + ":");
            ds.get(i).printInfo();
        }

        // Tìm điểm cao nhất và thấp nhất
        double max = ds.get(0).getDiemTB();
        double min = ds.get(0).getDiemTB();
        for (Student s : ds) {
            if (s.getDiemTB() > max) max = s.getDiemTB();
            if (s.getDiemTB() < min) min = s.getDiemTB();
        }

        System.out.println("\n===== SINH VIÊN CÓ ĐIỂM TRUNG BÌNH CAO NHẤT (" + max + ") =====");
        for (Student s : ds) {
            if (s.getDiemTB() == max) {
                s.printInfo();
                System.out.println();
            }
        }

        System.out.println("===== SINH VIÊN CÓ ĐIỂM TRUNG BÌNH THẤP NHẤT (" + min + ") =====");
        for (Student s : ds) {
            if (s.getDiemTB() == min) {
                s.printInfo();
                System.out.println();
            }
        }

        System.out.println("===== SINH VIÊN ĐƯỢC HỌC BỔNG (điểm TB > 8.0) =====");
        boolean coAi = false;
        for (Student s : ds) {
            if (s.coHocBong()) {
                s.printInfo();
                System.out.println();
                coAi = true;
            }
        }
        if (!coAi) {
            System.out.println("Không có sinh viên nào được học bổng.");
        }
    }

    private static int docSoLuong() {
        while (true) {
            System.out.print("Nhập số lượng sinh viên n (> 0): ");
            try {
                int n = Integer.parseInt(Person.SC.nextLine().trim());
                if (n > 0) {
                    return n;
                }
                System.out.println("n phải lớn hơn 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên!");
            }
        }
    }
}
