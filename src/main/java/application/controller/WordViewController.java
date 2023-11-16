package application.controller;

import application.API.Synonym;
import application.API.VoiceRSS;
import application.backCode.Bookmark;
import application.backCode.History;
import application.backCode.Trie;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import static application.MainApplication.dictionary;

public class WordViewController extends MasterView {
    @FXML
    private WebView word_view_web_view;
    @FXML
    private ImageView my_fav;

    Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/fav_icon.png")));
    Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/fav_filled_icon.png")));
    public void init(String target, String explain) {
        view_word_word.setText(target);
        word_view_web_view.getEngine().loadContent(explain, "text/html");

        if (!target.isEmpty()) {
            try {
                if (dictionary.checkFavorite(target)) {
                    my_fav.setImage(image2);
                } else {
                    my_fav.setImage(image1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
   public void clickDelete(MouseEvent e) throws IOException {
        String word = view_word_word.getText();
        dictionary.delete(word);
        Trie.erase(word);
        History.deleteWord(word);
        this.reload();
    }

    public void reload() {
        view_word_word.setText("");
        word_view_web_view.getEngine().loadContent("", "text/html");
    }

    @FXML
    public void clickSpeaker(MouseEvent e) {
        try {
            String word = view_word_word.getText();
            if (word.isEmpty()) {
                throw new Exception("You have to have word to speak.");
            } else {
                // Create a new task for speech synthesis
                Task<Void> speakTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            VoiceRSS.speakWords(word, "Nancy", "en-gb");
                        } catch (IOException e) {
                            System.out.println("Connection lost: " + e.getMessage());
                        }
                        return null;
                    }
                };


                // Start the task in a new thread
                new Thread(speakTask).start();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void handleAddBookMark() {
        String word = view_word_word.getText();
        dictionary.updateFavorite(word, true);
        Bookmark.addToFile(word);
    }

    public void handleRemoveBookMark() {
        String word = view_word_word.getText();
        dictionary.updateFavorite(word, false);
        Bookmark.deleteWord(word);
    }

    @FXML
    public void clickBookMark(MouseEvent e) {
        String word = view_word_word.getText();
        if (!word.isEmpty()) {
            if (!dictionary.checkFavorite(word)) {
                my_fav.setImage(image2);
                handleAddBookMark();
            } else {
                my_fav.setImage(image1);
                handleRemoveBookMark();
            }
        }
    }



}
