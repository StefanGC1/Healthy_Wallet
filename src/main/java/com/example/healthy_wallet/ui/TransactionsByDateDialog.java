package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.AbstractTransaction;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

public class TransactionsByDateDialog {
    public static void display(Stage parentStage, List<AbstractTransaction> transactionsForDate) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(parentStage);
        dialog.setTitle("Add Transaction");

        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(15);

        ListView<AbstractTransaction> transactionList = new ListView<>();
        setupTransactionListView(transactionList, transactionsForDate);

        Button cancelButton = new Button("Return");
        cancelButton.setOnAction(event -> dialog.setResult(ButtonType.CANCEL));

        HBox buttonBox = new HBox(10, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        contentBox.getChildren().addAll(transactionList, buttonBox);

        dialog.getDialogPane().setContent(contentBox);
        dialog.getDialogPane().setPrefSize(400, 300);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.CLOSE) {
                return dialogButton;
            }
            return null;
        });
        dialog.showAndWait();
    }

    private static void setupTransactionListView(ListView<AbstractTransaction> transactionList, List<AbstractTransaction> transactionsForDate) {
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
                            setText(item.getType() + ", " + item.getAmount() + ", " + item.getDate() + ", " + item.getDescription() + ", " + item.getCategory() + ", " + item.getPriority());
                        }
                    }
                };
            }
        });

        try {
            transactionList.getItems().addAll(transactionsForDate);
        } catch (Exception e) {
            // TODO: Handle Possible Exceptions
        }
    }
}
