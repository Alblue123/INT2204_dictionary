package application.backCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class History {
    private static final String path = "src/main/resources/data/history.txt";
    private static final int MAX_WORDS = 15;

    private static ArrayList<String> his = new ArrayList<>();

    public static ArrayList<String> getHistory() {
        return his;
    }

    /** maintain the limited size. */
    public static void maintainSize() {
        if (his.size() > MAX_WORDS) {
            his.remove(0);
        } else {
            return;
        }
    }

    /** add new word to history. */
    public static void addWord(String target) {
        his.removeIf(word -> word.equals(target));
        his.add(target);
        maintainSize();
    }

    public static void insertHistory(String target) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));
        lines.add(target);
        while (lines.size() > MAX_WORDS) {
            lines.remove(0);
        }
        Files.write(Path.of(path), lines);
        addWord(target);
    }

    public static void loadHistory() throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));
        for (String line : lines) {
            addWord(line);
        }
    }

}