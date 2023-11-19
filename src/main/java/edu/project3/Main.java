package edu.project3;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
    final static DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    final static String COUNT = "Количество";
    final static String PATH_STRING = "path";
    final static String FROM_STRING = "from";
    final static String TO_STRING = "to";

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

    public static void main(String[] args) throws ParseException, URISyntaxException, IOException {

        Options options = new Options();
        options.addRequiredOption("p", PATH_STRING, true, "путь к логам");
        options.addOption("s", FROM_STRING, true, "начальная дата логов");
        options.addOption("e", TO_STRING, true, "конечная дата логов");
        options.addOption("f", "format", true, "формат вывода");
        CommandLine cmd = new DefaultParser().parse(options, args);
        String pathString = cmd.getOptionValue(PATH_STRING);
        LogReport report;

        LocalDateTime start = null;
        LocalDateTime end = null;
        if (cmd.hasOption(FROM_STRING)) {
            start = LocalDateTime.of(
                LocalDate.parse(cmd.getOptionValue(FROM_STRING), DateTimeFormatter.ISO_LOCAL_DATE),
                LocalTime.MIN
            );
        }
        if (cmd.hasOption(TO_STRING)) {
            end = LocalDateTime.of(
                LocalDate.parse(cmd.getOptionValue(TO_STRING), DateTimeFormatter.ISO_LOCAL_DATE),
                LocalTime.MAX
            );
        }

        if (pathString.startsWith("http")) {
            report = Analyzer.analyse(new URI(pathString), start, end);
        } else {
            List<Path> paths = new ArrayList<>();
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + pathString);

            try (Stream<Path> childrenPaths = Files.walk(Path.of("."))) {
                childrenPaths.forEach(path -> {
                    if (pathMatcher.matches(path)) {
                        paths.add(path);
                    }
                });
            }
            report = Analyzer.analyse(paths, start, end);
        }
        toMD(report, Path.of("src/main/resources/project3/logreport1.md"));
    }
}
