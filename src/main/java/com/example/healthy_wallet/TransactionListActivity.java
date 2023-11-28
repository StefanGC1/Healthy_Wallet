package com.example.healthy_wallet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.util.List;

public class TransactionListActivity extends Application {

    private ListView<AbstractTransaction> transactionList;
    private FileStorage fileStorage = new FileStorage();

    @Override
    public void start(Stage primaryStage) {
        transactionList = new ListView<>();
        transactionList.setCellFactory(new Callback<ListView<AbstractTransaction>, ListCell<AbstractTransaction>>() {
            @Override
            public ListCell<AbstractTransaction> call(ListView<AbstractTransaction> listView) {
                return new ListCell<AbstractTransaction>() {
                    @Override
                    protected void updateItem(AbstractTransaction item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getType() + ", " + item.getAmount() + ", " + item.getDate() + ", " + item.getDescription() + ", " + item.getCategory());
                        }
                    }
                };
            }
        });

        try {
            List<AbstractTransaction> loadedTransactions = fileStorage.loadTransactions();
            transactionList.getItems().addAll(loadedTransactions);
        } catch (Exception e) {
            // TODO: Handle Possible Exceptions
        }

        primaryStage.setOnCloseRequest((WindowEvent we) -> {
            try {
                fileStorage.saveTransactions(transactionList.getItems());
            } catch (Exception e) {
                // TODO: Handle Possible Exceptions
            }
        });

        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove");
        Button backButton = new Button("Back to Main Menu");

        addButton.setOnAction(e -> AddTransactionForm.display(primaryStage, transactionList));
        removeButton.setOnAction(e -> {
            int selectedIndex = transactionList.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                transactionList.getItems().remove(selectedIndex);
            }
        });
        backButton.setOnAction(e -> returnToMainMenu(primaryStage));

        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(addButton, removeButton, backButton);

        VBox layout = new VBox();
        layout.getChildren().addAll(transactionList, buttonBox);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#5CBF62"), CornerRadii.EMPTY, Insets.EMPTY)));
        VBox.setVgrow(buttonBox, Priority.ALWAYS);

        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Healthy Wallet");
        primaryStage.show();
    }

    private void returnToMainMenu(Stage stage) {
        try {
            fileStorage.saveTransactions(transactionList.getItems());
        } catch (Exception e) {
            // TODO: Handle Possible Exceptions
        }

        MainMenuActivity mainMenu = new MainMenuActivity();
        mainMenu.start(stage);
    }
}
