package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

public class WordViewController extends MasterView {
    @FXML
    private WebView word_view_web_view;
    @FXML
    private Label view_word_word;

    public void init(String target, String explain) {
        view_word_word.setText(target);
        word_view_web_view.getEngine().loadContent(explain, "text/html");
    }
}
