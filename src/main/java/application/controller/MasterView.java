package application.controller;

import application.backCode.Trie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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


import java.io.IOException;
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
    protected SynonymController synonymsController;
    @FXML
    protected AnchorPane search_view;
    @FXML
    protected Label view_word_word;



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

    protected void loadSynonymsView(String target) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/synonyms.fxml"));
        ScrollPane scrollPane = fxmlLoader.load();
        if (scrollPane != null) {
            VBox vBox = (VBox) scrollPane.getContent();
            if (vBox != null) {
                search_view.getChildren().add(scrollPane);
                synonymsController = fxmlLoader.getController();
                if (synonymsController != null) {
                    synonymsController.init(target);
                } else {
                    System.out.println("synonymsController is null");
                }
            } else {
                System.out.println("vBox is null");
            }
        } else {
            System.out.println("scrollPane is null");
        }
    }

}
