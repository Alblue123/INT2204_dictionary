package application.controller;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Collections;
import java.net.URL;
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
import application.Game.Scorer;
import javafx.event.EventHandler;
import javafx.util.Duration;




public class GameController implements Initializable {
    private boolean gameStarted = false;
    private Scorer scorer = new Scorer(); 
    private int currentScore = 0;  
    private List<Button> buttonsToUpdate;
    private Set<String> dictionary = new HashSet<>();
    private StringBuilder currentWord = new StringBuilder();

    @FXML
    private GridPane gridPane; 
    @FXML
    private Label scoreLabel;
    @FXML
    private TextField wordTextField;
    @FXML
    private Label timerLabel;
    private int timeSeconds = 60; // Initial countdown time in seconds
    private Timeline timeline;
    
    String randomAlphabet = getRandomLetter();
    private String getRandomLetter() {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'A');
        return String.valueOf(randomChar);
    }
    
    @FXML
    private void handlePlayButton(ActionEvent event) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }

    private void populateButtonsToUpdate() {
    buttonsToUpdate = gridPane.getChildren().stream()
            .filter(node -> node instanceof Button)
            .map(node -> (Button) node)
            .collect(Collectors.toList());

        System.out.println("Number of buttons to update: " + buttonsToUpdate.size());
        buttonsToUpdate.forEach(button -> System.out.println("Button Text: " + button.getText()));
}

    @FXML
private void handleStartGameButton(ActionEvent event) {
    currentScore = 0;
    timeSeconds = 60; 
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

        
    } else {
        System.out.println("Error: buttonsToUpdate is null or empty");
    }
    currentWord.setLength(0);

    wordTextField.setText(currentWord.toString());
    updateScoreLabel();
}

 private void startCountdown() {
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

    private void showTimeUpAlert() {
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
    private void handleAlertClose() {
        // Implement the logic to handle the alert close event
        // For example, you can navigate back to the main menu or perform other actions
        System.out.println("Alert closed. Returning to the main menu...");
    }


private void shuffleButtonsInGridPane() {
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

    @FXML
    private void initialize() {
        // Load dictionary when the controller is initialized
        loadDictionary();
    }

    private void loadDictionary() {
        try {
            // Load dictionary words from dictionary.txt
            InputStream inputStream = getClass().getResourceAsStream("/Data/diction1.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
    
            // Convert Set to List
            List<String> dictionaryList = new ArrayList<>(dictionary);
    
            while ((line = reader.readLine()) != null) {
                dictionaryList.add(line.trim().toLowerCase());
            }
    
            // Update the Set with the new elements
            dictionary.addAll(dictionaryList);
    
            // Print the first 10 words in the dictionary for debugging
            System.out.println("Dictionary content: " + dictionaryList.subList(0, Math.min(dictionaryList.size(), 10)));
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

@FXML
private void handleButtonClick(ActionEvent event) {
    if (gameStarted && event.getSource() instanceof Button) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        // Append the letter to the current word
        currentWord.append(buttonText);

        // Update the TextField with the accumulated word
        wordTextField.setText(currentWord.toString());

        toggleClickedEffect(clickedButton);
    }
}

private void toggleClickedEffect(Button button) {
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
private void handleSubmitButton(ActionEvent event) {
    if (gameStarted) {
        String submittedWord = wordTextField.getText().trim().toLowerCase();
        int wordScore = scorer.calculateScore(submittedWord);

        if (dictionary.contains(submittedWord)) {
            // Word is in the dictionary
            System.out.println("Valid word: " + submittedWord);
            // Update the current score
            currentScore += wordScore;
            System.out.println("Current Score: " + currentScore);
           
           changeButtonColors("#d49c44");
           shuffleButtonsInGridPane();
         
           updateScoreLabel();

        } else {
            // Word is not in the dictionary
            
            System.out.println("Invalid word: " + submittedWord);
            currentScore += wordScore;
            System.out.println("Current Score: " + currentScore);
            

            changeButtonColors("#d49c44");

         
            updateScoreLabel();

            wordTextField.clear();
        }

        currentWord.setLength(0);
        wordTextField.setText(currentWord.toString());
    }
}
private void changeButtonColors(String color) {
    for (Button button : buttonsToUpdate) {
        button.setStyle("-fx-background-color: " + color + ";");
    }
}
private void updateScoreLabel() {
    if (scoreLabel != null) {
        scoreLabel.setText("Score: " + currentScore);
    }
}


// Add a method to get the current score
public int getCurrentScore() {
    return currentScore;
}

    @FXML
    private void handleExitButton(ActionEvent event) {
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
