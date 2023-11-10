package application.controller;

import application.API.VoiceRSS;
import application.backCode.Trie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import javafx.scene.input.MouseEvent;

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
    private Label word_speaker;


    public void init(String target, String explain) {
        view_word_word.setText(target);
        word_view_web_view.getEngine().loadContent(explain, "text/html");
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


    @FXML
    public void clickBookMark(MouseEvent e) {
        String word = view_word_word.getText();

    }

}
