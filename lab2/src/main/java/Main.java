public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        System.out.println(p1.toString());
        System.out.println(p1.toSvg());

        Point p2 = p1.translated();
        p2.translate(2, -1);
        System.out.println(p1);
        System.out.println(p2.toString());

        Segment seg = new Segment(p1, p2);
        System.out.println(seg.length());

        Segment[] segments = {
            new Segment(new Point(1, 2), new Point(2, 4)),
            new Segment(new Point(5, 3), new Point(1, 4)),
            new Segment(new Point(5, 4), new Point(5, 0))
        };

        for (Segment s : segments) {
            System.out.println(s.length());
        }
        Segment longest = Segment.findLongestSegment(segments);
        System.out.println("longest segment: " + longest.length());

        Point[] points = {p1, p2, new Point(5, 4)};
        Polygon polygon = new Polygon(points);
        System.out.println(polygon);
        System.out.println(polygon.toSvg());

        // Płytka kopia zmieni wynik
        p1.translate(-1, 1);
        System.out.println(polygon);

        // Głęboka nie zmieni
        polygon = new Polygon(polygon);
        p1.translate(-1, 1);
        System.out.println(polygon);

        System.out.println(" ");
        SvgScene scene = new SvgScene();
        scene.addPolygon(new Polygon(points).copy());
        points[0].translate(1, 1);
        scene.addPolygon(new Polygon(points).copy());
        points[0].translate(5, 5);
        scene.addPolygon(new Polygon(points).copy());
        scene.print();

        points[0].translate(1, 1);
        scene.addPolygon(new Polygon(points).copy());
        scene.print();
        System.out.println(scene.toSvg());
        scene.save("scene.svg");
    }
}
