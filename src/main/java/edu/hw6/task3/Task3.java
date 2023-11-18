package edu.hw6.task3;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Task3 {
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;
    public static final AbstractFilter writable = Files::isWritable;
    public static AbstractFilter largerThan(int size) {
        return (path)->Files.size(path) > size;
    }

    public static AbstractFilter magicNumber(int... startBytes) {
        return (path)-> {
            try (FileChannel inChannel = FileChannel.open(path)) {
                ByteBuffer buffer = ByteBuffer.allocate(startBytes.length);
                if (inChannel.read(buffer) == startBytes.length) {
                    buffer.flip();
                    for (int startByte : startBytes) {
                        if ((byte)startByte != buffer.get()) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        };
    }

    public static AbstractFilter globMatches(String globString) {
        return (path)-> FileSystems
            .getDefault()
            .getPathMatcher("glob:" + globString)
            .matches(path.getFileName());
    }

    public static AbstractFilter regexContains(String regex) {
        return (path) -> Pattern.compile(regex)
                                .matcher(path.getFileName().toString()).find();
    }
    public static DirectoryStream.Filter<Path> filter = regularFile
        .and(readable)
        .and(largerThan(1))
        .and(magicNumber(0x89, 'P', 'N', 'G'))
        .and(globMatches("*.png"))
        .and(regexContains("[-]"));
}
