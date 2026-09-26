import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Bai tap tong hop 9: quan ly ton kho bang OOP + Java I/O Streams.
 * Luong xu ly: nhap tu ban phim -> ghi CSV -> doc lai CSV -> hien thi,
 * tinh tong, tim san pham gia tri cao nhat -> ghi bao cao.
 *
 * Ghi chu: thong bao in ra man hinh (console) khong dau de tranh loi
 * hien thi tren mot so terminal Windows. Rieng NOI DUNG ghi vao tep
 * CSV va bao cao van dung UTF-8 co dau, dung theo yeu cau de bai.
 */
public class InventoryManager {

    private static final Path CSV_FILE = Path.of("data", "inventory.csv");
    private static final Path REPORT_FILE = Path.of("data", "inventory-report.txt");

    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));

        List<Product> products = inputProducts(console);

        if (products.isEmpty()) {
            System.out.println("Khong co san pham nao duoc nhap. Chuong trinh ket thuc.");
            return;
        }

        saveToCsv(products);

        // Doc lai tu tep de chung minh du lieu da duoc luu dung (yeu cau 9.1.3)
        List<Product> reloaded = loadFromCsv();

        System.out.println("\n=== Danh sach san pham doc lai tu tep CSV ===");
        double total = 0;
        Product maxProduct = null;
        for (Product p : reloaded) {
            System.out.println(p);
            total += p.inventoryValue();
            if (maxProduct == null || p.inventoryValue() > maxProduct.inventoryValue()) {
                maxProduct = p;
            }
        }

        System.out.printf("%nTong gia tri ton kho: %,.0f VND%n", total);
        if (maxProduct != null) {
            System.out.println("San pham co gia tri ton kho cao nhat: " + maxProduct);
        }

        writeReport(reloaded, total, maxProduct);
    }

    /**
     * Nhap danh sach san pham tu ban phim.
     * De trong ma san pham de ket thuc nhap.
     * Du lieu khong hop le (ma/ten rong, gia <= 0, so luong am, khong parse
     * duoc so) se bi tu choi va cho nhap lai dung san pham do, chuong trinh
     * khong dung (dap ung test case "Ma rong" va "Gia am" o muc 9.3).
     */
    private static List<Product> inputProducts(BufferedReader console) {
        List<Product> products = new ArrayList<>();
        System.out.println("Nhap danh sach san pham. De trong ma san pham de ket thuc nhap.");
        int index = 1;
        try {
            while (true) {
                System.out.printf("%n-- San pham #%d --%n", index);
                System.out.print("Ma san pham: ");
                String code = console.readLine();
                if (code == null || code.isBlank()) {
                    break;
                }
                System.out.print("Ten san pham: ");
                String name = console.readLine();
                System.out.print("Don gia: ");
                String priceText = console.readLine();
                System.out.print("So luong: ");
                String quantityText = console.readLine();

                try {
                    double price = Double.parseDouble(priceText.trim());
                    int quantity = Integer.parseInt(quantityText.trim());
                    Product product = new Product(code.trim(), name.trim(), price, quantity);
                    products.add(product);
                    index++;
                } catch (NumberFormatException e) {
                    System.out.println("Don gia hoac so luong khong hop le (khong phai so). "
                            + "Vui long nhap lai san pham nay.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Du lieu khong hop le: " + e.getMessage()
                            + ". Vui long nhap lai san pham nay.");
                }
            }
        } catch (IOException e) {
            System.err.println("Loi doc du lieu tu ban phim: " + e.getMessage());
        }
        return products;
    }

    /** Ghi danh sach san pham vao tep CSV bang UTF-8, dung try-with-resources. */
    private static void saveToCsv(List<Product> products) {
        try {
            Files.createDirectories(CSV_FILE.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(CSV_FILE, StandardCharsets.UTF_8)) {
                writer.write("ma,ten,donGia,soLuong");
                writer.newLine();
                for (Product p : products) {
                    writer.write(String.join(",",
                            p.getCode(), p.getName(),
                            String.valueOf(p.getUnitPrice()),
                            String.valueOf(p.getQuantity())));
                    writer.newLine();
                }
            }
            System.out.println("\nDa luu " + products.size() + " san pham vao " + CSV_FILE);
        } catch (IOException e) {
            System.err.println("Khong ghi duoc tep " + CSV_FILE + ": " + e.getMessage());
        }
    }

    /**
     * Doc lai danh sach san pham tu tep CSV.
     * Neu tep khong ton tai (test case "Tep thieu"), IOException se duoc bat
     * va chuong trinh thong bao ro rang thay vi dung bat thuong.
     * Dong thieu cot hoac du lieu so sai (test case "CSV loi") se bi bo qua,
     * kem so dong loi.
     */
    private static List<Product> loadFromCsv() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(CSV_FILE, StandardCharsets.UTF_8)) {
            reader.readLine(); // bo qua dong tieu de
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;
                String[] parts = line.split(",", -1);
                if (parts.length != 4) {
                    System.err.println("Bo qua dong " + lineNumber + " trong " + CSV_FILE
                            + ": thieu cot (can 4, co " + parts.length + ").");
                    continue;
                }
                try {
                    products.add(new Product(
                            parts[0].trim(), parts[1].trim(),
                            Double.parseDouble(parts[2].trim()),
                            Integer.parseInt(parts[3].trim())));
                } catch (IllegalArgumentException e) {
                    // NumberFormatException la lop con cua IllegalArgumentException
                    // nen bat IllegalArgumentException la du cho ca hai truong hop.
                    System.err.println("Bo qua dong " + lineNumber + " trong " + CSV_FILE
                            + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Khong tim thay hoac khong doc duoc tep " + CSV_FILE
                    + ": " + e.getMessage());
        }
        return products;
    }

    /** Ghi bao cao tong hop vao tep van ban UTF-8. */
    private static void writeReport(List<Product> products, double total, Product maxProduct) {
        try {
            Files.createDirectories(REPORT_FILE.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(REPORT_FILE, StandardCharsets.UTF_8)) {
                writer.write("BAO CAO TON KHO");
                writer.newLine();
                writer.write("So san pham: " + products.size());
                writer.newLine();
                writer.write("Tong gia tri ton kho: %,.0f VND".formatted(total));
                writer.newLine();
                if (maxProduct != null) {
                    writer.write("San pham co gia tri ton kho cao nhat: " + maxProduct);
                    writer.newLine();
                }
            }
            System.out.println("Da ghi bao cao vao " + REPORT_FILE);
        } catch (IOException e) {
            System.err.println("Khong ghi duoc bao cao " + REPORT_FILE + ": " + e.getMessage());
        }
    }
}