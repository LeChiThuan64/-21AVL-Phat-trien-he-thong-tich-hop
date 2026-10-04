package bai1;
public class HVuong extends HCN {

    public HVuong() {
        super(1, 1);
    }

    public HVuong(double canh) {
        super(canh, canh);
    }

    public double getCanh() { return getDai(); }

    public void setCanh(double canh) {
        super.setDai(canh);
        super.setRong(canh);
    }

    // Hình vuông luôn có 2 cạnh bằng nhau nên đổi 1 cạnh là đổi cả hai
    @Override
    public void setDai(double dai) { setCanh(dai); }

    @Override
    public void setRong(double rong) { setCanh(rong); }

    @Override
    public void xuat() {
        System.out.printf("Hình vuông: cạnh=%.2f, chu vi=%.2f, diện tích=%.2f%n",
                getCanh(), chuVi(), dienTich());
    }
}
