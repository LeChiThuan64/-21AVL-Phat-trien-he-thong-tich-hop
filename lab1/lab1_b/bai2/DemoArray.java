package bai2;
import java.util.Scanner;

public class DemoArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max;
        do {
            max = MangSoNguyen.docSo(sc, "Nhập số phần tử tối đa của mảng (> 0): ");
        } while (max <= 0);

        MangSoNguyen m = new MangSoNguyen(max);

        while (true) {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Nhập mảng");
            System.out.println("2. Xuất mảng");
            System.out.println("3. Thêm phần tử Y vào đầu");
            System.out.println("4. Thêm phần tử Y vào cuối");
            System.out.println("5. Thêm phần tử Y vào vị trí thứ i");
            System.out.println("6. Xóa phần tử có giá trị X");
            System.out.println("7. Xóa phần tử thứ j");
            System.out.println("8. Sắp xếp tăng dần (Radix)");
            System.out.println("9. Sắp xếp giảm dần (Radix)");
            System.out.println("10. Tìm B (mảng chưa sắp xếp - tìm tuần tự)");
            System.out.println("11. Tìm B (mảng đã sắp xếp - tìm nhị phân)");
            System.out.println("0. Thoát");
            int c = MangSoNguyen.docSo(sc, "Chọn: ");

            switch (c) {
                case 1 -> {
                    m.nhap(sc);
                    m.xuat();
                }
                case 2 -> m.xuat();
                case 3 -> {
                    int y = MangSoNguyen.docSo(sc, "Nhập Y: ");
                    ketQuaThem(m.themDau(y), m);
                }
                case 4 -> {
                    int y = MangSoNguyen.docSo(sc, "Nhập Y: ");
                    ketQuaThem(m.themCuoi(y), m);
                }
                case 5 -> {
                    int y = MangSoNguyen.docSo(sc, "Nhập Y: ");
                    int i = MangSoNguyen.docSo(sc, "Nhập vị trí i (1.." + (m.getN() + 1) + "): ");
                    ketQuaThem(m.themTai(i, y), m);
                }
                case 6 -> {
                    int x = MangSoNguyen.docSo(sc, "Nhập X cần xóa: ");
                    int k = m.xoaGiaTri(x);
                    if (k == 0) {
                        System.out.println("Không có phần tử nào bằng " + x);
                    } else {
                        System.out.println("Đã xóa " + k + " phần tử");
                        m.xuat();
                    }
                }
                case 7 -> {
                    int j = MangSoNguyen.docSo(sc, "Nhập vị trí j (1.." + m.getN() + "): ");
                    if (m.xoaViTri(j)) {
                        System.out.println("Đã xóa");
                        m.xuat();
                    } else {
                        System.out.println("Vị trí không hợp lệ");
                    }
                }
                case 8 -> {
                    m.radixSort(true);
                    System.out.print("Sau khi sắp xếp tăng dần: ");
                    m.xuat();
                }
                case 9 -> {
                    m.radixSort(false);
                    System.out.print("Sau khi sắp xếp giảm dần: ");
                    m.xuat();
                }
                case 10 -> {
                    int b = MangSoNguyen.docSo(sc, "Nhập B cần tìm: ");
                    in(m.timTuyenTinh(b), b);
                }
                case 11 -> {
                    int b = MangSoNguyen.docSo(sc, "Nhập B cần tìm: ");
                    try {
                        in(m.timNhiPhan(b), b);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 0 -> {
                    System.out.println("Tạm biệt!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    private static void ketQuaThem(boolean ok, MangSoNguyen m) {
        if (ok) {
            System.out.println("Đã thêm");
            m.xuat();
        } else {
            System.out.println("Không thêm được (mảng đầy hoặc vị trí không hợp lệ)");
        }
    }

    private static void in(int vt, int b) {
        if (vt == -1) {
            System.out.println("Không tìm thấy " + b);
        } else {
            System.out.println("Tìm thấy " + b + " ở vị trí thứ " + vt);
        }
    }
}
