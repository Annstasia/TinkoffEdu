package edu.project3;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Analyzer {
    private Analyzer() {
    }

    private static void analyseLog(
        Stream<LogRecord> logStream, String fileName, LogReportBuilder reportBuilder
    ) {
        reportBuilder.addFile(fileName);
        logStream.forEach((log) -> {
            if (log == null) {
                return;
            }
            reportBuilder.addTime(log.dateTime());
            reportBuilder.addCount(1);
            reportBuilder.addResource(log.resource());
            reportBuilder.addCode(log.status());
            reportBuilder.addBytes(log.bodyBytesSent());
        });
    }

    public static LogReport analyse(List<Path> paths, LocalDateTime start, LocalDateTime end) throws IOException {
        LogReportBuilder logReport = new LogReportBuilder();
        for (Path path : paths) {
            analyseLog(toRecord(parseFile(path), start, end), path.getFileName().toString(), logReport);
        }
        return logReport.toLogReport();
    }

    public static LogReport analyse(URI uri, LocalDateTime start, LocalDateTime end) throws IOException {
        LogReportBuilder logReport = new LogReportBuilder();
        analyseLog(toRecord(parseUri(uri), start, end), uri.toString(), logReport);
        return logReport.toLogReport();
    }

    public static Stream<LogRecord> toRecord(Stream<String> lineStream, LocalDateTime start, LocalDateTime end) {
        return lineStream
            .map(LogParser::toLogRecord)
            .filter(Objects::nonNull)
            .filter(logRecord -> (start == null
                                  || logRecord.dateTime().isAfter(start))
                                 && (end == null
                                     || logRecord.dateTime().isBefore(end)));

    }

    public static Stream<String> parseFile(Path path) throws IOException {
        return Files.readAllLines(path).stream();
    }

    public static Stream<String> parseUri(URI uri) throws IOException {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<Stream<String>> response = client.send(request, HttpResponse.BodyHandlers.ofLines());
            return response.body();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<LogRecord> getStream(Path path, LocalDateTime start, LocalDateTime end) throws IOException {
        return Files.readAllLines(path)
                    .stream()
                    .map(LogParser::toLogRecord)
                    .filter(Objects::nonNull)
                    .filter(logRecord ->
                                (start == null
                                 || logRecord.dateTime().isAfter(start))
                                && (end == null || logRecord.dateTime()
                                                            .isBefore(
                                                                end)));
    }

    public static Stream<LogRecord> getStream(URI uri) throws IOException {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<Stream<String>> response = client.send(request, HttpResponse.BodyHandlers.ofLines());
            return response.body().map(LogParser::toLogRecord);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
