import java.io.FileWriter;
import java.io.IOException;

public class SvgScene {

    private int iterator = 0;
    private final int MAX_POLYGONS = 3;
    private final Polygon[] polygons = new Polygon[MAX_POLYGONS];

    public SvgScene() {

    }

    public void addPolygon(Polygon polygon) {
        /*
        if (iterator >= MAX_POLYGONS) {
            iterator = 0;
        }
        polygons[iterator] = polygon;
        iterator++;
        */

        //Taki sam efekt jako powyżej
        polygons[iterator] = polygon;
        iterator = (iterator + 1) % MAX_POLYGONS;
    }

    public void print() {
        for (Polygon polygon : polygons) {
            System.out.println(polygon);
        }
    }

    //<polygon points="100,10 150,190 50,190" style="fill:lime;stroke:purple;stroke-width:3" />
    public String toSvg() {
        /*
        StringBuilder sb = new StringBuilder();
        for (Polygon polygon : polygons) {
            sb.append(polygon.toSvg()).append("\n");
        }
        return sb.toString();
         */
        //idk czy dobrze
        StringBuilder sb = new StringBuilder();
        sb.append("<svg width=\"200\" height=\"200\">");
        for (Polygon polygon : polygons) {
            if (polygon != null) {
                sb.append(polygon.toSvg());
            }
        }
        sb.append("</svg>");

        return sb.toString();
    }

    public void save(String file) {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(toSvg());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
