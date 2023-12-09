package edu.project4.objects;

import edu.project4.generators.PointGenerator;
import edu.project4.generators.VariationStorage;

public record WorldInfo(FractalImage canvas, Rect world, PointGenerator generator,
                        VariationStorage variations) {
}
