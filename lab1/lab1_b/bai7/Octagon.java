package bai7;

import java.util.List;

public class Octagon extends AbstractPolygon {

    public Octagon(List<Point> vertices) {
        super(vertices, 8);
    }

    @Override
    public String getName() {
        return "Bát giác";
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
