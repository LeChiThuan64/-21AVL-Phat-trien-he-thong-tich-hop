import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        try {
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            HelloService service = (HelloService) registry.lookup("HelloService");
            System.out.println("Server tra ve: " + service.sayHello());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
