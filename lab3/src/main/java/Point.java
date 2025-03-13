public class Point {
    private double x, y;

    Point() {
        this.x = 0.0;
        this.y = 0.0;
    }

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
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
