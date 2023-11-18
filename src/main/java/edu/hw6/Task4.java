package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32C;
import java.util.zip.CheckedOutputStream;

public class Task4 {
    public static void main(String[] args) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Path.of("src/main/resources/diskmap_storage.txt"));) {
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32C());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                bufferedOutputStream,
                StandardCharsets.US_ASCII
            );
            PrintWriter writer = new PrintWriter(outputStreamWriter, true);
            writer.println("Programming is learned by writing programs. ― Brian Kernighan");
        }

    }
}
