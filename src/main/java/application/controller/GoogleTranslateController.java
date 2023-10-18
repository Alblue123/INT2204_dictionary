package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GoogleTranslateController {

    @FXML
    private AnchorPane googletranslate_view;

    /** load translate view. */
    public void loadWordView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/googletranslate_view.fxml"));
            VBox wordBox = fxmlLoader.load();
            googletranslate_view.getChildren().addAll(wordBox);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
