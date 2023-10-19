package application.backCode;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class InnerList {
    /**
     * Constructor 1.
     */
    public InnerList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inner_list.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException exc) {
            // handle exception
            exc.printStackTrace();
        }
    }
}
