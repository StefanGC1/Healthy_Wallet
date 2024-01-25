package com.example.healthy_wallet;

import com.example.healthy_wallet.database.DatabaseConnector;
import com.example.healthy_wallet.ui.*;
import com.example.healthy_wallet.utils.FileStorage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;

public class MainApplication extends Application {

    private Stage primaryStage;
    private DatabaseConnector dbConnector;
    private boolean isConnToDb;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image("file:src/main/resources/com/example/healthy_wallet/healthy-wallet-logo.png"));

        dbConnector = new DatabaseConnector();
        this.isConnToDb = dbConnector.connect();

        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            handleShutdown();
        });

        primaryStage.setTitle("Healthy Wallet");
        showLoginView();
        primaryStage.show();
    }

    public void showLoginView() {
        LoginView loginView = new LoginView(this);
        Scene scene = new Scene(loginView.getView(), 1000, 800);
        primaryStage.setScene(scene);
    }

    public void showMainMenuView() {
        MainMenuView mainMenuView = new MainMenuView(this);
        Scene scene = new Scene(mainMenuView.getView(), 1000, 800);
        primaryStage.setScene(scene);
    }

    public void showTransactionListview() {
        TransactionListView transactionListView = new TransactionListView(this);
        Scene scene = new Scene(transactionListView.getView(), 1000, 800);
        primaryStage.setScene(scene);
    }

    public void showCalendarSceneView() {
        CalendarSceneView calendarSceneView = new CalendarSceneView(this);
        Scene scene = new Scene(calendarSceneView.getView(), 1000, 800);
        primaryStage.setScene(scene);
    }

    public void showReportGenerationVIew() {
        ReportGenerationView reportGenerationView = new ReportGenerationView(this);
        Scene scene = new Scene(reportGenerationView.getView(), 1000, 800);
        primaryStage.setScene(scene);
    }

    public void showAdminSceneView() {
        AdminSceneView adminSceneView = new AdminSceneView(this);
        Scene scene = new Scene(adminSceneView.getView(), 1000, 800);
        primaryStage.setScene(scene);
    }

    public Stage getPrimaryStage() { return this.primaryStage; }

    public static void main(String[] args) {
        launch(args);
    }

    private void handleShutdown() {
        Account account = Account.getInstance();
        FileStorage fileStorage = FileStorage.getInstance();
        fileStorage.saveTransactions(account.getTransactions());

        dbConnector.closeConnection();

        Platform.exit();
    }
}
