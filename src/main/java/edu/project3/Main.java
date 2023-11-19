package edu.project3;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    final static DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    final static String COUNT = "Количество";

    private Main() {
    }

    public static void toMD(LogReport report) throws IOException {
        toMD(report, Path.of("src/main/resources/project3/logreport.md"));
    }

    public static void toMD(LogReport report, Path path) throws IOException {
        try (PrintWriter writer =
                 new PrintWriter(
                     new OutputStreamWriter(
                         new BufferedOutputStream(
                             Files.newOutputStream(path))
                     )
                 )
        ) {

            WriteMD writeMD = new WriteMD().setWriter(writer).title("Общая информация")
                                           .grid(
                                               List.of("Метрика", "Значения"),
                                               List.of(
                                                   List.of(List.of("Файл(-ы)"), report.files()),
                                                   List.of(
                                                       List.of("Начальная дата"),
                                                       List.of(report.start()
                                                                     .format(OUTPUT_DATE_FORMAT))
                                                   ),
                                                   List.of(
                                                       List.of("Конечная дата"),
                                                       List.of(report.end()
                                                                     .format(OUTPUT_DATE_FORMAT))
                                                   ),
                                                   List.of(
                                                       List.of("Количество запросов"),
                                                       List.of(String.valueOf(report.count()))
                                                   ),
                                                   List.of(
                                                       List.of("Средний размер ответа "),
                                                       List.of(String.valueOf(report.averageBytes()))
                                                   )
                                               )
                                           );

            writeMD.title("Запрашиваемые ресурсы")
                   .grid(
                       List.of("Ресурс", COUNT),
                       report.resourceCount().entrySet().stream().map(entry ->
                                                                          List.of(
                                                                              List.of(entry.getKey()),
                                                                              List.of(String.valueOf(entry.getValue()))
                                                                          )).toList()
                   );

            writeMD.title("Коды ответа")
                   .grid(
                       List.of("Код", "Имя", COUNT),
                       report.codeInfos().stream().map(codeInfo -> List.of(
                           List.of(String.valueOf(codeInfo.code())),
                           List.of(codeInfo.name().isEmpty() ? "-" : codeInfo.name()),
                           List.of(String.valueOf(codeInfo.count()))
                       )).toList()
                   );
        }
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        LogReport report = Analyzer
            .analyse(new URI("https://raw.githubusercontent"
                             + ".com/elastic/examples/master"
                             + "/Common%20Data%20Formats"
                             + "/nginx_logs/nginx_logs"));
        toMD(report);
        report = Analyzer.analyse(Path.of("src/main/resources/project3/logs.txt"));
        toMD(report, Path.of("src/main/resources/project3/logreport1.md"));

    }
}
