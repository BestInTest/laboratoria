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
        Segment longest = Segment.longestSegment(segments);
        System.out.println("longest segment: " + longest.length());
    }
}
