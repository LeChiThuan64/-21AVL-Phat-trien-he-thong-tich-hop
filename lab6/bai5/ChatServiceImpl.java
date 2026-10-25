import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    // Hang doi chua cac tin phan hoi do nguoi van hanh server go vao
    private final BlockingQueue<String> replies = new LinkedBlockingQueue<>();

    public ChatServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.println("\n[Client]: " + message);
        System.out.print("[Server] Tra loi: ");
    }

    @Override
    public String receiveReply(int timeoutSec) throws RemoteException {
        try {
            return replies.poll(timeoutSec, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    /** Server goi ham nay khi nguoi dung go phan hoi */
    public void pushReply(String reply) {
        replies.offer(reply);
    }
}
