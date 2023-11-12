package application.controller;

import application.backCode.Trie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static application.MainApplication.dictionary;

public class MasterView implements Initializable {
    @FXML
    protected ListView<String> search_list;
    @FXML
    protected TextField start_search;
    @FXML
    protected WordViewController wordViewController;
    @FXML
    protected AnchorPane search_view;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void init() {
        if (wordViewController == null) {
            loadWordView("", "");
        }

    }

    @FXML
    public void enterInput(ActionEvent e) {
        if (e.getSource() == start_search) {
            System.out.println("It's searching time!");
        }
    }
    /** load word view. */
    protected void loadWordView(String target, String explain) {
        try {
            if (!search_view.getChildren().isEmpty()) {
                search_view.getChildren().clear();
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/word_view.fxml"));
            VBox wordBox = fxmlLoader.load();
            search_view.getChildren().addAll(wordBox);
            wordViewController = fxmlLoader.getController();
            wordViewController.init(target, explain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
