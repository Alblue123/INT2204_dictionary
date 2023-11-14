package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerController extends GameController {

    @FXML
    protected void handlePlayButton(ActionEvent event) {
        try {
            // Load the playing view FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/player_view.fxml"));

            Parent root = loader.load();

            GameUIController controller = loader.getController();

            // Create a new scene with the playing view
            Scene scene = new Scene(root);

            // Get the stage from the event source
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Set the scene to the stage
            stage.setScene(scene);

            // Show the stage
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
