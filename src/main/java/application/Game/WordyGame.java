package application.Game;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class WordyGame {
    private char[][] board;
    private int rows;
    private int cols;
    private Set<String> validWords = new HashSet<>(); // A set of valid words from a dictionary.
    private int score = 0;
    private Timer timer;
    private int elapsedTime;
    private List<String> submittedWords = new ArrayList<>();
    private TextField wordInputField; 
    private StringBuilder clickedCharacters = new StringBuilder();
    private GridPane gameBoardGrid;
    private GameController gameController;

    public WordyGame(GameController gameController) {
        this.gameController = gameController;
    }

    public WordyGame(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
        this.gameBoardGrid = new GridPane();
        this.timer = new Timer();
        this.elapsedTime = 0;
    }

    public void setGameBoardGrid(GridPane gameBoardGrid) {
        this.gameBoardGrid = gameBoardGrid;
    }

    public void initializeBoard(GridPane gameBoardGrid) {
        // Assuming your board is already created and contains characters

        int numRows = board.length;
        int numCols = board[0].length;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Button button = createBoardButton(board[i][j]);
                gameBoardGrid.add(button, j, i);
            }
        }
    }

    private Button createBoardButton(char letter) {
        Button button = new Button(Character.toString(letter));
        button.setMinSize(45, 45);
        button.setMaxSize(45, 45);
        button.setOnAction(event -> handleButtonClick(rows,cols));
        return button;
    }

    private void handleButtonClick(int row, int col) {
        // Your button click logic in the WordyGame class.
        // This might involve updating the game state or performing some action.
        char clickedCharacter = getLetterAt(row, col);
        
        // Append the character of the clicked button to the StringBuilder
        clickedCharacters.append(clickedCharacter);
    
        // Update the wordInputField
        if (wordInputField != null) {
            wordInputField.setText(clickedCharacters.toString());
        }
    
        System.out.println("Clicked characters: " + clickedCharacters.toString());
        // Add more logic as needed.
    }

    public char[][] getBoard() {
        return board;
    }

    public void setWordInputField(TextField wordInputField) {
        this.wordInputField = wordInputField;
    }

    public char getLetterAt(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return board[row][col];
        } else {
            // Handle invalid indices, for example, return a placeholder character.
            return ' ';
        }
    }

    public String getUserInputForWordSubmission() {
        // Assuming you have a TextField named wordInputField
        if (wordInputField != null) {
            return wordInputField.getText();
        } else {
            return ""; // or handle the case where wordInputField is not set
        }
    }

    public boolean isWordValid(String word) {
        return validWords.contains(word);
    }

    public boolean isWordDuplicate(String word) {
        return submittedWords.contains(word);
    }
    public void addSubmittedWord(String word) {
        submittedWords.add(word);
    }

    public void shuffleBoard() {
        // Create a copy of the current game board.
        char[][] newBoard = new char[rows][cols];
    
        // Create a list of all characters from the current board.
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                characters.add(board[i][j]);
            }
        }
    
        // Shuffle the list of characters.
        Collections.shuffle(characters);
    
        // Populate the new board with shuffled characters.
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newBoard[i][j] = characters.get(index);
                index++;
            }
        }
    
        // Update the game board with the shuffled board.
        board = newBoard;
    }
    
    public int calculateScore(String word) {

        int wordLength = word.length();
        if (wordLength < 3) {
            return 1;
        } else if (wordLength < 5) {
            return 2;
        } else if (wordLength < 7) {
            return 3;
        } else {
            return 4;
        }
    }
    public int calculateFinalScore(List<String> submittedWords) {
        int finalScore = 0;
    
        for (String word : submittedWords) {
            if (isWordValid(word)) {
                int wordScore = calculateScore(word);
                finalScore += wordScore;
                score += wordScore;
            }
        }
        
        gameController.updateScoreLabel(score);

        return finalScore;
    }


    public String determineGameOutcome(int finalScore) {
        if (finalScore >= 50) {
            return "You won! Great job!";
        } else if (finalScore >= 25) {
            return "You did well. It's a tie!";
        } else {
            return "You lost. Try again!";
        }
    }

    public void displayGameOutcomeAndScore(String gameOutcome, int finalScore) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("application.Game Outcome");
        alert.setHeaderText("application.Game Over");
        alert.setContentText("Outcome: " + gameOutcome + "\nFinal Score: " + finalScore);
        alert.showAndWait();
    }
    
    public void resetGameBoard() {
        score = 0;
        submittedWords.clear();
        resetTimer();
        initializeBoard(gameBoardGrid);

        
    }
    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                elapsedTime++;
            }
        }, 1000, 1000);
    }

    public void stopTimer() { 
        timer.cancel(); 
    }

    public void resetTimer() {
        timer.cancel();
        elapsedTime = 0; 
    }


    public int getElapsedTime() { 
        return elapsedTime;
    }
    public void endGame() {
        int finalScore = calculateFinalScore(new ArrayList<>());
        String gameOutcome = determineGameOutcome(finalScore);
        displayGameOutcomeAndScore(gameOutcome, finalScore);
        resetGameBoard();
    }
}
