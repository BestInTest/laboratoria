import java.util.Locale;

public class Ellipse extends Shape {

    private Point p1;
    private double r1, r2;

    public Ellipse(Point p, double r1, double r2, Style style) {
        super(style);
        this.p1 = p;
        this.r1 = r1;
        this.r2 = r2;

        if (style != null) {
            this.style = style;
        } else {
            this.style = new Style("transparent", "black", 1);
        }
    }

    @Override
    public String toSvg() {
        return String.format(Locale.US,
                "<ellipse cx=\"%.2f\" cy=\"%.2f\" rx=\"%.2f\" ry=\"%.2f\" %s",
                p1.getX(), p1.getY(), r1, r2, style.toSvg());
    }
}
