public class Style {

    public String fillColor;
    public String strokeColor;
    public double strokeWidth;

    public Style(String fillColor, String strokeColor, double strokeWidth) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    //apisz publiczną metodę toSvg() zwracającą napis, będący opcją style, którą można umieścić np. w tagu <polygon>.
    public String toSvg() {
        return String.format("style=\"fill:%s;stroke:%s;stroke-width:%s\" />", fillColor, strokeColor, strokeWidth);
    }
}
