import java.util.Arrays;

public class Polygon extends Shape {

    private Point[] points;

    public Polygon(Point[] points) {
        super(new Style("transparent", "black", 1));
        //this.points = points;
        this.points = Arrays.copyOf(points, points.length);

        //Głęboka kopia
        //this.points = new Point[points.length];
        //for (int i = 0; i < points.length; i++) {
        //    this.points[i] = new Point(points[i].getX(), points[i].getY());
        //}
    }

    // Konstruktor kopiujący
    public Polygon(Polygon polygon) {
        super(polygon.style);
        //płytka kopia
        //this.points = polygon.points;
        //this.points = Arrays.copyOf(polygon.points, polygon.points.length);

        this.points = new Point[polygon.points.length];
        for (int i = 0; i < polygon.points.length; i++) {
            this.points[i] = new Point(polygon.points[i].getX(), polygon.points[i].getY());
        }
        style = polygon.style; // tutaj przydałoby się dorobić głęboką kopię
    }

    public Polygon(Point[] points, Style style) {
        super(style);
        this.points = Arrays.copyOf(points, points.length);
        if (style != null) {
            this.style = style;
        } else {
            this.style = new Style("transparent", "black", 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Polygon: [");
        for (Point p : points) {
            sb.append(p.toString()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        return sb.toString();
    }

    public String toSvg() {
        StringBuilder sb = new StringBuilder();
        sb.append("<polygon points=\"");
        for (Point p : points) {
            sb.append(p.getX()).append(",").append(p.getY()).append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());

        sb.append("\" ").append(style.toSvg());

        return sb.toString();
    }

    public Polygon copy() {
        return new Polygon(this);
    }

    public BoundingBox boundingBox() {
        double minX = points[0].getX();
        double minY = points[0].getY();
        double maxX = points[0].getX();
        double maxY = points[0].getY();
        for (Point p : points) {
            if (p.getX() < minX) {
                minX = p.getX();
            }
            if (p.getX() > maxX) {
                maxX = p.getX();
            }
            if (p.getY() < minY) {
                minY = p.getY();
            }
            if (p.getY() > maxY) {
                maxY = p.getY();
            }
        }
        Point leftTop = new Point(minX, maxY);
        double width = maxX - minX;
        double height = maxY - minY;
        return new BoundingBox(minX, minY, leftTop, width, height);
    }

    public static Polygon square(Segment line, Style style) {
        Segment[] lines;
        Point p1, p2, p3, p4;
        p1 = line.getStart();
        p3 = line.getEnd();
        Point centerPoint = new Point(p3.getX() + p1.getX(), p3.getY() + p1.getY());
        lines = Segment.perpendicular(line, centerPoint, line.length() / 2);
        return null; //todo
    }
}
