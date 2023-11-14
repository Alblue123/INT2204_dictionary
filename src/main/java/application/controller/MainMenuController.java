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
    @FXML private Button btn_search, btn_add, btn_edit, btn_gg, btn_game, btn_wl, btn_light;

    @FXML private AnchorPane todo_pane;

    @FXML private ImageView my_light;

    public static boolean isLightMode = true;
    private AnchorPane current_pane;
    private AnchorPane search_pane;
    private AnchorPane add_word_pane;
    private AnchorPane edit_word_pane;
    private AnchorPane game_pane;
    private SearchController searchController;
    private AddWordViewController addWordViewController;
    private EditWordViewController editWordViewController;
    private GoogleTranslateController googleTranslateController;
    private GameController gameManager;
    private WordListController wordListController;
    private AnchorPane gg_translate_pane;
    private AnchorPane wordlist_pane;

    private Image image1 =
            new Image(Objects.requireNonNull(getClass().
                    getResourceAsStream("/img/grey_sun_icon.png")));
    private final Image image2 =
            new Image(Objects.requireNonNull(getClass().
                    getResourceAsStream("/img/moon_icon.png")));

    public static boolean isLightMode() {
        return isLightMode;
    }
    public static void changeMode() { isLightMode = !isLightMode; }

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

    public void onChangeMode() {
        Platform.runLater(() -> {
            changeMode();
            if (!isLightMode) {
                btn_light.getScene().getStylesheets().clear();
                my_light.setImage(image2);
                btn_light.getStylesheets().add(getClass().getResource("/css/darkMainMenu.css").toExternalForm());
            } else {
                btn_light.getScene().getStylesheets().clear();
                my_light.setImage(image1);
                btn_light.getStylesheets().add(getClass().getResource("/css/mainMenu.css").toExternalForm());
            }
        });
    }

    /** show gg translate view. */
    public void ggTranslateView() {
        this.setToDo(gg_translate_pane);
    }

    /** show game view. */
    public void gameView() {
        this.setToDo(game_pane);
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
        } else if (ev.getSource() == btn_light) {
            this.onChangeMode();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            addWordViewController = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/edit_word_view.fxml"));
            edit_word_pane = fxmlLoader.load();
            editWordViewController = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        } 

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/googletranslate_view.fxml"));
            gg_translate_pane = fxmlLoader.load();
            googleTranslateController = fxmlLoader.getController();
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
        this.searchView();
    }
    
}
