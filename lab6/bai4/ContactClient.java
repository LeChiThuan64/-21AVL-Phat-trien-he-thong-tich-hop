import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Scanner;

public class ContactClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            ContactService service = (ContactService) registry.lookup("ContactService");

            while (true) {
                System.out.println("\n===== QUAN LY DANH BA =====");
                System.out.println("1. Them lien he");
                System.out.println("2. Tim lien he");
                System.out.println("3. Sua so dien thoai");
                System.out.println("4. Xoa lien he");
                System.out.println("5. Xem toan bo danh ba");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1": {
                        System.out.print("Ten: ");
                        String name = sc.nextLine().trim();
                        System.out.print("So dien thoai: ");
                        String phone = sc.nextLine().trim();
                        service.addContact(name, phone);
                        System.out.println("=> Da them lien he.");
                        break;
                    }
                    case "2": {
                        System.out.print("Ten can tim: ");
                        String phone = service.findContact(sc.nextLine().trim());
                        System.out.println(phone != null ? "=> So dien thoai: " + phone : "=> Khong tim thay.");
                        break;
                    }
                    case "3": {
                        System.out.print("Ten can sua: ");
                        String name = sc.nextLine().trim();
                        System.out.print("So dien thoai moi: ");
                        String phone = sc.nextLine().trim();
                        System.out.println(service.updateContact(name, phone) ? "=> Da cap nhat." : "=> Khong tim thay lien he.");
                        break;
                    }
                    case "4": {
                        System.out.print("Ten can xoa: ");
                        System.out.println(service.deleteContact(sc.nextLine().trim()) ? "=> Da xoa." : "=> Khong tim thay lien he.");
                        break;
                    }
                    case "5": {
                        Map<String, String> all = service.listContacts();
                        if (all.isEmpty()) System.out.println("=> Danh ba trong.");
                        else all.forEach((n, p) -> System.out.println("  - " + n + " : " + p));
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
