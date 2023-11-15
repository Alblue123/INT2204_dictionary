package application.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;

public class WordFetcher {

    public CompletableFuture<List<String>> fetchWordsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                
                String filePath = "scr/main/resources/data/engmix.txt";
                Path path = Paths.get(filePath);

                // Read all lines from the file
                List<String> validWords = Files.readAllLines(path);

                return validWords;
            } catch (IOException e) {
                e.printStackTrace();
                return Arrays.asList("one", "two"); // Provide a default list in case of an error
            }
        });
    }
}
