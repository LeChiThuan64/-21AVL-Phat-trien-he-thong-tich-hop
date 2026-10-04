package bai7;

public class Square extends Rectangle {
    private final double side;

    public Square(double side) {
        super(side, side);
        this.side = side;
    }

    @Override
    public String getName() {
        return "Hình vuông";
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public double perimeter() {
        return 4 * side;
    }
}
