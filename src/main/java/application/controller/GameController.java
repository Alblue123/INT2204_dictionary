package application.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import application.Game.Scorer;
import javafx.event.EventHandler;
import javafx.util.Duration;




public class GameController implements Initializable {
    protected boolean gameStarted = false;
    protected WordFetcher wordFetcher = new WordFetcher();
    protected Scorer scorer = new Scorer(); 
    protected int currentScore = 0;  
    protected List<Button> buttonsToUpdate;
    protected Set<String> dictionary = new HashSet<>();
    protected StringBuilder currentWord = new StringBuilder();
    protected List<String> submittedWordsList = new ArrayList<>();
    protected boolean isButtonClicked = false;
    @FXML
    protected GridPane gridPane; 
    @FXML
    protected Label scoreLabel;
    @FXML
    protected TextField wordTextField;
    @FXML
    protected Label timerLabel;
    protected int timeSeconds = 60; // Initial countdown time in seconds
    protected Timeline timeline;
    


    String randomAlphabet = getRandomLetter();
    protected String getRandomLetter() {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'A');
        return String.valueOf(randomChar);
    }
    
    @FXML
    protected void handlePlayButton(ActionEvent event) {
        try {
            // Load the playing view FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/player_view.fxml"));
            
            Parent root = loader.load();

            GameController controller = loader.getController();


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
        @FXML
    protected void handleInfoButton(ActionEvent event) {
        try {
            // Load the playing view FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameinfo_view.fxml"));
            
            Parent root = loader.load();

            GameController controller = loader.getController();


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }

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

// Add this method to provide access to the GridPane
public GridPane getGridPane() {
    return gridPane;
}


    
    
protected Button clickedButton;

@FXML
protected void handleButtonClick(ActionEvent event) {
    if (gameStarted && timeSeconds > 0 && event.getSource() instanceof Button) {
        clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        // Append the letter to the current word
        currentWord.append(buttonText);
        clickedButton.setDisable(true);
        // Update the TextField with the accumulated word
        wordTextField.setText(currentWord.toString());

        toggleClickedEffect(clickedButton);
        
    }
}

protected void toggleClickedEffect(Button button) {
    // Check if the button currently has the clicked effect
    String currentStyle = button.getStyle();
    String clickedStyle = "-fx-background-color: #a67b3a;";

    if (currentStyle.equals(clickedStyle)) {
        // Reset the button style to its original state
        button.setStyle("-fx-background-color: #d49c44");
    } else {
        // Apply the clicked effect to the button
        button.setStyle(clickedStyle);
    }
}

@FXML
protected void handleSubmitButton(ActionEvent event) {
    if (gameStarted && timeSeconds > 0 ) {
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
        isButtonClicked = false;
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

protected void enableAllButtons() {
    for (Button button : buttonsToUpdate) {
        button.setDisable(false);
    }
}
protected void changeButtonColors(String color) {
    for (Button button : buttonsToUpdate) {
        button.setStyle("-fx-background-color: " + color + ";");
    }
}
protected void updateScoreLabel() {
    if (scoreLabel != null) {
        scoreLabel.setText("Score: " + currentScore);
    }
}


// Add a method to get the current score
public int getCurrentScore() {
    return currentScore;
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
