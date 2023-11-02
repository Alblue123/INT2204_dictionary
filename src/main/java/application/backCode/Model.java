package application.backCode;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.controller.MainMenuController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Model implements Initializable {
    protected MainMenuController mainMenuController;
    @FXML
    protected TextField input_word;
    @FXML protected TextArea input_explain;
    @FXML protected VBox msg_box;
    @FXML protected Button bnt_save;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /** check if the word is existed in dictionary. */
    //protected boolean checkExistedWord() {
       // String word = input_word.getText().trim();
        //String wordObj = mainMenuController.getOfflineDictionary().search(word);
       // return wordObj != null && wordObj.equals(word);
    //}

    /** change the definition for the word. */
   // protected void changeExplain() {
       // String explain = input_explain.getText();
        //System.out.println("Change explain: " + explain);
    //}

    /*/@FXML
    public void onClickSave(ActionEvent e) {
        if (e.getSource() == bnt_save) {
            if (!checkExistedWord()) {
                String word = input_word.getText().trim();
                String explain = input_explain.getText().trim();
                mainMenuController.getOfflineDictionary().insert(word, explain);
            }
        }
    }*/

    //public void init(MainMenuController mainMenuController) {
        //this.mainMenuController = mainMenuController;
    //}

}
