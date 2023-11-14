package application.controller;

import application.backCode.History;
import application.backCode.Trie;
import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Objects;

import static application.MainApplication.dictionary;

public class SearchController extends MasterView {
    private Image historyImage = new Image(Objects.requireNonNull(getClass().
            getResourceAsStream("/img/clock.png")));
    @FXML
    public void searchList() {
        search_list.getItems().clear();
        String searchText = start_search.getText();
        ArrayList<String> list = Trie.searchPrefix(searchText);
        ArrayList<String> history = History.getHistory();

        for (int i = history.size() - 1; i >= 0; i--) {
            if (searchText.isEmpty() || history.get(i).startsWith(searchText)) {
                search_list.getItems().add("#" + history.get(i));
            }
        }

        for (String word : list) {
            search_list.getItems().add(word);
        }

        search_list.setCellFactory(
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
                                } else {
                                    ImageView imageView = new ImageView(historyImage);
                                    imageView.setFitHeight(15);
                                    imageView.setFitWidth(15);
                                    setGraphic(imageView);
                                    setText("  " + item.substring(1));
                                    setFont(Font.font(15));
                                }
                            }
                        };
                    }
                });
    }
    @FXML
    public void findTarget() throws IOException {
        String target = start_search.getText();
        if (target.startsWith("#")) {
            target = target.substring(1);
        }
        if (!target.isEmpty()) {
            History.insertHistory(target);
        }
        String explain = dictionary.search(target);
        loadWordView(target, explain);
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
    public void clickDefinition(MouseEvent e) throws IOException {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            if (e.getClickCount() == 1 && search_list != null) {
                findTarget();
            }
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
    
}
