package edu.project3;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static LogReport analyse(Path... paths) throws IOException {
        LogReportBuilder logReport = new LogReportBuilder();
        for (Path path : paths) {
            analyseLog(getStream(path), path.getFileName().toString(), logReport);
        }
        return logReport.toLogReport();
    }

    public static LogReport analyse(URI uri) throws IOException {
        LogReportBuilder logReport = new LogReportBuilder();
        analyseLog(getStream(uri), uri.toString(), logReport);
        return logReport.toLogReport();
    }

    public static Stream<LogRecord> getStream(Path path) throws IOException {
        return Files.readAllLines(path).stream().map(LogParser::toLogRecord);
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
