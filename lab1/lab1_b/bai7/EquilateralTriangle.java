public class EquilateralTriangle extends IsoscelesTriangle {
    private final double side;

    public EquilateralTriangle(double side) {
        super(side, side);
        this.side = side;
    }

    @Override
    public String getName() {
        return "Tam giác đều";
    }

    @Override
    public double area() {
        return Math.sqrt(3) / 4 * side * side;
    }

    @Override
    public double perimeter() {
        return 3 * side;
    }
}
