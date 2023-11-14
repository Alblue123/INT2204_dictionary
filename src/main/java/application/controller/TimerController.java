package application.controller;



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import java.util.Optional;

public class TimerController extends GameController {

    protected int timeSeconds = 60; // Initial countdown time in seconds
    protected Timeline timeline;

    protected void startCountdown() {
        timerLabel.setText(String.valueOf(timeSeconds)); // Set initial time on the label

        // Create a timeline for the countdown
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeSeconds--;

                // Update the timer label
                timerLabel.setText(String.valueOf(timeSeconds));

                if (timeSeconds <= 0) {
                    // Time is up, stop the timer
                    timeline.stop();
                    showTimeUpAlert();
                }
            }
        }));

        timeline.playFromStart(); // Start the countdown
    }

    protected void showTimeUpAlert() {
        // Create a time-up alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Time's Up!");
        alert.setHeaderText(null);
        alert.setContentText("Your time is up!");

        // Set the result converter to handle the OK button
        alert.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                // Set gameStarted to false when OK is pressed
                gameStarted = false;
                return null;
            }
            return null;
        });

        // Show the alert and wait for the OK button to be pressed
        Optional<ButtonType> result = alert.showAndWait();
    }
}
