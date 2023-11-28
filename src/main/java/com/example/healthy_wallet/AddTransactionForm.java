package com.example.healthy_wallet;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.time.LocalDate;

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

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            try {
                ParsedData parsedData = FormDataParser.parseAndSanitize(
                        amountField.getText(),
                        dateField.getText(),
                        descriptionField.getText(),
                        categoryField.getText()
                );

                String type = ((RadioButton) typeGroup.getSelectedToggle()).getText();
                double amount = parsedData.amount;
                LocalDate date = parsedData.date;
                String description = parsedData.description;
                String category = parsedData.category;

                AbstractTransaction transaction;
                if (type.equals("Income")) {
                    transaction = new Income(amount, date, description, category);
                } else {
                    transaction = new Expense(amount, date, description, category);
                }
                transactionList.getItems().add(transaction);

                dialog.setResult(ButtonType.OK);
            } catch (IllegalArgumentException | InvalidDateException e) {
                showErrorAlert(e.getMessage());
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

    private static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}