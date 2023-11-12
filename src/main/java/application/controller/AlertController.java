package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AlertController {
    @FXML
    private Label alertHeader, alertBody;

    public void init(String header, String body) {
        alertHeader.setText(header);
        alertBody.setText(body);
    }
}
