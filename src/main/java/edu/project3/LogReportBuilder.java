package edu.project3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogReportBuilder {
    List<String> files = new ArrayList<>();
    LocalDateTime start = null;
    LocalDateTime end = null;
    int count = 0;
    long sumBytes = 0;
    Map<String, Integer> resourceCount = new HashMap<>();
    Map<Integer, CodeInfo> codeInfos = new HashMap<>();

    public LogReportBuilder() {
    }

    public void addFile(String name) {
        files.add(name);
    }

    public void addTime(LocalDateTime time) {
        if (start == null || start.isAfter(time)) {
            start = time;
        }
        if (end == null || end.isBefore(time)) {
            end = time;
        }
    }

    public void addCount(int count) {
        this.count += count;
    }

    public void addBytes(long bytes) {
        this.sumBytes += bytes;
    }

    public void addResource(String resource) {
        this.resourceCount.put(resource, this.resourceCount.getOrDefault(resource, 0) + 1);
    }

    public void addCode(int code) {
        this.codeInfos.put(code, new CodeInfo(
            code,
            this.codeInfos.getOrDefault(code, new CodeInfo(code, 0, "")).count() + 1,
            ""
        ));
    }

    public LogReport toLogReport() {
        return new LogReport(files, start, end, count, sumBytes / count, resourceCount,
                             new ArrayList<>(codeInfos.values())
        );
    }
}
