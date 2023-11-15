package application.Game;

import java.util.stream.Collectors;

import application.Game.ValidWordsProvider;
import application.controller.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.*;

public class GameUIController extends GameController {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label scoreLabel;

    @FXML
    private TextField wordTextField;

    @FXML
    private Label timerLabel;

    protected void populateButtonsToUpdate() {
        buttonsToUpdate = gridPane.getChildren().stream()
                .filter(node -> node instanceof Button)
                .map(node -> (Button) node)
                .collect(Collectors.toList());

        System.out.println("Number of buttons to update: " + buttonsToUpdate.size());
        buttonsToUpdate.forEach(button -> System.out.println("Button Text: " + button.getText()));

    }

    @FXML
    protected void handleStartGameButton(ActionEvent event) {
        currentScore = 0;
        timeSeconds = 60;
        submittedWordsList.clear();
        timerLabel.setText(String.valueOf(timeSeconds));
        // Stop the previous timeline if it exists
        if (timeline != null) {
            timeline.stop();
        }
        startCountdown();
        gameStarted = true;
        populateButtonsToUpdate();
        if (buttonsToUpdate != null && !buttonsToUpdate.isEmpty()) {
            System.out.println("Shuffling buttons");
            shuffleButtonsInGridPane();
            changeButtonColors("#d49c44");
            enableAllButtons();

        } else {
            System.out.println("Error: buttonsToUpdate is null or empty");
        }
        currentWord.setLength(0);

        wordTextField.setText(currentWord.toString());

        updateScoreLabel();
    }

    

    protected void shuffleButtonsInGridPane() {
        // Shuffle the list of buttonsToUpdate
        List<Button> shuffledButtons = new ArrayList<>(buttonsToUpdate);
        Collections.shuffle(shuffledButtons);

        // Clear the existing buttons from the gridPane
        gridPane.getChildren().clear();

        int numColumns = 5;
        // Add the shuffled buttons back to the gridPane
        for (int i = 0; i < shuffledButtons.size(); i++) {
            Button button = shuffledButtons.get(i);
            gridPane.add(button, i % numColumns, i / numColumns);
        }
    }

    protected Button clickedButton;

    @FXML
    protected void handleButtonClick(ActionEvent event) {
        if (gameStarted && timeSeconds > 0 && event.getSource() instanceof Button) {
            clickedButton = (Button) event.getSource();
            String buttonText = clickedButton.getText();

            // Append the letter to the current word
            currentWord.append(buttonText);

            // Update the TextField with the accumulated word
            wordTextField.setText(currentWord.toString());

            toggleClickedEffect(clickedButton);
        }
    }

    @FXML
    protected void handleSubmitButton(ActionEvent event) {
        if (gameStarted && timeSeconds > 0) {
            String submittedWord = wordTextField.getText().trim().toLowerCase();
            int wordScore = scorer.calculateScore(submittedWord);

            List<String> validWords = ValidWordsProvider.getValidWords();

            if (submittedWordsList.contains(submittedWord)) {
                // Handle the case where the word is a duplicate
                System.out.println("Duplicate word: " + submittedWord);
                System.out.println("Current Score: " + currentScore);
                changeButtonColors("#d49c44");
                updateScoreLabel();
                enableAllButtons();
            } else if (validWords.contains(submittedWord)) {
                // Word is in the dictionary
                System.out.println("Valid word: " + submittedWord);
                // Update the current score
                currentScore += wordScore;
                System.out.println("Current Score: " + currentScore);

                changeButtonTextOfClickedButton();
                // Add the submitted word to the list
                submittedWordsList.add(submittedWord);

                changeButtonColors("#d49c44");
                shuffleButtonsInGridPane();
                updateScoreLabel();
                enableAllButtons();
            } else {
                // Word is not in the dictionary
                System.out.println("Invalid word: " + submittedWord);
                System.out.println("Current Score: " + currentScore);
                changeButtonColors("#d49c44");
                updateScoreLabel();
                enableAllButtons();
                wordTextField.clear();
            }

            currentWord.setLength(0);
            wordTextField.setText(currentWord.toString());
        }
    }

    protected void changeButtonTextOfClickedButton() {
        if (clickedButton != null) {
            // Get a random alphabet character
            String randomAlphabet = getRandomLetter();

            // Change the text of the clicked button
            clickedButton.setText(randomAlphabet);
        }
    }

    protected void updateScoreLabel() {
        if (scoreLabel != null) {
            scoreLabel.setText("Score: " + currentScore);
        }
    }

    @FXML
    protected void handleExitButton(ActionEvent event) {
        try {
            // Load the main menu view FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainMenu.fxml"));
            Parent root = loader.load();

            // Create a new scene with the main menu view
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
