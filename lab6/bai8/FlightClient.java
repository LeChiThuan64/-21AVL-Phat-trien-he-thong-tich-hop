import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Scanner;

public class FlightClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            FlightService fs = (FlightService) registry.lookup("FlightService");

            while (true) {
                System.out.println("\n===== DAT VE MAY BAY =====");
                System.out.println("1. Xem danh sach chuyen bay");
                System.out.println("2. Kiem tra ghe trong");
                System.out.println("3. Dat ve");
                System.out.println("4. Huy ve");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                String choice = sc.nextLine().trim();
                try {
                    switch (choice) {
                        case "1": {
                            Map<String, Integer> all = fs.listFlights();
                            all.forEach((k, v) -> System.out.println("  - " + k + " : con " + v + " ghe"));
                            break;
                        }
                        case "2": {
                            System.out.print("Ma chuyen bay: ");
                            String code = sc.nextLine().trim();
                            int n = fs.availableSeats(code);
                            System.out.println(n < 0 ? "=> Khong ton tai chuyen bay nay." : "=> Con " + n + " ghe trong.");
                            break;
                        }
                        case "3": {
                            System.out.print("Ma chuyen bay: ");
                            String code = sc.nextLine().trim();
                            System.out.print("So ghe can dat: ");
                            int seats = Integer.parseInt(sc.nextLine().trim());
                            System.out.println(fs.bookTicket(code, seats)
                                    ? "=> Dat ve thanh cong!"
                                    : "=> Dat ve that bai (sai ma chuyen hoac khong du ghe).");
                            break;
                        }
                        case "4": {
                            System.out.print("Ma chuyen bay: ");
                            String code = sc.nextLine().trim();
                            System.out.print("So ghe can huy: ");
                            int seats = Integer.parseInt(sc.nextLine().trim());
                            System.out.println(fs.cancelBooking(code, seats)
                                    ? "=> Huy ve thanh cong!"
                                    : "=> Huy ve that bai (sai ma chuyen hoac so ghe huy khong hop le).");
                            break;
                        }
                        case "0":
                            System.out.println("Tam biet!");
                            return;
                        default:
                            System.out.println("Lua chon khong hop le!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap so nguyen hop le!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
