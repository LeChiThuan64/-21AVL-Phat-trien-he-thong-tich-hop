package bai3;

import java.time.LocalDate;

public class PersonDemo {
    public static void main(String[] args) {
        // Dùng constructor đầy đủ tham số
        Person p1 = new Person("Nguyễn Văn A", "Nam", LocalDate.of(2004, 5, 20), "TP.HCM");
        System.out.println("--- Person p1 (tạo sẵn) ---");
        p1.printInfo();

        // Dùng constructor không tham số rồi nhập từ bàn phím
        Person p2 = new Person();
        System.out.println("\n--- Nhập Person p2 từ bàn phím ---");
        p2.inputInfo();
        System.out.println("\n--- Thông tin p2 ---");
        p2.printInfo();
    }
}
