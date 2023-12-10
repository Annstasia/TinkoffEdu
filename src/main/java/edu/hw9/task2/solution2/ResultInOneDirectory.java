package edu.hw9.task2.solution2;

import java.nio.file.Path;

public record ResultInOneDirectory(int filesInDirectory, SpeedLinkedListMVP<Path> allBigDirectories) {
}
