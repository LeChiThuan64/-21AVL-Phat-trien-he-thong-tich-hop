import java.rmi.Remote;
import java.rmi.RemoteException;

/** Interface callback do CLIENT cai dat de SERVER goi nguoc lai khi can thong bao */
public interface BidderCallback extends Remote {
    void notifyMessage(String message) throws RemoteException;
}
