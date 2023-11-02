package application.controller;

import application.backCode.Trie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.web.WebView;
import javafx.util.Callback;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static application.MainApplication.dictionary;

public class MasterView implements Initializable {
    protected MainMenuController mainMenuController;
    @FXML
    protected TextField start_search;
    @FXML
    protected ListView<String> search_list;
    @FXML
    protected AnchorPane search_view;
    @FXML
    protected WebView word_view_web_view;
    private String lastWord = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /** load word view. */
    protected void loadWordView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/word_view.fxml"));
            VBox wordBox = fxmlLoader.load();
            search_view.getChildren().addAll(wordBox);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void enterInput(ActionEvent e) {
        if (e.getSource() == start_search) {
            System.out.println("It's searching time!");
        }
    }

    @FXML
    public void searchList() {
        search_list.getItems().clear();
        String searchText = start_search.getText();
        ArrayList<String> list = Trie.searchPrefix(searchText);

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
                                }
                            }
                        };
                    }
                });
    }

    @FXML
    public void findTarget() {
        String target = start_search.getText();
        String explain = dictionary.search(target);
        if (explain.equals("Error: Word not found")) {
            word_view_web_view.getEngine().loadContent("", "text/html");
        } else {
            lastWord = target;
            word_view_web_view.getEngine().loadContent(explain, "text/html");
        }
    }

    @FXML
    public void clickWord(MouseEvent e) {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            if (e.getClickCount() == 1) {
                String target = search_list.getSelectionModel().getSelectedItem();
                start_search.setText(target);
                findTarget();
            }
        }
    }

    @FXML
    public void changeSearch(KeyEvent e) {
        if (e.getSource() == start_search) {
            String searchText = start_search.getText();
            if (!searchText.isEmpty()) {
                searchList();
            } else {
                search_list.getItems().clear();
            }
        }
    }


    public void init(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        loadWordView();
    }
}
