package application.backCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class BookMark {
    private static final String path = "src/main/resources/data/bookmark.txt";
    public static void addToFile(String word) {
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            lines.add(word);
            Collections.sort(lines);
            Files.write(Path.of(path), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteWord(String word) {
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            lines.remove(word);
            Collections.sort(lines);
            Files.write(Path.of(path), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean findWordInFile(String word) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines.anyMatch(line -> line.contains(word));
        }
    }
}
