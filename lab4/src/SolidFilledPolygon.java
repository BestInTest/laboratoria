import java.util.Locale;

public class SolidFilledPolygon extends Polygon {

    private String color;

    public SolidFilledPolygon(Vec2[] points, String color) {
        super(points);
        this.color = color;
    }

    @Override
    public String toSvg(String s) {
        return super.toSvg(String.format(Locale.US,"fill=\"%s\" %s ", color, s));
    }

}
