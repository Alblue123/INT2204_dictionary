package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import javafx.scene.input.MouseEvent;

public class WordViewController extends MasterView {
    @FXML
    private WebView word_view_web_view;
    @FXML
    private Label view_word_word;
    @FXML
    private Label book_mark;


    public void init(String target, String explain) {
        view_word_word.setText(target);
        word_view_web_view.getEngine().loadContent(explain, "text/html");
    }

    public void selectItem() {
        //TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void clickBookMark(MouseEvent e) {
        String word = view_word_word.getText();

    }

}
