public class ShapeDecorator implements Shape {

    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public BoundingBox boundingBox() {
        return null;
    }

    @Override
    public String toSvg(String s) {
        return decoratedShape.toSvg(s);
    }
}
