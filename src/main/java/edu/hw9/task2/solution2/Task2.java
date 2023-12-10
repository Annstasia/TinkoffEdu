package edu.hw9.task2.solution2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

// второе решение. Отличается тем, что в RecursiveTask не передается ссылка хранилище ответа,
// а в каждой итерации происходит merge найденных в потомках решений.
// +не нужна лишняя синхронизация
// -чтобы за O(1) делать merge нужно написать свою структуру
// В тестах есть сравнение производительности. Это решение действительно быстрее
public class Task2 {
    static final int DEFAULT_LARGE = 1000;

    private Task2() {
    }

    public static List<Path> findLargeDirectories(Path source) {
        return findLargeDirectories(source, filesCount -> filesCount > DEFAULT_LARGE);
    }

    public static List<Path> findLargeDirectories(Path source, Predicate<Integer> barrier) {
        FindDirectoriesByFilesCount searcher = new FindDirectoriesByFilesCount(source, barrier);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            SpeedLinkedListMVP<Path> ans = forkJoinPool.invoke(searcher).allBigDirectories();
            return ans.toList();
        }
    }

    public static List<Path> findFilesByPredicate(Path source, Predicate<Path> predicate) {
        FindFilesByPredicate searcher = new FindFilesByPredicate(source, predicate);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(searcher).toList();
        }
    }
}
