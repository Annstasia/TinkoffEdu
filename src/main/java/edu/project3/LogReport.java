package edu.project3;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record LogReport(List<String> files, LocalDateTime start, LocalDateTime end, int count, long averageBytes,
                        Map<String, Integer> resourceCount, List<CodeInfo> codeInfos) {
}
