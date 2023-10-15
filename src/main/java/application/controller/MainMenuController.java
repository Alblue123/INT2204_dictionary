package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML private Button btn_search, btn_add;

    @FXML private AnchorPane todo_pane;

    private AnchorPane current_pane;
    private AnchorPane search_pane;
    private AnchorPane add_word_pane;
    private SearchController searchController;

    /**
     * switch the pane and track the current pane
     *
     * @param anchorPane the current anchorPane
     */
    private void setToDo(AnchorPane anchorPane) {
        todo_pane.getChildren().setAll(anchorPane);
        current_pane = anchorPane;
    }

    /** Show the search view. */
    public void searchView() {
        this.setToDo(search_pane);
    }

    /** show the add view. */
    public void addWordView() {
        this.setToDo(add_word_pane);
    }

    /**
     * On clicking switch scene
     *
     * @param ev the current nav button
     */
    public void onClick(ActionEvent ev) {
        if (ev.getSource() == btn_search) {
            this.searchView();
        } else if (ev.getSource() == btn_add) {
            this.addWordView();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/search_view.fxml"));
            search_pane = fxmlLoader.load();
            searchController = fxmlLoader.getController();
            searchController.loadWordView();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/add_word_view.fxml"));
            add_word_pane = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.searchView();
    }
}
