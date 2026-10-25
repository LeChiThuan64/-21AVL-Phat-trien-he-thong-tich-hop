import java.io.Serializable;

/** Doi tuong duoc truyen qua RMI nen phai Serializable */
public class AuctionItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String name;
    private final String seller;
    private final double startPrice;
    private double currentPrice;
    private String highestBidder; // null neu chua co ai dat gia

    public AuctionItem(int id, String name, String seller, double startPrice) {
        this.id = id;
        this.name = name;
        this.seller = seller;
        this.startPrice = startPrice;
        this.currentPrice = startPrice;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSeller() { return seller; }
    public double getStartPrice() { return startPrice; }
    public double getCurrentPrice() { return currentPrice; }
    public String getHighestBidder() { return highestBidder; }

    public void setCurrentPrice(double p) { this.currentPrice = p; }
    public void setHighestBidder(String b) { this.highestBidder = b; }

    /** Tao ban sao de gui cho client (tranh lo doi tuong goc tren server) */
    public AuctionItem copy() {
        AuctionItem c = new AuctionItem(id, name, seller, startPrice);
        c.currentPrice = currentPrice;
        c.highestBidder = highestBidder;
        return c;
    }

    @Override
    public String toString() {
        return String.format("[#%d] %-20s | Nguoi ban: %-10s | Gia khoi diem: %,.0f | Gia cao nhat: %,.0f | Nguoi dan dau: %s",
                id, name, seller, startPrice, currentPrice,
                highestBidder == null ? "(chua co)" : highestBidder);
    }
}
