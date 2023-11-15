package application.controller;

import application.Exception.DictionaryException;
import application.Exception.NonExistedWordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import static application.MainApplication.dictionary;

public class EditWordViewController extends ModifiedWordController {

    @Override
    @FXML
    public void save(ActionEvent e) {
        try {
            String word = this.getInput_word().getText();
            byte[] pText = this.getInput_explain().getHtmlText().getBytes(StandardCharsets.ISO_8859_1);
            String definition = new String(pText, StandardCharsets.UTF_8);
            definition =
                    definition.replace(
                            "<html dir=\"ltr\"><head></head><body contenteditable=\"true\">", "");
            definition = definition.replace("</body></html>", "");
            definition = definition.replace("\"", "'");
            if (!dictionary.edit(word, definition)) {
                this.displayAlert("Edit word failed!", "You can't edit word that doesn't exist!", false);
                throw new NonExistedWordException("Word doesn't exist!");
            } else {
                this.displayAlert("Edit word succeeded!", "Word has been changed!", true);
            }
        } catch (DictionaryException ex) {
            System.out.println(ex.getMessage());
        } finally{
            this.getInput_word().setText("");
            this.getInput_explain().setHtmlText("");
        }

    }
}
