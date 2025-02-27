public class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public String toSvg() {
        return "r=\"" + 5 + "\" cx=\"" + x + "\" cy=\"" + y + "\" fill=\"red\"";
    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public Point translated() {
        return new Point(this.x, this.y);
    }
}
