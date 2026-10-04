import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PolygonApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<AbstractPolygon> ds = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Tạo đa giác mới");
            System.out.println("2. Xem danh sách đa giác (diện tích, chu vi)");
            System.out.println("3. So sánh hai đa giác có giống nhau không");
            System.out.println("0. Thoát");
            int c = docSoNguyen("Chọn: ");
            try {
                switch (c) {
                    case 1 -> taoDaGiac();
                    case 2 -> xemDanhSach();
                    case 3 -> soSanh();
                    case 0 -> {
                        System.out.println("Tạm biệt!");
                        return;
                    }
                    default -> System.out.println("Lựa chọn không hợp lệ");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    private static void taoDaGiac() {
        System.out.println("--- Nhập bằng tọa độ các đỉnh ---");
        System.out.println("1. Tam giác   2. Tứ giác   3. Ngũ giác   4. Lục giác   5. Bát giác");
        System.out.println("--- Nhập bằng kích thước ---");
        System.out.println("6. Tam giác cân   7. Tam giác đều   8. Hình chữ nhật   9. Hình vuông");
        int t = docSoNguyen("Chọn loại: ");

        AbstractPolygon p = switch (t) {
            case 1 -> new Triangle(docDinh(3));
            case 2 -> new Quadrilateral(docDinh(4));
            case 3 -> new Pentagon(docDinh(5));
            case 4 -> new Hexagon(docDinh(6));
            case 5 -> new Octagon(docDinh(8));
            case 6 -> new IsoscelesTriangle(docSoThuc("Cạnh đáy: "), docSoThuc("Cạnh bên: "));
            case 7 -> new EquilateralTriangle(docSoThuc("Độ dài cạnh: "));
            case 8 -> new Rectangle(docSoThuc("Chiều dài: "), docSoThuc("Chiều rộng: "));
            case 9 -> new Square(docSoThuc("Độ dài cạnh: "));
            default -> null;
        };

        if (p == null) {
            System.out.println("Loại không hợp lệ");
            return;
        }
        ds.add(p);
        System.out.println("Đã thêm đa giác #" + ds.size());
        in(ds.size() - 1);
    }

    private static void xemDanhSach() {
        if (ds.isEmpty()) {
            System.out.println("Chưa có đa giác nào");
            return;
        }
        for (int i = 0; i < ds.size(); i++) {
            in(i);
        }
    }

    private static void soSanh() {
        if (ds.size() < 2) {
            System.out.println("Cần ít nhất 2 đa giác để so sánh");
            return;
        }
        int i = docSoNguyen("Chọn đa giác thứ nhất (1.." + ds.size() + "): ");
        int j = docSoNguyen("Chọn đa giác thứ hai (1.." + ds.size() + "): ");
        if (i < 1 || i > ds.size() || j < 1 || j > ds.size()) {
            System.out.println("Số thứ tự không hợp lệ");
            return;
        }
        boolean giong = ds.get(i - 1).sameAs(ds.get(j - 1));
        System.out.println("Đa giác #" + i + " và #" + j
                + (giong ? " GIỐNG nhau (cùng các đỉnh)" : " KHÔNG giống nhau"));
    }

    private static void in(int i) {
        AbstractPolygon p = ds.get(i);
        System.out.println("#" + (i + 1) + " " + p.getName());
        System.out.println("   Các đỉnh : " + p.getVertices());
        System.out.printf("   Diện tích: %.3f%n", p.area());
        System.out.printf("   Chu vi   : %.3f%n", p.perimeter());
    }

    // Nhập n đỉnh, mỗi đỉnh một dòng dạng "x y"
    private static List<Point> docDinh(int n) {
        List<Point> list = new ArrayList<>();
        int i = 1;
        while (i <= n) {
            System.out.print("Đỉnh " + i + " (nhập: x y): ");
            String[] parts = sc.nextLine().trim().replace(',', ' ').split("\\s+");
            try {
                if (parts.length != 2) {
                    throw new NumberFormatException();
                }
                list.add(new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1])));
                i++;
            } catch (NumberFormatException e) {
                System.out.println("Nhập 2 số cách nhau bởi khoảng trắng, ví dụ: 3 4");
            }
        }
        return list;
    }

    private static int docSoNguyen(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên!");
            }
        }
    }

    private static double docSoThuc(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
}
