import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AuctionServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AuctionService", new AuctionServiceImpl());
            System.out.println("[Server] AuctionService da san sang (cong 1099)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
