package edu.project4.generators;

import edu.project4.objects.Point;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalGenerator implements PointGenerator {
    @Override
    public Point generate() {
        return new Point(
            ThreadLocalRandom.current().nextDouble(-1, 1),
            ThreadLocalRandom.current().nextDouble(-1, 1)
        );
    }
}
