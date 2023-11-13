module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires voicerss.tts;
    requires javafx.media;
    requires java.desktop;
    requires org.apache.tika.core;
    requires org.json;
    requires org.apache.commons.text;

    opens application.controller to javafx.fxml;
    exports application;
    opens application.backCode to javafx.fxml;
    opens application.Game to javafx.fxml, javafx.graphics;
}
