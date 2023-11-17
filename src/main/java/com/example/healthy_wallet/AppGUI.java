package com.example.healthy_wallet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.util.List;

public class AppGUI extends Application {

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

        // Save transactions at shutdown
        primaryStage.setOnCloseRequest((WindowEvent we) -> {
            try {
                fileStorage.saveTransactions(transactionList.getItems());
            } catch (Exception e) {
                // TODO: Handle Possible Exceptions
            }
        });

        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove");

        addButton.setOnAction(e -> AddTransactionForm.display(primaryStage, transactionList));
        removeButton.setOnAction(e -> {
            int selectedIndex = transactionList.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                transactionList.getItems().remove(selectedIndex);
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(transactionList, addButton, removeButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Healthy Wallet");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
