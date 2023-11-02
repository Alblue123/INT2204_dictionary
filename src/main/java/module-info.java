module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens application.controller to javafx.fxml;
    exports application;
    opens application.backCode to javafx.fxml;
}
