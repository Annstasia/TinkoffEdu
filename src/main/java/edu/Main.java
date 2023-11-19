package edu;

import edu.project3.WriteMD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;

public class Main {
    // не нашла простого способа экранизации кавычек.
    // 1-нные названия переменных - плохо, но, кажется, в регулярках только утяжелится чтения при использовании
    // длинного имени
    final static String Q = Pattern.quote("\"");
    final static String ADDRESS_REGEX = "((?:\\d{1,3}\\.){3}\\d{1,3})()";
    final static String PORT_REGEX = "(\\d{1,5})";
    final static String REMOTE_USER_REGEX = "(.*?)";
    final static String DATE_TIME_REGEX =
        "\\[(\\d{1,2}\\/\\w*\\/\\d{4}(?::\\d{2}){3} \\+\\d{4})\\]";
    final static String REQUEST_REGEX =
        Q + "(\\w*) ([^" + Q + " ]*) ([^" + Q + "]*)" + Q;
    final static String STATUS_REGEX = "(\\d*)";
    final static String BODY_BYTES_REGEX = "(\\d*)";
    final static String HTTP_REFERER_REGEX = Q + "([^" + Q + "]*)" + Q;
    final static String USER_AGENT_REGEX = Q + "([^" + Q + "]*)" + Q;

    final static Pattern LOG_PATTERN = Pattern.compile(ADDRESS_REGEX + " - "
                                                       + REMOTE_USER_REGEX + "- "
                                                       + DATE_TIME_REGEX + " "
                                                       + REQUEST_REGEX + " "
                                                       + STATUS_REGEX + " "
                                                       + BODY_BYTES_REGEX + " "
                                                       + HTTP_REFERER_REGEX + " "
                                                       + USER_AGENT_REGEX);
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/uuuu:HH:mm:ss Z");


    public record LogRecord(InetAddress remoteAddr, String remoteUser,
                     LocalDateTime dateTime, String command, String resource,
                     String protocol,
                     int status, int bodyBytesSent,
                     String httpReferer, String userAgent){}

    public record CodeInfo(int code, int count, String name){}
    public static class LogReportBuilder {
        public LogReportBuilder() {
        }
        List<String> files = new ArrayList<>();
        LocalDateTime start = null;
        LocalDateTime end = null;
        int count = 0;
        int sumBytes = 0;

        Map<String, Integer> resourceCount = new HashMap<>();
        Map<Integer, CodeInfo> codeInfos = new HashMap<>();

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

        public void addBytes(int bytes) {
            this.sumBytes += bytes;
        }

        public void addResource(String resource) {
            this.resourceCount.put(resource, this.resourceCount.getOrDefault(resource, 0) + 1);
        }

        public void addCode(int code) {
            this.codeInfos.put(code, new CodeInfo(code,
                                                  this.codeInfos.getOrDefault(code, new CodeInfo(code, 0, "")).count + 1,
                                                  ""));
        }

        public LogReport toLogReport() {
            return new LogReport(files, start, end, count, sumBytes / count, resourceCount,
                                 new ArrayList<>(codeInfos.values()));
        }
    }
    public record LogReport(List<String> files, LocalDateTime start, LocalDateTime end, int count, int averageBytes,
                            Map<String, Integer> resourceCount, List<CodeInfo> codeInfos){}


    public static LogRecord toLogRecord(String stringLog) {
        try {
            Matcher matcher = LOG_PATTERN.matcher(stringLog);
            if (!matcher.find()) {
                Logger logger = LogManager.getLogger();
                logger.error(stringLog);
                logger.error(LOG_PATTERN.toString());

                return null;
                // TODO: LOG.ERR / EXCEPTION
            } else {
                return new LogRecord(InetAddress.getByName(matcher.group(1)),
                                     matcher.group(3),
                                     LocalDateTime.parse(matcher.group(4), dateTimeFormatter),
                                     matcher.group(5),
                                     matcher.group(6),
                                     matcher.group(7),
                                     Integer.parseInt(matcher.group(8)),
                                     Integer.parseInt(matcher.group(9)),
                                     matcher.group(10),
                                     matcher.group(11));

            }
        } catch (UnknownHostException e) {
            // TODO
            throw new RuntimeException(e);
        }
    }

    public static Stream<LogRecord> getStream(Path path) throws IOException {
        return Files.readAllLines(path).stream().map(Main::toLogRecord);
    }

    public static Stream<LogRecord> getStream(URI uri) throws IOException {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        return Files.readAllLines(path).stream().map(Main::toLogRecord);
    }


    private static void analyseLog(Stream<LogRecord> logStream, String fileName, LogReportBuilder reportBuilder) throws IOException {
        reportBuilder.addFile(fileName);
        logStream.forEach((log)->{
            if (log == null) {
                return;
            }
            reportBuilder.addTime(log.dateTime);
            reportBuilder.addCount(1);
            reportBuilder.addResource(log.resource);
            reportBuilder.addCode(log.status);
            reportBuilder.addBytes(log.bodyBytesSent);
        }
        );
    }

    public static LogReport analyse(Path... paths) throws IOException {
        LogReportBuilder logReport = new LogReportBuilder();
        for (Path path : paths) {
            analyseLog(getStream(path), path.getFileName().toString(),
                       logReport);
        }
        return logReport.toLogReport();
    }

    public static void toMD(LogReport report) throws IOException {
        try (PrintWriter writer =
                 new PrintWriter(
                     new OutputStreamWriter(
                         new BufferedOutputStream(
                                 Files.newOutputStream(Path.of("src/main/resources/project3/logreport.md")))
                         )
                     )
                 ){

            WriteMD writeMD = new WriteMD().setWriter(writer).title("Общая информация")
                             .grid(List.of("Метрика", "Значения"),
                                   List.of(
                                       List.of(List.of("Файл(-ы)"), report.files),
                                       List.of(List.of("Начальная дата"),
                                               List.of(report.start.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))),
                                       List.of(List.of("Конечная дата"),
                                               List.of(report.end.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))),
                                       List.of(List.of("Количество запросов"),
                                               List.of(String.valueOf(report.count))),
                                       List.of(List.of("Средний размер ответа "),
                                               List.of(String.valueOf(report.averageBytes)))
                                       ));

            writeMD.title("Запрашиваемые ресурсы")
                .grid(List.of("Ресурс", "Количество"),
                      report.resourceCount.entrySet().stream().map(entry->
                          List.of(List.of(entry.getKey()), List.of(String.valueOf(entry.getValue())))).toList()
                );

            writeMD.title("Коды ответа")
                .grid(List.of("Код", "Имя", "Количество"),
                      report.codeInfos.stream().map(codeInfo -> List.of(
                          List.of(String.valueOf(codeInfo.code)),
                          List.of(codeInfo.name.isEmpty() ? "-" : codeInfo.name),
                          List.of(String.valueOf(codeInfo.count)))).toList());
        };
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
        LogReport report = analyse(Path.of("src/main/resources/project3/logs.txt"));
        System.out.println(report.files);
        System.out.println(" Файл(-ы) " +  report.files.get(0));
        System.out.println(" Начальная дата (-ы) " +  report.start);
        toMD(report);

//        System.out.println(LOG_PATTERN.pattern());
//        System.out.println(toLogRecord("31.22.86.126 - - [17/May/2015:08:05:24 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\""));
//        System.out.println("31.22.86.126 - - [17/May/2015:08:05:24 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\"");

//        System.out.println(InetAddress.getByName("127.0.0.1.").getHostAddress().toString());
////        HttpRequest request =
////            HttpRequest.newBuilder().uri(new URI("https://docs.oracle.com/en/java/javase/20")).GET().build();


    }
}
