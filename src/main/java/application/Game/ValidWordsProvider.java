package application.Game;

import java.util.List;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
public class ValidWordsProvider {
    private static final String VALID_WORDS_FILE_PATH = "src/main/resources/data/favorite.txt";

    public static List<String> getValidWords() {
        try {
            List<String> words = Files.readAllLines(Paths.get(VALID_WORDS_FILE_PATH));
            return words;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // Return an empty list in case of an error
        }
    }
}