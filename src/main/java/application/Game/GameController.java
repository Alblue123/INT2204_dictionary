package application.Game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;


public abstract class GameController {

    @FXML
    private AnchorPane game_view;
    private WordyGame wordyGame; 
  

    public GameController() {
        wordyGame = new WordyGame(5, 5); 
    }

    public void playButtonClicked(ActionEvent event) {
        try {
            // Load the playing view FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playing_view.fxml"));
            AnchorPane playingView = loader.load();

            // Create a new scene with the playing view
            Scene playingScene = new Scene(playingView);

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to the stage
            stage.setScene(playingScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GridPane getGameBoardGrid() {
        return gameBoardGrid;
    }

    

    
    /** load game view. */
    public void loadWordView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/playing_view.fxml"));
            VBox wordBox = fxmlLoader.load();
    
            // Assuming playing_view.fxml has a GridPane with fx:id="gameBoardGrid"
            GridPane gameBoardGrid = (GridPane) wordBox.lookup("#gameBoardGrid");
            wordyGame.initializeBoard(gameBoardGrid);
    
            game_view.getChildren().addAll(wordBox);
    
            // You can also set other properties directly if needed
            // For example:
            // wordyGame.setSomeProperty(fxmlLoader.getController().getSomeProperty());
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    public void startNewGame(ActionEvent event) {
    // Handle starting a new game. You can reset the game state, initialize the board, and start the timer here.
        wordyGame.resetGameBoard();
        wordyGame.startTimer();
}
    @FXML
    public void submitWord(ActionEvent event) {
    // Get the submitted word from the user interface.
        String submittedWord = wordyGame.getUserInputForWordSubmission();

        if (wordyGame.isWordValid(submittedWord)) {
            int wordScore = wordyGame.calculateScore(submittedWord);
        // Update the UI to display the word's score.
            updateScoreLabel(wordScore);
        // Add the word to the list of submitted words.
            wordyGame.addSubmittedWord(submittedWord);
        } else {
        // Handle an invalid word (e.g., display an error message).
            displayInvalidWordError();
        }
    }

    private void displayInvalidWordError() {
        System.out.println("Error: Invalid word. Please enter a valid word.");
    }


    @FXML
    private Label scoreLabel; 
    
    public void updateScoreLabel(int score) {
        scoreLabel.setText("Score: " + score);
    }
    @FXML
    private GridPane gameBoardGrid;

    public void updateGameBoard(char[][] board) {
        int numRows = board.length;
        int numCols = board[0].length;
    
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // Assuming you have Text elements in your GridPane with corresponding row and column indices.
                // You can access them using the getChildren() method.
                Text textElement = (Text) gameBoardGrid.getChildren().get(i * numCols + j);
    
                // Set the text of the Text element to the letter from the 'board' array.
                textElement.setText(Character.toString(board[i][j]));
            }
        }
    }
    @FXML
    private Button shuffleButton;
    
    @FXML
    public void shuffleBoard(ActionEvent event) {
        wordyGame.shuffleBoard();
        updateGameBoard(wordyGame.getBoard());
    }
    

    public void displayGameOutcomeAndScore(String outcome, int finalScore) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("application.Game Outcome");
        alert.setHeaderText("application.Game Over");
        alert.setContentText("Outcome: " + outcome + "\nFinal Score: " + finalScore);
        alert.showAndWait();
    }

    public void resetUIForNewGame() {
        scoreLabel.setText("Score: 0"); // Reset the score label to its initial state.
        // Reset the game board UI elements as needed.
    }
    
    
}
