package application.controller;

import application.API.GPT;
import application.backCode.ChatMessage;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ChatGPTController {
    @FXML
    private VBox chatBox;
    @FXML
    private TextField inputField;
    @FXML
    private ScrollPane scrollPane;
    private static final int MAX_PROMPTS = 5;
    private int promptCount = 0;
    public void handleUserMessage(ActionEvent event) {
        promptCount++;
        String promptCountText = promptCount + "/" + MAX_PROMPTS;

        if (promptCount > MAX_PROMPTS) {
            ChatMessage limitMessage = new ChatMessage("You have reached the limit for today's chat.", Pos.CENTER_LEFT, "");
            chatBox.getChildren().add(limitMessage);
            inputField.setDisable(true); // Disable the input field
            return;
        }

        String userMessage = inputField.getText();
        inputField.clear();

        // Display a loading message
        ChatMessage loadingMessage = new ChatMessage("Processing...", Pos.CENTER_LEFT, promptCountText);
        chatBox.getChildren().add(loadingMessage);

        // Create a new task to get response from GPT model
        Task<String> gptTask = new Task<>() {
            @Override
            protected String call() {
                return GPT.chatGPT(userMessage);
            }
        };

        // Update the chat with the GPT response when the task is done
        gptTask.setOnSucceeded(ev -> {
            // Remove the loading message
            chatBox.getChildren().remove(loadingMessage);

            String gptResponse = gptTask.getValue();
            ChatMessage gptMessageControl = new ChatMessage(gptResponse, Pos.CENTER_LEFT, promptCountText);
            chatBox.getChildren().add(gptMessageControl);
            scrollPane.setVvalue(1.0);  // Scroll to bottom

            try (BufferedWriter writer = new BufferedWriter
                    (new FileWriter("src/main/resources/data/chat_log.txt", true))) {
                writer.write("User: " + userMessage + "\n");
                writer.write("GPT: " + gptResponse + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Start the task in a new thread
        new Thread(gptTask).start();
    }
}
