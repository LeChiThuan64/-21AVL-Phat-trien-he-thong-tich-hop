public record Point(double x, double y) {

    public double distanceTo(Point p) {
        return Math.hypot(x - p.x, y - p.y);
    }

    public boolean nearlyEquals(Point p) {
        return Math.abs(x - p.x) < 1e-9 && Math.abs(y - p.y) < 1e-9;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
