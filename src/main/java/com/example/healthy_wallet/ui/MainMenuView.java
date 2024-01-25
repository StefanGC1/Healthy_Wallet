package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.Account;
import com.example.healthy_wallet.MainApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

        VBox mainMenuBox = new VBox();
        mainMenuBox.getStyleClass().add("content-box");
        mainMenuBox.setMaxSize(400, 200);

        Text someText = new Text("Welcome, " + Account.getInstance().getUsername());
        someText.setFill(Color.WHITE);
        someText.setFont(Font.font(14));
        Button showListBtn = new Button("View Transactions");
        Button showCalendarBtn = new Button("Calendar View");
        Button backBtn = new Button("Log out");

        VBox balanceBox = new VBox();
        balanceBox.setAlignment(Pos.CENTER);
        balanceBox.setSpacing(10);
        Text someText2 = new Text("Your current balance is");
        someText2.setFill(Color.WHITE);
        Text balanceText = new Text(Account.getInstance().getBalance() + "$");
        balanceText.setFill(Color.web("#ffea00"));
        balanceText.setFont(Font.font(14));
        balanceBox.getChildren().addAll(someText2, balanceText);

        MainMenuController controller = new MainMenuController(mainApp);
        backBtn.setOnAction(controller::handleBackButtonAction);
        showCalendarBtn.setOnAction(controller::handleCalendarButtonAction);
        showListBtn.setOnAction(controller::handleTransactionButtonAction);

        mainMenuBox.getChildren().addAll(someText, showListBtn, showCalendarBtn, backBtn, balanceBox);

        uiPane.setCenter(mainMenuBox);
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
