package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class WordListController {
    @FXML private AnchorPane wl_view;
    @FXML private Button btn_export;

    /** load word view. */
    public void loadWordView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/word_view.fxml"));
            VBox wordBox = fxmlLoader.load();
            wl_view.getChildren().addAll(wordBox);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** jump to export view. */
    public void export(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/export.fxml"));
            Parent root = loader.load();

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage secondaryStage = new Stage();
            Scene scene = new Scene(root);
            secondaryStage.setScene(scene);
            secondaryStage.initOwner(primaryStage);
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
