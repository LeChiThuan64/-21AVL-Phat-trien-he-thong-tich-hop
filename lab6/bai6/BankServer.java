import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BankServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BankService", new BankServiceImpl(1000000)); // so du ban dau: 1,000,000
            System.out.println("[Server] BankService san sang. So du ban dau: 1000000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
