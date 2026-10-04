package bai6;

public class C extends B {
    protected int x = 3;   // che (hide) biến x của B

    // Truy cập và đặt x của A: ép kiểu this về A.
    // Truy cập BIẾN được quyết định theo kiểu khai báo (kiểu tĩnh) chứ không theo đối tượng thật,
    // nên ((A) this).x chính là x được khai báo trong A.
    public void datXCuaA(int giaTri) {
        ((A) this).x = giaTri;
    }

    public int layXCuaA() {
        return ((A) this).x;
    }

    // Với x của B thì dùng super.x
    public int layXCuaB() {
        return super.x;
    }

    public int layXCuaC() {
        return this.x;
    }
}
