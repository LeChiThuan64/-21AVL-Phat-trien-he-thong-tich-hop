public class DemoHinh {
    public static void main(String[] args) {
        HCN h1 = new HCN();
        HCN h2 = new HCN(5, 3);
        HVuong v1 = new HVuong();
        HVuong v2 = new HVuong(4);
        HTG t1 = new HTG();
        HTG t2 = new HTG(3, 4, 5);

        System.out.println("--- Hình chữ nhật ---");
        h1.xuat();
        h2.xuat();

        System.out.println("--- Hình vuông ---");
        v1.xuat();
        v2.xuat();

        System.out.println("--- Tam giác ---");
        t1.xuat();
        t2.xuat();

        System.out.println("--- Thử các phương thức set ---");
        h1.setDai(10);
        h1.setRong(2);
        h1.xuat();
        v1.setCanh(6);
        v1.xuat();
        t1.setA(1.5);
        t1.xuat();

        System.out.println("--- Thử dữ liệu sai ---");
        try {
            new HTG(1, 2, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        try {
            h2.setDai(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
