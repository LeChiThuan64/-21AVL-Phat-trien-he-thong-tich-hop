import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractPolygon implements Polygon {
    protected final List<Point> vertices;

    protected AbstractPolygon(List<Point> vertices, int soDinh) {
        if (vertices == null || vertices.size() != soDinh) {
            throw new IllegalArgumentException("Đa giác này cần đúng " + soDinh + " đỉnh");
        }
        this.vertices = new ArrayList<>(vertices);
        if (shoelaceArea() < 1e-9) {
            throw new IllegalArgumentException("Các đỉnh thẳng hàng hoặc trùng nhau (diện tích bằng 0)");
        }
    }

    public abstract String getName();

    public List<Point> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    // Diện tích theo công thức Shoelace (Gauss) từ tọa độ các đỉnh
    protected final double shoelaceArea() {
        double s = 0;
        int n = vertices.size();
        for (int i = 0; i < n; i++) {
            Point p = vertices.get(i);
            Point q = vertices.get((i + 1) % n);
            s += p.x() * q.y() - q.x() * p.y();
        }
        return Math.abs(s) / 2;
    }

    // Chu vi = tổng độ dài các cạnh
    protected final double sidesPerimeter() {
        double s = 0;
        int n = vertices.size();
        for (int i = 0; i < n; i++) {
            s += vertices.get(i).distanceTo(vertices.get((i + 1) % n));
        }
        return s;
    }

    // Hai đa giác "giống nhau" khi có cùng số đỉnh và cùng dãy đỉnh
    // (cho phép bắt đầu từ đỉnh bất kỳ và duyệt theo chiều xuôi hoặc ngược)
    public boolean sameAs(AbstractPolygon o) {
        if (o == null || vertices.size() != o.vertices.size()) {
            return false;
        }
        int n = vertices.size();
        for (int shift = 0; shift < n; shift++) {
            boolean xuoi = true;
            boolean nguoc = true;
            for (int i = 0; i < n; i++) {
                if (!vertices.get(i).nearlyEquals(o.vertices.get((i + shift) % n))) {
                    xuoi = false;
                }
                if (!vertices.get(i).nearlyEquals(o.vertices.get(((shift - i) % n + n) % n))) {
                    nguoc = false;
                }
            }
            if (xuoi || nguoc) {
                return true;
            }
        }
        return false;
    }
}
