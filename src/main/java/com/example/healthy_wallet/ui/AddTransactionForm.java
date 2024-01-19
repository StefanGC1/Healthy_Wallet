package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.AbstractTransaction;
import com.example.healthy_wallet.Account;
import com.example.healthy_wallet.InvalidDateException;
import com.example.healthy_wallet.utils.FormDataParser;
import com.example.healthy_wallet.utils.Utilities;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddTransactionForm {

    public static void display(Stage parentStage, ListView<AbstractTransaction> transactionList) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(parentStage);
        dialog.setTitle("Add Transaction");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);

        ToggleGroup typeGroup = new ToggleGroup();
        RadioButton incomeButton = new RadioButton("Income");
        incomeButton.setToggleGroup(typeGroup);
        incomeButton.setSelected(true);  // Default to Income
        RadioButton expenseButton = new RadioButton("Expense");
        expenseButton.setToggleGroup(typeGroup);

        HBox typeBox = new HBox(10, incomeButton, expenseButton);

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");
        TextField dateField = new TextField();
        dateField.setPromptText("Date (e.g., 2023-01-30)");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description (leave empty for no description)");
        TextField categoryField = new TextField();
        categoryField.setPromptText("Category (leave empty for no category)");

        ComboBox<String> priorityBox = new ComboBox<>();
        priorityBox.getItems().addAll("Very Low", "Low", "Medium", "High", "Very High");
        priorityBox.setValue("Low");

        grid.add(new Label("Type:"), 0, 0);
        grid.add(typeBox, 1, 0);
        grid.add(new Label("Amount:"), 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(new Label("Date:"), 0, 2);
        grid.add(dateField, 1, 2);
        grid.add(new Label("Description:"), 0, 3);
        grid.add(descriptionField, 1, 3);
        grid.add(new Label("Category:"), 0, 4);
        grid.add(categoryField, 1, 4);
        grid.add(new Label("Priority:"), 0, 5);
        grid.add(priorityBox, 1, 5);

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            try {
                AbstractTransaction newTransaction = FormDataParser.parseAndSanitizeTransactionData(
                        ((RadioButton) typeGroup.getSelectedToggle()).getText(),
                        amountField.getText(),
                        dateField.getText(),
                        descriptionField.getText(),
                        categoryField.getText(),
                        priorityBox.getValue()
                );

                transactionList.getItems().add(newTransaction);
                Account account = Account.getInstance();
                account.addTransaction(newTransaction);

                Thread thread = new Thread(new Runnable() {
                   @Override
                   public void run() {
                       Utilities.addTransactionToDatabase(newTransaction);
                   }
                });

                thread.start();

//                Utilities.addTransactionToDatabase(newTransaction);

                dialog.setResult(ButtonType.OK);
            } catch (IllegalArgumentException | InvalidDateException e) {
                Utilities.showErrorAlert(e.getMessage());
                event.consume();
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> dialog.setResult(ButtonType.CANCEL));

        HBox buttonBar = new HBox(10, okButton, cancelButton);
        grid.add(buttonBar, 1, 6);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().setPrefSize(400, 300);

        dialog.setResultConverter(dialogButton -> {
            return null;
        });
        dialog.showAndWait();
    }
}