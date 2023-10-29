package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MasterView implements Initializable {
    protected MainMenuController mainMenuController;
    @FXML
    protected TextField start_search;
    @FXML
    protected ListView<String> search_list;
    @FXML
    protected AnchorPane search_view;

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
    public void clickListView(ActionEvent e) {

    }



}
