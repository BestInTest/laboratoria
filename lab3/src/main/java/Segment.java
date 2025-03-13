public class Segment {

    public Point start;
    public Point end;

//    public Segment(Point start, Point end) {
//        this.start = start;
//        this.end = end;
//    }

    //konstruktor kopiujący
    public Segment(Point start, Point end){
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    //Metoda zwracająca długość odcinka
    public double length() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    // Metoda statyczna zwracająca najdłuższy segment z tablicy
    public static Segment findLongestSegment(Segment[] segments) {
        if (segments == null || segments.length == 0) {
            return null;
        }

        Segment longest = segments[0];
        for (Segment segment : segments) {
            if (segment.length() > longest.length()) {
                longest = segment;
            }
        }
        return longest;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Segment: [" + start + ", " + end + "]";
    }

    //metoda potrzebna do zad 2 z lab 3 wyznaczająca odcinki prostopadłe do segment, wychodzące z punktu point, o długoci odcinka segment
    public static Segment[] perpendicular(Segment segment, Point point) {
        // Obliczanie współczynników nachylenia segmentu
        double x1 = segment.start.getX();
        double y1 = segment.start.getY();
        double x2 = segment.end.getX();
        double y2 = segment.end.getY();

        // Długość segmentu
        double r = segment.length();

        // Obliczanie współczynnika nachylenia segmentu (a)
        double dx = x2 - x1;
        double dy = y2 - y1;
        double a = dy / dx; // Jeśli dx == 0, segment jest pionowy i musimy to obsłużyć inaczej

        // Obliczanie współczynnika nachylenia prostej prostopadłej (a_perpendicular)
        double aPerpendicular = -1 / a;

        // Wyznaczanie b w równaniu prostej prostopadłej: y - y0 = a_perpendicular * (x - x0)
        double b = point.getY() - aPerpendicular * point.getX();

        // Obliczanie punktów przecięcia
        // Ponieważ prosta jest prostopadła, możemy wyliczyć punkt w odległości r na prawo i lewo od punktu

        // Obliczanie zmiennych potrzebnych do uzyskania punktów przecięcia
        double deltaX = r / Math.sqrt(1 + aPerpendicular * aPerpendicular);
        double deltaY = aPerpendicular * deltaX;

        // Pierwszy punkt przecięcia
        double x1_intersect = point.getX() + deltaX;
        double y1_intersect = point.getY() + deltaY;

        // Drugi punkt przecięcia
        double x2_intersect = point.getX() - deltaX;
        double y2_intersect = point.getY() - deltaY;

        // Zwrócenie dwóch segmentów
        return new Segment[]{
                new Segment(point, new Point(x1_intersect, y1_intersect)),
                new Segment(point, new Point(x2_intersect, y2_intersect))
        };
    }

    public static Segment[] perpendicular(Segment segment, Point point, double len) {
        Segment[] lines = Segment.perpendicular(segment, point);
        double scale = len / segment.length();
        for (Segment seg : lines) {
            double distanceX = seg.end.getX() - point.getX();
            double distanceY = seg.end.getY() - point.getY();
            seg.end.setX(distanceX);
            seg.end.setY(distanceY);
        }
        return null; // todo
    }
}
