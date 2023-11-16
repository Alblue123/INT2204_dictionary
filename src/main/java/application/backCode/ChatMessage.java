package application.backCode;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ChatMessage extends VBox {
    public ChatMessage(String message, Pos alignment, String limitText) {
        super();

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.JUSTIFY);
        messageLabel.setMaxWidth(500);
        messageLabel.setPadding(new Insets(5));
        messageLabel.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        Label limitLabel = new Label(limitText);
        limitLabel.setAlignment(Pos.BOTTOM_RIGHT);

        this.setAlignment(alignment);
        this.getChildren().addAll(messageLabel, limitLabel);
        this.setMargin(messageLabel, new Insets(5));
    }
}

