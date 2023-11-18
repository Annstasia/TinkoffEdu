package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;


public interface AbstractFilter extends DirectoryStream.Filter<Path>{
    default AbstractFilter and(AbstractFilter other) {
        return (path)->this.accept(path)&&other.accept(path);
    };
//    default Predicate<Path> and(Predicate<? super Path> other) {
//        return this;
////        Objects.requireNonNull(other);
////        return (t) -> {
////            try {
////                return accept(t) && other.test(t);
////            } catch (IOException e) {
////                throw new RuntimeException(e);
////            }
////        };
//    }
}



//public class AbstractFilter implements DirectoryStream.Filter<Path> {
//    DirectoryStream.Filter<Path> filter;
//    public AbstractFilter(DirectoryStream.Filter<Path> filter) {
//        this.filter = filter;
//    }
//
//    public AbstractFilter and(DirectoryStream.Filter<Path> filterToAdd) {
//        filter.
//        filter = filter.and(filterToAdd);
//        return this;
//    }
//
//    @Override
//    public boolean accept(Path entry) {
//        return filter.test(entry);
//    }
//
//    public static void main(String[] args) {
//        final AbstractFilter regularFile = new AbstractFilter(Files::isRegularFile);
//        final AbstractFilter readable = new AbstractFilter(Files::isReadable);
//
//
//        DirectoryStream.Filter<Path> filter = regularFile
//            .and(readable)
//            .and(largerThan(100_000))
//            .and(magicNumber(0x89, 'P', 'N', 'G'))
//            .and(globMatches("*.png"))
//            .and(regexContains("[-]"));
//
//        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
//            entries.forEach(System.out::println);
//        }
//    }
//}


