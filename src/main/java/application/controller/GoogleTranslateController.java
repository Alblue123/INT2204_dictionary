package application.controller;

import application.API.Translator;
import application.API.VoiceRSS;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.net.UnknownHostException;
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
        new Thread(() -> {
            try {
                String source = sourceText.getText();
                if (isVietnamese) {
                    desText.setText(Translator.viToEn(source));
                } else {
                    desText.setText(Translator.enToVi(source));
                }
            } catch (Exception e) {
                System.out.println("Connection lost during translation.");
                e.printStackTrace();
            }
        }).start();
    }

    public void speak() {
    new Thread(
            () -> {
              if (sourceText.getText().isEmpty()) {
                System.out.println("You have to enter text to speak.");
                return;
              }
              try {
                String source = sourceText.getText();
                if (isVietnamese) {
                  VoiceRSS.speakWords(source, "Chi", "vi-vn");
                } else {
                  VoiceRSS.speakWords(source, "Nancy", "en-gb");
                }
              } catch (Exception e) {
                System.out.println("Connection lost during speech.");
                e.printStackTrace();
              }
            })
        .start();
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

    public void copy() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(desText.getText());
        clipboard.setContent(content);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sourceText.setWrapText(true);
        desText.setWrapText(true);
    }
}
