module com.example.int2204_dictionary {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.int2204_dictionary to javafx.fxml;
    exports com.example.int2204_dictionary;
}