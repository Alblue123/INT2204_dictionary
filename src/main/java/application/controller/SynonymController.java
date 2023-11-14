package application.controller;

import application.API.Synonym;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import org.json.JSONArray;
import org.json.JSONObject;

public class SynonymController extends MasterView {
    @FXML
    private VBox synBox;

    public void init(String target) {
        Thread thread = new Thread(() -> {
            JSONObject list = Synonym.getSynonym(target);
            Platform.runLater(() -> {
                synBox.getChildren().clear();
                Label synonymsLabel = new Label("synonyms");
                synonymsLabel.getStyleClass().add("text");
                fetchData(list, "synonyms");
                Label antonymsLabel = new Label("antonyms");
                antonymsLabel.getStyleClass().add("text");
                fetchData(list, "antonyms");
            });
        });
        thread.setDaemon(true);
        thread.start();
        synBox.getChildren().clear();

        Label loadingLabel = new Label("Loading data...");
        loadingLabel.getStyleClass().add("text");
        synBox.getChildren().add(loadingLabel);
    }

    // Method to fetch data from a JSONObject
    private void fetchData(JSONObject list, String type) {
        // Get the JSONArray from the JSONObject
        JSONArray wordlist = list.getJSONArray(type);

        // If the JSONArray is empty, exit the method
        if (wordlist.length() < 1) {
            Label label = new Label("Doesn't have " + type);
            label.getStyleClass().add("text");
            synBox.getChildren().add(label);
            return;
        }

        // Create a new TextFlow
        TextFlow wordlistBox = new TextFlow();

        // Add a new Label to the VBox
        synBox.getChildren().add(new Label(type));

        // Add the TextFlow to the VBox
        synBox.getChildren().add(wordlistBox);

        // Loop through the JSONArray
        for (Object v : wordlist) {
            // Create a new Button for each item in the JSONArray
            Button wordItem = new Button((String) v);

            // Create an EventHandler for the Button
            EventHandler<ActionEvent> event = e -> {
                view_word_word.setText((String) v);

                // Clear the VBox
                synBox.getChildren().clear();

                this.init();
            };

            // Add styles to the Button
            wordItem.getStyleClass().add("btn-selected");
            wordItem.getStyleClass().add("btn-margin-wrap-content");

            // Add the Button to the TextFlow
            wordlistBox.getChildren().add(wordItem);
        }
    }

}
