package application.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class ModifiedWordController {
    @FXML
    protected VBox msg_box;

    protected void displayAlert(String header, String body, boolean state) {
        this.displayAlert(header, body, "new_alert_message", true, state);
    }

    /**
     * Display alert message for adding and editing word
     * @param header    header of alert
     * @param body      body of alert
     * @param id        id of alert
     * @param autoClose auto close alert
     */
    protected void displayAlert(String header, String body, String id,
                                boolean autoClose, boolean state) {
        VBox alertBox = loadFXML("/fxml/alert.fxml");
        if (alertBox == null) {
            return;
        }


        alertBox.setId("new_alert_message");
        AlertController controller = (AlertController) alertBox.getUserData();
        controller.init(header, body);
        this.clearAlert(id);

        msg_box.getChildren().add(0, alertBox);

        if (state) {
            alertBox.setStyle("-fx-background-color: #cce5ff;" +
                    " -fx-border-color: #b8daff");
            alertBox.setStyle("-fx-text-fill: #004085");
        } else {
            alertBox.setStyle("-fx-background-color: #ffcccb;" +
                    " -fx-border-color: #e54646");
        }

        if (autoClose) {
            Timeline autoCloseTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(3),
                            event -> msg_box.getChildren().remove(alertBox)));
            autoCloseTimeline.play();
        }
    }

    private VBox loadFXML(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            VBox vbox = loader.load();
            vbox.setUserData(loader.getController());
            return vbox;
        } catch (IOException e) {
            System.out.println("Error loading FXML file: " + fxmlPath);
            return null;
        }
    }

    protected void clearAlert() {
        this.clearAlert("new_alert_message");
    }

    protected void clearAlert(String alertId) {
        ObservableList<Node> messageChildren = msg_box.getChildren();
        messageChildren.removeIf(node -> alertId.equals(node.getId()));
    }
}