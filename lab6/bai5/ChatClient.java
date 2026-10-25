import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            ChatService chat = (ChatService) registry.lookup("ChatService");

            System.out.println("Da ket noi server. Go 'exit' de thoat.");
            while (true) {
                System.out.print("Ban: ");
                String msg = sc.nextLine();
                if (msg.equalsIgnoreCase("exit")) break;
                if (msg.trim().isEmpty()) continue;

                chat.sendMessage(msg);
                System.out.println("(dang cho server phan hoi...)");
                String reply = chat.receiveReply(120);
                System.out.println(reply != null ? "Server: " + reply : "(Server khong phan hoi trong 120 giay)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
