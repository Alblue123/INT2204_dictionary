package application.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public abstract class ModifiedWordController {
    @FXML
    private TextField input_word;
    @FXML
    private HTMLEditor input_explain;
    @FXML
    private VBox msg_box;

    public TextField getInput_word() {
        return input_word;
    }

    public HTMLEditor getInput_explain() {
        return input_explain;
    }

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
            alertBox.getStylesheets().add(
                    Objects.requireNonNull(
                            getClass().getResource("/css/alert.css"))
                            .toExternalForm());
        } else {
            alertBox.getStylesheets().add(
                    Objects.requireNonNull(
                            getClass().getResource("/css/alertFailed.css"))
                            .toExternalForm());
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

    protected void clearAlert(String alertId) {
        ObservableList<Node> messageChildren = msg_box.getChildren();
        messageChildren.removeIf(node -> alertId.equals(node.getId()));
    }

    public abstract void save(ActionEvent e) throws IOException;
}
