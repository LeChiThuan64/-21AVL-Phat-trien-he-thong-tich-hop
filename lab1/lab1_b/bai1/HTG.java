public class HTG {
    private double a;
    private double b;
    private double c;

    public HTG() {
        this.a = 1;
        this.b = 1;
        this.c = 1;
    }

    public HTG(double a, double b, double c) {
        kiemTra(a, b, c);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // 3 cạnh phải dương và thỏa bất đẳng thức tam giác
    private static void kiemTra(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Cạnh phải lớn hơn 0");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Ba cạnh không tạo thành tam giác");
        }
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }

    public void setA(double a) { kiemTra(a, b, c); this.a = a; }
    public void setB(double b) { kiemTra(a, b, c); this.b = b; }
    public void setC(double c) { kiemTra(a, b, c); this.c = c; }

    public double chuVi() {
        return a + b + c;
    }

    // Công thức Heron
    public double dienTich() {
        double p = chuVi() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public void xuat() {
        System.out.printf("Tam giác: a=%.2f, b=%.2f, c=%.2f, chu vi=%.2f, diện tích=%.2f%n",
                a, b, c, chuVi(), dienTich());
    }
}
