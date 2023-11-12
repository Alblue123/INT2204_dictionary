package Game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.label;
import java.util.Arrays;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public abstract class GameController {

    @FXML
    private AnchorPane game_view;
    private WordyGame wordyGame; 
    private TextField wordInputField;

    public GameController() {
        wordyGame = new WordyGame(5, 5); 
    }

    public GridPane getGameBoardGrid() {
        return gameBoardGrid;
    }

    private void onButtonClick(int row, int col) {
        // Append the character of the clicked button to the StringBuilder
        clickedCharacters.append(wordyGame.getLetterAt(row, col));

        // Update the wordInputField
        if (wordInputField != null) {
            wordInputField.setText(clickedCharacters.toString());
        }
        
        
        System.out.println("Clicked characters: " + clickedCharacters.toString());
        
    }

    /** load game view. */
public void loadWordView() {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/playing_view.fxml"));
        VBox wordBox = fxmlLoader.load();
        game_view.getChildren().addAll(wordBox);

        GameView gameViewController = fxmlLoader.getController();
        gameViewController.setWordyGame(wordyGame);
        gameViewController.setWordInputField(wordInputField);

        // Pass the game board GridPane to initializeBoard
        wordyGame.initializeBoard(gameViewController.getGameBoardGrid());
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

    private void displayDuplicateWordError() {
        
        System.out.println("Error: Word already submitted. Choose a different word.");
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
        alert.setTitle("Game Outcome");
        alert.setHeaderText("Game Over");
        alert.setContentText("Outcome: " + outcome + "\nFinal Score: " + finalScore);
        alert.showAndWait();
    }

    public void resetUIForNewGame() {
        scoreLabel.setText("Score: 0"); // Reset the score label to its initial state.
        // Reset the game board UI elements as needed.
    }
    
    
}