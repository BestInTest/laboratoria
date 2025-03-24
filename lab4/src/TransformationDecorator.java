public class TransformationDecorator extends ShapeDecorator {

    private String transform;

    public TransformationDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    public static class Builder {

        private boolean isTranslated= false;
        private boolean isRotated = false;
        private boolean isScaled = false;

        private Vec2 translation;
        private float angle;
        private Vec2 center;
        private Vec2 scale;

        public TransformationDecorator build() {

            return new TransformationDecorator((Shape) this);
        }

        public Builder translate(Vec2 translation) {
            isTranslated = true;
            this.translation = translation;
            return this;
        }

        public Builder rotate(float angle, Vec2 center) {
            isRotated = true;
            this.angle = angle;
            this.center = center;
            return this;
        }

        public Builder scale(Vec2 scaleFactor) {
            isScaled = true;
            this.scale = scaleFactor;
            return this;
        }
    }
}
