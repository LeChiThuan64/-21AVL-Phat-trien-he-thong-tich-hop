import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatService extends Remote {
    /** Client gui tin nhan toi server */
    void sendMessage(String message) throws RemoteException;

    /** Client lay tin phan hoi cua server (cho toi da timeoutSec giay, het gio tra ve null) */
    String receiveReply(int timeoutSec) throws RemoteException;
}
