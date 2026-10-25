import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AuctionService extends Remote {
    /** Dang ky nguoi dung + callback de nhan thong bao realtime */
    void register(String username, BidderCallback callback) throws RemoteException;

    /** Seller: them san pham, tra ve id san pham */
    int addItem(String seller, String itemName, double startPrice) throws RemoteException;

    /** Bidder: xem danh sach san pham dang dau gia */
    List<AuctionItem> listItems() throws RemoteException;

    /** Bidder: dat gia, thanh cong khi amount > gia hien tai */
    boolean placeBid(int itemId, String bidder, double amount) throws RemoteException;
}
