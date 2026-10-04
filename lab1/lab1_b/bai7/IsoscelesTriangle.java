package bai7;

import java.util.List;

public class IsoscelesTriangle extends Triangle {
    private final double base;  // cạnh đáy
    private final double leg;   // cạnh bên

    public IsoscelesTriangle(double base, double leg) {
        super(build(base, leg));
        this.base = base;
        this.leg = leg;
    }

    private static List<Point> build(double base, double leg) {
        if (base <= 0 || leg <= 0 || 2 * leg <= base) {
            throw new IllegalArgumentException(
                    "Cần đáy > 0, cạnh bên > 0 và 2 × cạnh bên > đáy");
        }
        double h = Math.sqrt(leg * leg - base * base / 4);
        return List.of(new Point(0, 0), new Point(base, 0), new Point(base / 2, h));
    }

    @Override
    public String getName() {
        return "Tam giác cân";
    }

    @Override
    public double area() {
        return base * Math.sqrt(leg * leg - base * base / 4) / 2;
    }

    @Override
    public double perimeter() {
        return base + 2 * leg;
    }
}
