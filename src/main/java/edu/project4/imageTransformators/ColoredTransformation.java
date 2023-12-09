package edu.project4.imageTransformators;

import edu.project4.objects.Color;
import edu.project4.objects.Point;

public class ColoredTransformation implements Transformation {
    Transformation transformation;
    Color color;

    public ColoredTransformation(Transformation transformation) {
        this(transformation, new Color(1, 1, 1));
    }

    public ColoredTransformation(Transformation transformation, Color color) {
        this.transformation = transformation;
        this.color = color;
    }

    @Override
    public Point apply(Point point) {
        return transformation.apply(point);
    }

    public Color getColor() {
        return color;
    }
}
