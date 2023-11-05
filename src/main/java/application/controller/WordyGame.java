import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WordyGame {
    private char[][] board;
    private int rows;
    private int cols;
    private Random random = new Random();
    private Set<String> validWords = new HashSet<>(); // A set of valid words from a dictionary.
    private int score = 0;

    public WordyGame(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];

        this.timer = new Timer();
        this.elapsedTime = 0;
    }

    public void initializeBoard() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int randomIndex = random.nextInt(alphabet.length());
                char randomLetter = alphabet.charAt(randomIndex);
                board[i][j] = randomLetter;
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isWordValid(String word) {
        return validWords.contains(word);
    }

    public String getUserInputForWordSubmission() {
        // You can use a TextField or any other JavaFX input control to get user input.
        // Assuming you have a TextField named 'wordInputField' in your FXML file.
        String userInput = wordInputField.getText();
        return userInput;
    }

    public char getLetterAt(int row, int col) {
        // Check if the row and column indices are valid.
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return board[row][col];
        } else {
            // Handle invalid indices (e.g., return a default character or throw an exception).
            return ' '; // Replace with an appropriate default value.
        }
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
        alert.setTitle("Game Outcome");
        alert.setHeaderText("Game Over");
        alert.setContentText("Outcome: " + gameOutcome + "\nFinal Score: " + finalScore);
        alert.showAndWait();
    }
    
    public void resetGameBoard() {
        score = 0;
        submittedWords.clear();
        resetTimer();
        initializeBoard();
        gameInProgress = false;
        
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
        int finalScore = calculateFinalScore();
        String gameOutcome = determineGameOutcome(finalScore);
        displayGameOutcomeAndScore(gameOutcome, finalScore);
        resetGameBoard();
    }
}
