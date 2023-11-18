package edu.hw6.task1;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DiskMap extends HashMap<String, String> {
    Path filePath;
    public DiskMap() {
        this(Path.of("src/main/resources/diskmap_storage.txt"));
    }
    public DiskMap(Path filePath) {
        this.filePath = filePath;
        try (FileChannel inChannel = FileChannel.open(filePath)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder builder = new StringBuilder();
            String currentKey = "";
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    char currentChar = (char) buffer.get();
                    if (currentChar == '\n') {
                        this.put(currentKey, new String(builder));
                        builder.setLength(0);
                    } else if (currentChar == ':') {
                        currentKey = new String(builder);
                        builder.setLength(0);
                    } else {
                        builder.append(currentChar);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        save(this.filePath);
    }
    public void save(Path filePath) {
        try (FileChannel outChannel = FileChannel.open(filePath,
                                                       StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            for (Entry<String, String> kv : this.entrySet()) {
                for (char c : kv.getKey().toCharArray()) {
                    buffer.put((byte) c);
                }
                buffer.put((byte) ':');
                for (char c : kv.getValue().toCharArray()) {
                    buffer.put((byte) c);
                }
                buffer.put((byte) '\n');
            }
            buffer.flip();
            outChannel.truncate(0);
            outChannel.write(buffer, 0);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
