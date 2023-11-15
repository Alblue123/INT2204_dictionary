package application.controller;

import application.backCode.History;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static application.MainApplication.dictionary;

public class MasterView {
    @FXML
    private ListView<String> search_list;
    @FXML
    protected TextField start_search;
    @FXML
    private WordViewController wordViewController;
    @FXML
    private SynonymController synonymsController;
    @FXML
    private AnchorPane search_view;
    @FXML
    protected Label view_word_word;


    public void init() {
        if (wordViewController == null) {
            loadWordView("", "");
        }

    }

    public ListView<String> getSearch_list() {
        return search_list;
    }

    /** load word view. */
    public void loadWordView(String target, String explain) {
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

    public void loadSynonymsView(String target) throws IOException {
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

    @FXML
    public void clickSynonyms(MouseEvent e) throws IOException {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            if (e.getClickCount() == 1 && search_list != null) {
                loadSynonymsView(start_search.getText());
            }
        }
    }

    @FXML
    public void clickWord(MouseEvent e) throws IOException {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            if (e.getClickCount() == 1 && search_list != null) {
                String target = search_list.getSelectionModel().getSelectedItem();
                if (target != null) {
                    start_search.setText(target);
                    findTarget();
                }
            }
        }

    }

    @FXML
    public void findTarget() throws IOException {
    }

    @FXML
    public void searchList() {
    }

    @FXML
    public void clickDefinition(MouseEvent e) throws IOException {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            if (e.getClickCount() == 1 && search_list != null) {
                findTarget();
            }
        }

    }
}
