import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AuctionServiceImpl extends UnicastRemoteObject implements AuctionService {
    private final Map<Integer, AuctionItem> items = new LinkedHashMap<>();
    private final Map<String, BidderCallback> callbacks = new LinkedHashMap<>();
    private int nextId = 1;

    public AuctionServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void register(String username, BidderCallback callback) throws RemoteException {
        callbacks.put(username, callback);
        System.out.println("[Server] Nguoi dung '" + username + "' da dang nhap.");
    }

    @Override
    public synchronized int addItem(String seller, String itemName, double startPrice) throws RemoteException {
        if (startPrice <= 0) {
            throw new RemoteException("Gia khoi diem phai lon hon 0");
        }
        int id = nextId++;
        items.put(id, new AuctionItem(id, itemName, seller, startPrice));
        System.out.printf("[Server] %s them san pham #%d '%s' gia khoi diem %,.0f%n", seller, id, itemName, startPrice);
        broadcast("San pham moi #" + id + " '" + itemName + "' (khoi diem " + String.format("%,.0f", startPrice) + ") cua " + seller, seller);
        return id;
    }

    @Override
    public synchronized List<AuctionItem> listItems() throws RemoteException {
        List<AuctionItem> list = new ArrayList<>();
        for (AuctionItem it : items.values()) {
            list.add(it.copy());
        }
        return list;
    }

    @Override
    public synchronized boolean placeBid(int itemId, String bidder, double amount) throws RemoteException {
        AuctionItem item = items.get(itemId);
        if (item == null) return false;
        if (item.getSeller().equals(bidder)) return false;     // nguoi ban khong duoc tu dat gia
        if (amount <= item.getCurrentPrice()) return false;     // phai cao hon gia hien tai

        String previous = item.getHighestBidder();
        item.setCurrentPrice(amount);
        item.setHighestBidder(bidder);
        System.out.printf("[Server] %s dat %,.0f cho #%d '%s'%n", bidder, amount, itemId, item.getName());

        // Thong bao nguoi bi vuot gia
        if (previous != null && !previous.equals(bidder)) {
            sendTo(previous, String.format("Ban da bi vuot gia o san pham #%d '%s'! %s dat %,.0f",
                    itemId, item.getName(), bidder, amount));
        }
        // Thong bao nguoi ban
        sendTo(item.getSeller(), String.format("San pham #%d '%s' cua ban co gia moi %,.0f tu %s",
                itemId, item.getName(), amount, bidder));
        return true;
    }

    private void sendTo(String user, String msg) {
        BidderCallback cb = callbacks.get(user);
        if (cb == null) return;
        try {
            cb.notifyMessage(msg);
        } catch (RemoteException e) {
            callbacks.remove(user); // client da thoat
        }
    }

    private void broadcast(String msg, String except) {
        for (String user : new ArrayList<>(callbacks.keySet())) {
            if (!user.equals(except)) sendTo(user, msg);
        }
    }
}
