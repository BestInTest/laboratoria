/*
  Zdefiniuj rekord BoundingBox składający się z czterech liczb zmiennoprzecinkowych:
  położenia x i y lewego górnego punktu prostokątnego obrysu wielokąta, a także szerokości oraz wysokości tego obrysu.
  W klasie Polygon napisz metodę boundingBox(), która zwróci obiekt BoundingBox opisujący dany wielokąt.
*/
public record BoundingBox(double x, double y, Point leftTop, double width, double height) {
}
