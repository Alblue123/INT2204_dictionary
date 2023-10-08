package com.example.int2204_dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    Stage window;
    Scene scene_MainMenu, scene_WordList, scene_GoogleTranslate, scene_Game;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Main Menu's Layout
        FXMLLoader fxmlLoader_MainMenu = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene_MainMenu = new Scene(fxmlLoader_MainMenu.load(), 600, 300);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene_MainMenu);
        primaryStage.show();
    
        // Word List's Layout
        FXMLLoader fxmlLoader_WordList= new FXMLLoader(HelloApplication.class.getResource("wordlist-view.fxml"));
        Scene scene_WordList = new Scene(fxmlLoader_WordList.load(), 600, 300);
        primaryStage.setTitle("This is a blank word list!");
        primaryStage.setScene(scene_WordList);
        primaryStage.show();

        // Google Translate's Layout
        FXMLLoader fxmlLoader_GoogleTranslate = new FXMLLoader(HelloApplication.class.getResource("googletranslate-view.fxml"));
        Scene scene_GoogleTranslate = new Scene(fxmlLoader_GoogleTranslate.load(), 600, 300);
        primaryStage.setTitle("Welcome to Google Translate!");
        primaryStage.setScene(scene_GoogleTranslate);
        primaryStage.show();

        // Game's Layout
        FXMLLoader fxmlLoader_Game = new FXMLLoader(HelloApplication.class.getResource("game-view.fxml"));
        Scene scene_Game = new Scene(fxmlLoader_Game.load(), 600, 300);
        primaryStage.setTitle("Welcome to our game!");
        primaryStage.setScene(scene_Game);
        primaryStage.show();


    
    }

    

}