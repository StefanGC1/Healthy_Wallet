package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.MainApplication;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LoginView {

    private StackPane root;
    private MainApplication mainApp;

    public LoginView(MainApplication mainApp) {
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

        Text someText = new Text("Login page");
        someText.setFill(Color.WHITE);
        someText.setFont(Font.font(14));

        VBox credentialsField = new VBox();
        credentialsField.getStyleClass().add("credentials-field");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button loginBtn = new Button("Login");
        Button registerBtn = new Button("Register");
        // Set the controller to handle button actions
        LoginController controller = new LoginController(mainApp);
        loginBtn.setOnAction(e -> controller.handleLoginAction(usernameField.getText(), passwordField.getText()));
        registerBtn.setOnAction(e -> controller.handleRegisterAction(usernameField.getText(), passwordField.getText()));

        credentialsField.getChildren().addAll(usernameField, passwordField);
        buttonBox.getChildren().addAll(loginBtn, registerBtn);

        loginBox.getChildren().addAll(someText, credentialsField, buttonBox);

        uiPane.setCenter(loginBox);
        root.getChildren().addAll(bgPane, uiPane);

        root.getStylesheets().add("file:src/main/resources/com/example/healthy_wallet/css/generalStyles.css");
        root.getStylesheets().add("file:src/main/resources/com/example/healthy_wallet/css/loginPageStyle.css");
    }

    private void setBackground(Region bgRegion) {
        Image bgImage = new Image("file:src/main/resources/com/example/healthy_wallet/background.png", 1792, 1024, false, true);
        BackgroundSize backgroundSize = new BackgroundSize(100.0, 100.0, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        bgRegion.setBackground(new Background(backgroundImage));

        // Set up blur
        GaussianBlur blur = new GaussianBlur(0);

        // Animate blur
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(blur.radiusProperty(), 10); // 10 is the desired blur radius
        KeyFrame keyFrame = new KeyFrame(Duration.millis(2000), keyValue); // 1000 ms for animation duration
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        bgRegion.setEffect(blur);
    }

    public StackPane getView() {
        return root;
    }
}
