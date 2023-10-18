package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GameController {

    @FXML
    private AnchorPane game_view;

    /** load game view. */
    public void loadWordView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/game_view.fxml"));
            VBox wordBox = fxmlLoader.load();
            game_view.getChildren().addAll(wordBox);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
