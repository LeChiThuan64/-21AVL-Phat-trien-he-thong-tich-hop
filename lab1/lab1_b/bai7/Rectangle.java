import java.util.List;

public class Rectangle extends Quadrilateral {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        super(build(width, height));
        this.width = width;
        this.height = height;
    }

    private static List<Point> build(double w, double h) {
        if (w <= 0 || h <= 0) {
            throw new IllegalArgumentException("Chiều dài và chiều rộng phải lớn hơn 0");
        }
        return List.of(new Point(0, 0), new Point(w, 0), new Point(w, h), new Point(0, h));
    }

    @Override
    public String getName() {
        return "Hình chữ nhật";
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }
}
