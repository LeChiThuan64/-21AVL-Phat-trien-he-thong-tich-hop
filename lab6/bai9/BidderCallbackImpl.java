import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BidderCallbackImpl extends UnicastRemoteObject implements BidderCallback {
    public BidderCallbackImpl() throws RemoteException {
        super();
    }

    @Override
    public void notifyMessage(String message) throws RemoteException {
        System.out.println("\n>>> THONG BAO: " + message);
        System.out.print("Chon: ");
    }
}
