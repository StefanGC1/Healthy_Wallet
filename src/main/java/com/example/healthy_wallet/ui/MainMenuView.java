package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.MainApplication;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class MainMenuView {
    private StackPane root;
    private MainApplication mainApp;

    public MainMenuView(MainApplication mainApp) {
        this.mainApp = mainApp;
        createView();
    }

    private void createView() {
        root = new StackPane();

        Pane bgPane = new Pane();
        BorderPane uiPane = new BorderPane();

        setBackground(bgPane);

        VBox loginBox = new VBox();
        loginBox.getStyleClass().add("content-box");
        loginBox.setMaxSize(400, 200);

        Text someText = new Text("Main Menu page");
        Button showListBtn = new Button("View Transactions");
        Button backBtn = new Button("Log out");

        // Set the controller to handle button actions
        MainMenuController controller = new MainMenuController(mainApp);
        backBtn.setOnAction(controller::handleBackButtonAction);
        showListBtn.setOnAction(controller::handleTransactionButtonAction);

        loginBox.getChildren().addAll(someText, showListBtn, backBtn);

        uiPane.setCenter(loginBox);
        root.getChildren().addAll(bgPane, uiPane);

        root.getStylesheets().add("file:src/main/resources/com/example/healthy_wallet/css/generalStyles.css");
    }

    private void setBackground(Region bgRegion) {
        Image bgImage = new Image("file:src/main/resources/com/example/healthy_wallet/background.png", 1792, 1024, false, true);
        BackgroundSize backgroundSize = new BackgroundSize(100.0, 100.0, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        bgRegion.setBackground(new Background(backgroundImage));

        // Set up blur
        GaussianBlur blur = new GaussianBlur(10);

        bgRegion.setEffect(blur);
    }

    public StackPane getView() {
        return root;
    }
}
