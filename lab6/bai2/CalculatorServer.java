import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Calculator", new CalculatorImpl());
            System.out.println("[Server] Calculator da san sang (cong 1099)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
