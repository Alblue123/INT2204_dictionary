package application.controller;

import application.API.VoiceRSS;
import application.backCode.Trie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

import static application.MainApplication.dictionary;

public class WordViewController extends MasterView {
    @FXML
    private WebView word_view_web_view;
    @FXML
    private Label view_word_word;
    @FXML
    private Label book_mark;
    @FXML
    private Label word_delete;
    @FXML
    private ImageView my_fav;
    @FXML
    private Label word_speaker;

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
   public void clickDelete(MouseEvent e) {
        String word = view_word_word.getText();
        dictionary.delete(word);
        Trie.erase(word);
        this.reload();
    }

    public void reload() {
        view_word_word.setText("");
        word_view_web_view.getEngine().loadContent("", "text/html");
    }

    @FXML
    public void clickSpeaker(MouseEvent e) throws Exception {
        String word = view_word_word.getText();
        if (word != null) {
            VoiceRSS.speakWords(word, "Nancy", "en-gb");
        }
    }

    public void handleAddBookMark() {
        String word = view_word_word.getText();
        dictionary.updateFavorite(word, true);
    }

    public void handleRemoveBookMark() {
        String word = view_word_word.getText();
        dictionary.updateFavorite(word, false);
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
