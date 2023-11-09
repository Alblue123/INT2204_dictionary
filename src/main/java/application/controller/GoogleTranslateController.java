package application.controller;

import application.API.Translator;
import application.API.VoiceRSS;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GoogleTranslateController implements Initializable {
    @FXML private TextArea sourceText;
    @FXML private TextArea desText;
    @FXML private Button btn_translate;
    @FXML private Button btn_swap;
    @FXML private Button btn_speak;
    @FXML private Label upper_label;
    @FXML private Label below_label;
    private boolean isVietnamese = false;


    public void translate() {
        String source = sourceText.getText();
        if (isVietnamese) {
            desText.setText(Translator.viToEn(source));
        } else {
            desText.setText(Translator.enToVi(source));
        }
    }

    public void swap() {
        isVietnamese = !isVietnamese;
        if (isVietnamese) {
            upper_label.setText("Vietnamese");
            below_label.setText("English");
        } else {
            upper_label.setText("English");
            below_label.setText("Vietnamese");
        }
        sourceText.clear();
        desText.clear();
    }

    public void speak() throws Exception {
        String source = sourceText.getText();
        if (isVietnamese) {
            VoiceRSS.speakWords(source, "Chi", "vi-vn");
        } else {
            VoiceRSS.speakWords(source, "Nancy", "en-gb");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
