package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class PlayingController {

    @FXML
    private List<Button> buttons;  // This assumes you have a list of buttons in your FXML

    @FXML
    private TextField wordTextField;

    // Other PlayingController methods...

    @FXML
    private void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        wordTextField.appendText(buttonText);
    }

    // Other methods...
}
