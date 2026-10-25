import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ShapeServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ShapeService", new ShapeServiceImpl());
            System.out.println("[Server] ShapeService da san sang (cong 1099)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
