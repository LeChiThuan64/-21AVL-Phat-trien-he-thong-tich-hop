import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ChatServiceImpl service = new ChatServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatService", service);
            System.out.println("[Server] ChatService san sang. Khi client nhan, hay go phan hoi roi Enter.");
            System.out.println("[Server] Go 'exit' de tat server.");

            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    System.exit(0);
                }
                service.pushReply(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
