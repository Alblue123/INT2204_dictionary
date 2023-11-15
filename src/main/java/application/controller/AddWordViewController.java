package application.controller;

import application.Exception.DictionaryException;
import application.Exception.ExistedWordException;
import application.Exception.VietnameseWordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;


import static application.MainApplication.dictionary;

public class AddWordViewController extends ModifiedWordController {

    /**
     * Check if word is a valid word or not
     * @param word  the word to be checked
     * @return      if word is valid
     */
    public boolean validate(String word) throws IOException {
        LanguageDetector detector = LanguageDetector.getDefaultLanguageDetector().loadModels();
        detector.addText(word);
        LanguageResult languageResult = detector.detect();
        return languageResult.getLanguage().equals("vi");
    }
    @Override
  @FXML
  public void save(ActionEvent e) throws IOException {
        try {
            String word = this.getInput_word().getText();
            byte[] pText = this.getInput_explain().getHtmlText().getBytes(StandardCharsets.ISO_8859_1);
            String definition = new String(pText, StandardCharsets.UTF_8);
            definition =
                    definition.replace(
                            "<html dir=\"ltr\"><head></head><body contenteditable=\"true\">", "");
            definition = definition.replace("</body></html>", "");
            if (!dictionary.search(word).equals("Error: Word not found")) {
                this.displayAlert("Add word failed!", "You can't add word that already exists!", false);
                throw new ExistedWordException("Word already exists!");
            } if(validate(word)) {
                this.displayAlert("Add word failed!", "You can't add word that is Vietnamese!", false);
                throw new VietnameseWordException("Word is Vietnamese!");
            } else if (dictionary.insert(word, definition)) {
                this.displayAlert("Word added successfully!", "Word has been added to dictionary!", true);
            }
        } catch(DictionaryException ex) {
            System.out.println(ex.getMessage());
        } finally{
            this.getInput_word().setText("");
            this.getInput_explain().setHtmlText("");
        }
    }

}
