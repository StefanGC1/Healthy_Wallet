package com.example.healthy_wallet;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.time.LocalDate;

public class AddTransactionForm {

    public static void display(Stage parentStage, ListView<AbstractTransaction> transactionList) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(parentStage);
        dialog.setTitle("Add Transaction");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

//        TextField typeField = new TextField();
//        typeField.setPromptText("Type");

        // Radio buttons for type selection
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

        grid.add(new Label("Type:"), 0, 0);
        grid.add(typeBox, 1, 0);
        grid.add(new Label("Amount:"), 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(new Label("Date:"), 0, 2);
        grid.add(dateField, 1, 2);
        grid.add(new Label("Description:"), 0, 3);
        grid.add(descriptionField, 1, 3);
        grid.add(new Label("Description:"), 0, 4);
        grid.add(categoryField, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String type = ((RadioButton) typeGroup.getSelectedToggle()).getText();
                double amount = Double.parseDouble(amountField.getText());
                LocalDate date = LocalDate.parse(dateField.getText());
                String description = descriptionField.getText();
                String category = categoryField.getText();

                AbstractTransaction transaction;
                if (type.equals("Income")) {
                    transaction = new Income(amount, date, description, category);
                } else { // Defaulting to Expense
                    transaction = new Expense(amount, date, description, category);
                }
                transactionList.getItems().add(transaction);
            }
            return null;
        });

        dialog.showAndWait();
    }
}