import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FlightServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("FlightService", new FlightServiceImpl());
            System.out.println("[Server] FlightService da san sang (cong 1099)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
