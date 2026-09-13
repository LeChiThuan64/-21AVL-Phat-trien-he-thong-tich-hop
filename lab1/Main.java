public class Main {
    public static void main(String[] args) {
        SuTu leo = new SuTu("Leo", 300, 5);
        Ran boa = new Ran("Boa", 50, 5);
        Khi george = new Khi("George", 150, "chuoi");

        leo.hienThi();
        boa.hienThi();
        george.hienThi();
    }
}

class DongVat {
    protected String ten;
    protected double canNang;

    public DongVat(String ten, double canNang) {
        this.ten = ten;
        this.canNang = canNang;
    }
}

class SuTu extends DongVat {
    private double luongAn;

    public SuTu(String ten, double canNang, double luongAn) {
        super(ten, canNang);
        this.luongAn = luongAn;
    }

    public void hienThi() {
        System.out.println("Su tu " + ten + " nang " + canNang + " can va an " + luongAn + " can thit moi ngay.");
    }
}

class Ran extends DongVat {
    private double chieuDai;

    public Ran(String ten, double canNang, double chieuDai) {
        super(ten, canNang);
        this.chieuDai = chieuDai;
    }

    public void hienThi() {
        System.out.println("Con ran " + ten + " nang " + canNang + " can va dai " + chieuDai + " met.");
    }
}

class Khi extends DongVat {
    private String thucAnYeuThich;

    public Khi(String ten, double canNang, String thucAnYeuThich) {
        super(ten, canNang);
        this.thucAnYeuThich = thucAnYeuThich;
    }

    public void hienThi() {
        System.out.println("Con khi " + ten + " nang " + canNang + " can va thich an " + thucAnYeuThich + ".");
    }
}