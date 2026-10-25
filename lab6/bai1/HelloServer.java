import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("HelloService", new HelloServiceImpl());
            System.out.println("[Server] HelloService da san sang (cong 1099)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
