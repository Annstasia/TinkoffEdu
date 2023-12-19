package edu.hw6.task3;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class Task3 {
    private Task3() {
    }

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;
    public static final AbstractFilter WRITABLE = Files::isWritable;

    public static AbstractFilter largerThan(int size) {
        return (path) -> Files.size(path) > size;
    }

    public static AbstractFilter magicNumber(int... startBytes) {
        return (path) -> {
            try (FileChannel inChannel = FileChannel.open(path)) {
                ByteBuffer buffer = ByteBuffer.allocate(startBytes.length);
                if (inChannel.read(buffer) == startBytes.length) {
                    buffer.flip();
                    for (int startByte : startBytes) {
                        if ((byte) startByte != buffer.get()) {
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
        return (path) -> FileSystems
            .getDefault()
            .getPathMatcher("glob:" + globString)
            .matches(path.getFileName());
    }

    public static AbstractFilter regexContains(String regex) {
        return (path) -> Pattern.compile(regex)
                                .matcher(path.getFileName().toString()).find();
    }
}
