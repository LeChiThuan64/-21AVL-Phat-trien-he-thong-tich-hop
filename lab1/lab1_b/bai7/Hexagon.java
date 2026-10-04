import java.util.List;

public class Hexagon extends AbstractPolygon {

    public Hexagon(List<Point> vertices) {
        super(vertices, 6);
    }

    @Override
    public String getName() {
        return "Lục giác";
    }

    @Override
    public double area() {
        return shoelaceArea();
    }

    @Override
    public double perimeter() {
        return sidesPerimeter();
    }
}
