package bai7;

import java.util.List;

public class Triangle extends AbstractPolygon {

    public Triangle(List<Point> vertices) {
        super(vertices, 3);
    }

    @Override
    public String getName() {
        return "Tam giác";
    }

    // Công thức Heron
    @Override
    public double area() {
        double a = vertices.get(0).distanceTo(vertices.get(1));
        double b = vertices.get(1).distanceTo(vertices.get(2));
        double c = vertices.get(2).distanceTo(vertices.get(0));
        double p = (a + b + c) / 2;
        return Math.sqrt(Math.max(0, p * (p - a) * (p - b) * (p - c)));
    }

    @Override
    public double perimeter() {
        return sidesPerimeter();
    }
}
