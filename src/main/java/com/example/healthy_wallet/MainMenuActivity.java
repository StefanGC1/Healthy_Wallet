package com.example.healthy_wallet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuActivity extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("file:src/main/resources/com/example/healthy_wallet/healthy-wallet-logo.png"));

        // Use BorderPane as the main layout
        BorderPane borderPane = new BorderPane();

        Text titleText = new Text("Healthy Wallet");
        titleText.setFill(Color.WHITE);
        titleText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));

        borderPane.setBackground(new Background(new BackgroundFill(Color.web("#5CBF62"), CornerRadii.EMPTY, Insets.EMPTY)));
        borderPane.setPadding((new Insets(30, 0, 0, 0)));
        BorderPane.setAlignment(titleText, Pos.TOP_CENTER);
        borderPane.setTop(titleText);

        VBox centerLayout = new VBox(10);
        centerLayout.setAlignment(Pos.CENTER);
        centerLayout.setBackground(new Background(new BackgroundFill(Color.web("#5CBF62"), CornerRadii.EMPTY, Insets.EMPTY)));

        Button transactionListButton = new Button("Transaction List");
        transactionListButton.setOnAction(e -> openTransactionList(primaryStage));

        centerLayout.getChildren().add(transactionListButton);
        borderPane.setCenter(centerLayout);

        Scene scene = new Scene(borderPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Healthy Wallet");
        primaryStage.show();
    }

    private void openTransactionList(Stage stage) {
        new TransactionListActivity().start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
