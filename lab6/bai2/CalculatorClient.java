import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            Calculator calc = (Calculator) registry.lookup("Calculator");

            System.out.print("Nhap so nguyen a: ");
            int a = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Nhap so nguyen b: ");
            int b = Integer.parseInt(sc.nextLine().trim());

            System.out.println(a + " + " + b + " = " + calc.add(a, b));
        } catch (NumberFormatException e) {
            System.out.println("Gia tri nhap khong phai so nguyen hop le!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
