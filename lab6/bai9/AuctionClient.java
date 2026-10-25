import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class AuctionClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            AuctionService auction = (AuctionService) registry.lookup("AuctionService");

            System.out.print("Nhap ten nguoi dung cua ban: ");
            String user = sc.nextLine().trim();
            if (user.isEmpty()) {
                System.out.println("Ten khong duoc de trong!");
                return;
            }
            // Dang ky callback de server thong bao realtime
            auction.register(user, new BidderCallbackImpl());

            while (true) {
                System.out.println("\n===== DAU GIA TRUC TUYEN (" + user + ") =====");
                System.out.println("1. [Seller] Them san pham dau gia");
                System.out.println("2. [Bidder] Xem danh sach san pham");
                System.out.println("3. [Bidder] Dat gia thau");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                String choice = sc.nextLine().trim();
                try {
                    switch (choice) {
                        case "1": {
                            System.out.print("Ten san pham: ");
                            String name = sc.nextLine().trim();
                            System.out.print("Gia khoi diem: ");
                            double price = Double.parseDouble(sc.nextLine().trim());
                            int id = auction.addItem(user, name, price);
                            System.out.println("=> Da them san pham voi ma #" + id);
                            break;
                        }
                        case "2": {
                            List<AuctionItem> list = auction.listItems();
                            if (list.isEmpty()) System.out.println("=> Chua co san pham nao.");
                            else list.forEach(System.out::println);
                            break;
                        }
                        case "3": {
                            System.out.print("Ma san pham: ");
                            int id = Integer.parseInt(sc.nextLine().trim());
                            System.out.print("Gia ban muon dat: ");
                            double amount = Double.parseDouble(sc.nextLine().trim());
                            System.out.println(auction.placeBid(id, user, amount)
                                    ? "=> Dat gia thanh cong! Ban dang dan dau."
                                    : "=> That bai (sai ma SP, gia khong cao hon gia hien tai, hoac ban la nguoi ban).");
                            break;
                        }
                        case "0":
                            System.out.println("Tam biet!");
                            System.exit(0); // callback la doi tuong RMI nen can exit de tat han
                            return;
                        default:
                            System.out.println("Lua chon khong hop le!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap so hop le!");
                } catch (java.rmi.RemoteException e) {
                    System.out.println("Loi: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
