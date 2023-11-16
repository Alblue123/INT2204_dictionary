package application.controller;

import application.Game.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML private Button btn_search, btn_info, btn_add, btn_edit, btn_gg, btn_game, btn_wl, btn_chat;

    @FXML private AnchorPane todo_pane;
    private AnchorPane current_pane;
    private AnchorPane search_pane;
    private AnchorPane add_word_pane;
    private AnchorPane edit_word_pane;
    private AnchorPane game_pane;
    private AnchorPane info_pane;
    private AnchorPane gpt_pane;
    private SearchController searchController;
    private WordListController wordListController;
    private AnchorPane gg_translate_pane;
    private AnchorPane wordlist_pane;

    /**
     * switch the pane and track the current pane
     *
     * @param anchorPane the current anchorPane
     */
    private void setToDo(AnchorPane anchorPane) {
        todo_pane.getChildren().setAll(anchorPane);
        current_pane = anchorPane;
    }

    /** Show the search view. */
    public void searchView() {
        this.setToDo(search_pane);
        searchController.init();
    }

    /** show the add view. */
    public void addWordView() {
        this.setToDo(add_word_pane);
    }

    /** show edit wordView. */
    public void editWordView() {
        this.setToDo(edit_word_pane);
    }

    /** show wordlist view. */
    public void wordlistView() {
        this.setToDo(wordlist_pane);
        wordListController.init();
    }


    /** show gg translate view. */
    public void ggTranslateView() {
        this.setToDo(gg_translate_pane);
    }

    /** show game view. */
    public void gameView() {
        this.setToDo(game_pane);
    }

    /** show gpt view. */
    public void gptView() {
        this.setToDo(gpt_pane);
    }

    /** show info view. */
    public void infoView() {
        this.setToDo(info_pane);
    }

    /**
     * On clicking switch scene
     *
     * @param ev the current nav button
     */
    public void onClick(ActionEvent ev) {
        if (ev.getSource() == btn_search) {
            this.searchView();
        } else if (ev.getSource() == btn_add) {
            this.addWordView();
        } else if (ev.getSource() == btn_edit) {
            this.editWordView();
        } else if (ev.getSource() == btn_gg) {
            this.ggTranslateView();
        } else if (ev.getSource() == btn_game) {
            this.gameView();
        } else if (ev.getSource() == btn_wl) {
            this.wordlistView();
        } else if (ev.getSource() == btn_chat) {
            this.gptView();
        } else if (ev.getSource() == btn_info) {
            this.infoView();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/instruction.fxml"));
            info_pane = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/search_view.fxml"));
            search_pane = fxmlLoader.load();
            searchController = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/add_word_view.fxml"));
            add_word_pane = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/edit_word_view.fxml"));
            edit_word_pane = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        } 

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/googletranslate_view.fxml"));
            gg_translate_pane = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/game_view.fxml"));
            game_pane = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/word_list_view.fxml"));
            wordlist_pane = fxmlLoader.load();
            wordListController= fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/gpt.fxml"));
            gpt_pane = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.infoView();
    }
    
}
