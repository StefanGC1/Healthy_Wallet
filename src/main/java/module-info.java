module com.example.healthy_wallet {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires com.google.gson;

    opens com.example.healthy_wallet to javafx.fxml, com.google.gson;
    exports com.example.healthy_wallet;
}