package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

import static application.MainApplication.dictionary;

public class WordListController extends MasterView implements Initializable {

    /** jump to export view. */
    /*public void export(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/export.fxml"));
            Parent root = loader.load();

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage secondaryStage = new Stage();
            Scene scene = new Scene(root);
            secondaryStage.setScene(scene);
            secondaryStage.initOwner(primaryStage);
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
