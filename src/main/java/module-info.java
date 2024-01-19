module com.example.healthy_wallet {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.google.gson;
    requires java.sql;

    opens com.example.healthy_wallet to javafx.fxml, com.google.gson, java.sql;
    exports com.example.healthy_wallet;
    exports com.example.healthy_wallet.utils;
    exports com.example.healthy_wallet.ui.admin_scene_classes;
    opens com.example.healthy_wallet.utils to com.google.gson, javafx.fxml;
    exports com.example.healthy_wallet.ui;
    opens com.example.healthy_wallet.ui to com.google.gson, java.sql, javafx.fxml;
}