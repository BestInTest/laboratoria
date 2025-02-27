public class Segment {

    private final Point p1, p2;

    Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double length() {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static Segment longestSegment(Segment[] segments) {
        Segment longest = segments[0];
        for (Segment seg : segments) {
            if (seg.length() > longest.length()) {
                longest = seg;
            }
        }
        return longest;
    }
}
