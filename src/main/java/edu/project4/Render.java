package edu.project4;

import edu.project4.objects.FractalImage;
import edu.project4.objects.ProcessInfo;
import edu.project4.objects.WorldInfo;

public interface Render {
    void generate(
        WorldInfo worldInfo,
        ProcessInfo processInfo
    );
//    void generate(
//        FractalImage image, Rect world, PointGenerator generator,
//        VariationStorage variations, int samples, int iterPerSample, int symmetry
//    );
//

    void gammaLog(FractalImage image, double gamma);

    void reduce(FractalImage original, FractalImage reduced);

    //    default void render(
//        FractalImage canvas, Rect world, PointGenerator pointGenerator,
//        VariationStorage variations, int samples, int iterationsPerSample,
//        int symmetry
//    ) {
//        render(canvas, world, pointGenerator, variations, samples, iterationsPerSample, symmetry, 5, 2.2);
//    }
    void render(WorldInfo worldInfo, ProcessInfo processInfo);

//    void render(
//        FractalImage canvas, Rect world, PointGenerator pointGenerator,
//        VariationStorage variations, int samples, int iterationsPerSample,
//        int symmetry, int filterScale, double gamma
//    );
}
