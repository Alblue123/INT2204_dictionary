package application;

import application.Dictionary.OnlineDictionary;
import application.backCode.Dictionary;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.IOException;
import java.sql.SQLException;

import javafx.stage.Stage;

public class MainApplication extends Application {
    public static Dictionary dictionary;
    
    public static void main(String[] args) {
        launch(args);
    }

    

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(event -> {
                event.consume();
                exit(stage);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        dictionary = new OnlineDictionary();
        dictionary.init();
    }

    @FXML
    public void exit(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit!");
        alert.setContentText("Please come back soon");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully exit!");
            stage.close();
        }
    }
}