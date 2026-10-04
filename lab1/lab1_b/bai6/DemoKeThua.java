package bai6;

public class DemoKeThua {
    public static void main(String[] args) {
        C c = new C();

        System.out.println("Trước khi đặt:");
        in(c);

        c.datXCuaA(100);

        System.out.println("\nSau khi gọi c.datXCuaA(100):");
        in(c);
        System.out.println("\n=> Chỉ x của A đổi thành 100, x của B và x của C không đổi.");
    }

    private static void in(C c) {
        System.out.println("x của A = " + c.layXCuaA());
        System.out.println("x của B = " + c.layXCuaB());
        System.out.println("x của C = " + c.layXCuaC());
    }
}
