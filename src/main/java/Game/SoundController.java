import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundController extends GameController {

    private MediaPlayer mediaPlayer;

    public SoundController() {
        super(); // Call the constructor of the superclass (GameController)
        initializeSound(); // Initialize sound-related properties
    }

    private void initializeSound() {
        // Load and set up the media file
        String soundFilePath = "path/to/your/sound/file.mp3"; // Replace with the actual path to your sound file
        Media soundMedia = new Media(new File(soundFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(soundMedia);
    }

    // Override methods from GameController as needed
    @Override
    public void startNewGame(ActionEvent event) {
        super.startNewGame(event); // Call the startNewGame method of the superclass
        playGameStartSound(); // Play a sound when a new game starts
    }

    @Override
    public void submitWord(ActionEvent event) {
        super.submitWord(event); // Call the submitWord method of the superclass
        playWordSubmitSound(); // Play a sound when a word is submitted
    }

    private void playGameStartSound() {
        // Customize this method to play the desired sound when a new game starts
        mediaPlayer.play();
    }

    private void playWordSubmitSound() {
        // Customize this method to play the desired sound when a word is submitted
        mediaPlayer.play();
    }

    public void playGameOverSound() {
        // Customize this method to play the desired sound when the game is over
        mediaPlayer.play();
    }

    public void playInvalidWordSound() {
        // Customize this method to play the desired sound for an invalid word submission
        mediaPlayer.play();
    }

}