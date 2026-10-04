package bai7;

import java.util.List;

public class Pentagon extends AbstractPolygon {

    public Pentagon(List<Point> vertices) {
        super(vertices, 5);
    }

    @Override
    public String getName() {
        return "Ngũ giác";
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
