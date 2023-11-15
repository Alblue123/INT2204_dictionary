package application.controller;

import application.backCode.Trie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.util.*;

import static application.MainApplication.dictionary;

public class WordListController extends MasterView {
    @Override
    @FXML
    public void findTarget() {
        String target = start_search.getText();
        String explain = dictionary.search(target);
        loadWordView(target, explain);
    }

    @Override
    @FXML
    public void searchList() {
        this.getSearch_list().getItems().clear();
        String searchText = start_search.getText();
        ArrayList<String> list = Trie.searchPrefix(searchText);

        Set<String> favoriteWords = dictionary.getFavoriteWords();

        for (String word : list) {
            if (favoriteWords.contains(word)) { // Check if the word is a favorite
                this.getSearch_list().getItems().add(word);
            }
        }

        this.getSearch_list().setCellFactory(
                new Callback<>() {
                    @Override
                    public ListCell<String> call(ListView<String> list) {
                        return new ListCell<>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item == null) {
                                    setGraphic(null);
                                    setText(null);
                                } else if (item.charAt(0) != '#') {
                                    setGraphic(null);
                                    setText(item);
                                    setFont(Font.font(15));
                                }
                            }
                        };
                    }
                });
    }
}
