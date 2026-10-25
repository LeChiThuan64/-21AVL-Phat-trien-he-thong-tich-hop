import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;
import java.util.Scanner;

public class BankClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        try (Scanner sc = new Scanner(System.in)) {
            sc.useLocale(Locale.US);
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            BankService bank = (BankService) registry.lookup("BankService");

            while (true) {
                System.out.println("\n===== NGAN HANG =====");
                System.out.println("1. Kiem tra so du");
                System.out.println("2. Gui tien");
                System.out.println("3. Rut tien");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                String choice = sc.nextLine().trim();
                try {
                    switch (choice) {
                        case "1":
                            System.out.printf("=> So du hien tai: %,.2f%n", bank.getBalance());
                            break;
                        case "2": {
                            System.out.print("So tien gui: ");
                            double a = Double.parseDouble(sc.nextLine().trim());
                            bank.deposit(a);
                            System.out.printf("=> Gui thanh cong. So du moi: %,.2f%n", bank.getBalance());
                            break;
                        }
                        case "3": {
                            System.out.print("So tien rut: ");
                            double a = Double.parseDouble(sc.nextLine().trim());
                            if (bank.withdraw(a))
                                System.out.printf("=> Rut thanh cong. So du moi: %,.2f%n", bank.getBalance());
                            else
                                System.out.println("=> Rut that bai (so du khong du hoac so tien khong hop le).");
                            break;
                        }
                        case "0":
                            System.out.println("Tam biet!");
                            return;
                        default:
                            System.out.println("Lua chon khong hop le!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap so hop le!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
