import java.util.List;

public class Quadrilateral extends AbstractPolygon {

    public Quadrilateral(List<Point> vertices) {
        super(vertices, 4);
    }

    @Override
    public String getName() {
        return "Tứ giác";
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
