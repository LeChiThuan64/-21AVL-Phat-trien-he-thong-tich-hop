package bai1;
public class HCN {
    private double dai;
    private double rong;

    public HCN() {
        this.dai = 1;
        this.rong = 1;
    }

    public HCN(double dai, double rong) {
        this.dai = kiemTra(dai);
        this.rong = kiemTra(rong);
    }

    private static double kiemTra(double v) {
        if (v <= 0) {
            throw new IllegalArgumentException("Kích thước phải lớn hơn 0");
        }
        return v;
    }

    public double getDai() { return dai; }
    public double getRong() { return rong; }

    public void setDai(double dai) { this.dai = kiemTra(dai); }
    public void setRong(double rong) { this.rong = kiemTra(rong); }

    public double chuVi() {
        return 2 * (dai + rong);
    }

    public double dienTich() {
        return dai * rong;
    }

    public void xuat() {
        System.out.printf("Hình chữ nhật: dài=%.2f, rộng=%.2f, chu vi=%.2f, diện tích=%.2f%n",
                dai, rong, chuVi(), dienTich());
    }
}
