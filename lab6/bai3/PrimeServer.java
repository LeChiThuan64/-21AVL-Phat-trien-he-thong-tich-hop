import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PrimeServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PrimeService", new PrimeServiceImpl());
            System.out.println("[Server] PrimeService da san sang (cong 1099)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
