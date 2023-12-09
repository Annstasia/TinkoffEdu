package edu.project4.imageTransformators;

import edu.project4.objects.Point;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
    default Transformation compose(Transformation before) {
        return point -> this.apply(before.apply(point));
    }
}
