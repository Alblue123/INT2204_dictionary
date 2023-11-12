package application.controller;

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

public class AddWordViewController implements Initializable {
    @FXML
    private TextField input_word;
    @FXML
    private Button btn_save;
    @FXML
    private HTMLEditor input_explain;
    @FXML
    private VBox msg_box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

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

    @FXML
    public void save(ActionEvent e) throws IOException {
        String word = input_word.getText();
        byte[] pText = input_explain.getHtmlText().getBytes(StandardCharsets.ISO_8859_1);
        String definition = new String(pText, StandardCharsets.UTF_8);
        definition =
                definition.replace(
                        "<html dir=\"ltr\"><head></head><body contenteditable=\"true\">", "");
        definition = definition.replace("</body></html>", "");
        if (!dictionary.insert(word, definition)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Thêm từ không thành công!");
            alert.show();
        } if(validate(word)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Từ không hợp lệ!");
            alert.show();
        }else {
            System.out.println("Them thanh cong");
        }
    }


}
