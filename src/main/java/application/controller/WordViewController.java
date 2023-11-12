package application.controller;

import application.API.VoiceRSS;
import application.backCode.BookMark;
import application.backCode.Trie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
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
    private boolean checkFav = false;

    Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/fav_icon.png")));
    Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/fav_filled_icon.png")));
    public void init(String target, String explain) {
        view_word_word.setText(target);
        word_view_web_view.getEngine().loadContent(explain, "text/html");

        if (!target.isEmpty()) {
            try {
                boolean hasWord = BookMark.findWordInFile(target);
                if (hasWord) {
                    my_fav.setImage(image2);
                    checkFav = true;
                } else {
                    my_fav.setImage(image1);
                    checkFav = false;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
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
        BookMark.addToFile(word);
    }

    public void handleRemoveBookMark() {
        String word = view_word_word.getText();
        BookMark.deleteWord(word);
    }

    @FXML
    public void clickBookMark(MouseEvent e) {
        if (!view_word_word.getText().isEmpty()) {
            if (!checkFav) {
                my_fav.setImage(image2);
                checkFav = true;
                handleAddBookMark();
            } else {
                my_fav.setImage(image1);
                checkFav = false;
                handleRemoveBookMark();
            }
        }
    }

}
