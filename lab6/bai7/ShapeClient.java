import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ShapeClient {
    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double v = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                if (v < 0) {
                    System.out.println("Gia tri phai >= 0!");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }
    }

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            ShapeService shape = (ShapeService) registry.lookup("ShapeService");

            while (true) {
                System.out.println("\n===== TINH DIEN TICH =====");
                System.out.println("1. Hinh chu nhat");
                System.out.println("2. Hinh tron");
                System.out.println("3. Hinh tam giac");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1": {
                        double w = readDouble(sc, "Chieu rong: ");
                        double h = readDouble(sc, "Chieu cao: ");
                        System.out.printf("=> Dien tich hinh chu nhat = %.2f%n", shape.rectangleArea(w, h));
                        break;
                    }
                    case "2": {
                        double r = readDouble(sc, "Ban kinh: ");
                        System.out.printf("=> Dien tich hinh tron = %.2f%n", shape.circleArea(r));
                        break;
                    }
                    case "3": {
                        double b = readDouble(sc, "Day: ");
                        double h = readDouble(sc, "Chieu cao: ");
                        System.out.printf("=> Dien tich tam giac = %.2f%n", shape.triangleArea(b, h));
                        break;
                    }
                    case "0":
                        System.out.println("Tam biet!");
                        return;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
