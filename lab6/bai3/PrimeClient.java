import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class PrimeClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            PrimeService service = (PrimeService) registry.lookup("PrimeService");

            while (true) {
                System.out.print("Nhap so nguyen can kiem tra (nhap 'q' de thoat): ");
                String line = sc.nextLine().trim();
                if (line.equalsIgnoreCase("q")) break;
                try {
                    int n = Integer.parseInt(line);
                    boolean r = service.isPrime(n);
                    System.out.println(n + (r ? " LA so nguyen to." : " KHONG phai so nguyen to."));
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap mot so nguyen hop le!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
