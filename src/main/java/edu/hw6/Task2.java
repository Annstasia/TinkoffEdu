package edu.hw6;

import java.io.IOException;
import java.io.PipedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Task2 {
    private final static String COPY_NAME = " - копия";
    private final static String COPY_REGEX = "(?:(" + COPY_NAME + ")(?: \\((\\d)\\))?)?";
    private final static int MAIN_NAME_GROUP = 1;
    private final static int MAIN_COPY_GROUP = 2;
    private final static int COPY_COUNT_GROUP = 3;
    private final static int SUFFIX_GROUP = 4;
    private final static int START_NUMBER = 2;
//    private final static Pattern COPY_PATTERN = Pattern.compile(
//        "(.*?)(" + COPY_NAME + ")?( \\(\\d\\))?(\\..*)?$");


    private final static int FILE_NAME_GROUP = 1;
    private final static int FILE_SUFFIX_GROUP = 4;
    private final static int FILE_COPY_NAME_GROUP = 2;
    private final static int FILE_COPY_NUMBER_GROUP = 3;
    private final static int COPY_NAME_GROUP = 1;
    private final static int COPY_NUMBER_GROUP = 2;
    private final static Pattern MAIN_FILE_PATTERN = Pattern.compile(
        "(.*?)" + COPY_REGEX + "(\\..*)?$");


    // Не знаю, как в Windows, вот правила копирования для linux mint (пояснения ниже)
    // (название копируемого файла, его копии в директории -> название полученной копии):
    // 1. "file - копия (k)", ["file", "file - копия (k)"] -> "file - копия (n)"
    // 2. "file", ["file - копия (2)", "file - копия (4)"] -> "file - копия (3)"
    // 3. "file - копия (1)", ["file - копия (1)"] -> "file - копия (2)"

    // Т.е.
    // 1. копия от копии называется как копия от оригинала
    // 2. номер копии берется минимально возможным.
    // Если между номерами есть промежутки, они заполняются
    // 3. При этом если в директории нет просто оригинала или копии без номера,
    // эти все равно имена не занимаются
    public static Path cloneFile(Path path) throws IOException {
        Matcher mainFileMather = MAIN_FILE_PATTERN.matcher(path.toFile().toString());
        if (!mainFileMather.find()) {
            throw new RuntimeException("да как так-то. Регулярка " + MAIN_FILE_PATTERN.pattern() + "неправильная( ");
        }
        String fileName = mainFileMather.group(FILE_NAME_GROUP);
        StringBuilder builder = new StringBuilder(fileName)
            .append(COPY_NAME);
        String fileSuffix = mainFileMather.group(FILE_SUFFIX_GROUP);
        if (fileSuffix == null) {
            fileSuffix = "";
        }


        final Pattern copyPattern = Pattern.compile(Pattern.quote(fileName)
                                                    + COPY_REGEX +
                                                    Pattern.quote(fileSuffix));
        boolean[] copiesNumbers = new boolean[4048];
        Arrays.fill(copiesNumbers, false);
        try (Stream<Path> paths = Files.walk(path.getParent())) {
            paths.map(copyPath -> copyPattern.matcher(copyPath.toString()))
                 .filter((matcher -> matcher.find() && matcher.group(COPY_NAME_GROUP) != null))
                 .map(matcher1 ->matcher1.group(COPY_NUMBER_GROUP))
                .forEach(groupNumber -> copiesNumbers[groupNumber == null?
                    0 : Integer.parseInt(groupNumber)] = true);
        }

        if (copiesNumbers[0] || mainFileMather.group(FILE_COPY_NAME_GROUP) != null) {
            for (int i = 2; i < copiesNumbers.length; ++i) {
                if (!copiesNumbers[i]) {
                    builder.append(" (")
                           .append(i)
                           .append(")");
                    break;
                }
            }
        }

        builder.append(fileSuffix);
        return Files.copy(path, Path.of(new String(builder)));
    }
}

