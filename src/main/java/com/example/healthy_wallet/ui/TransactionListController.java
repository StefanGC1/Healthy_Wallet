package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.*;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

public class TransactionListController {
    private MainApplication mainApp;
    private TransactionListView transactionListView;
    public TransactionListController(MainApplication mainApp, TransactionListView transactionListView) {
        this.mainApp = mainApp;
        this.transactionListView = transactionListView;
    }

    public void handleBackButtonAction(ActionEvent event) {
        // Logic for login action
        mainApp.showMainMenuView();
    }

    public void handleAddButtonAction(ActionEvent event) {
        AddTransactionForm.display(mainApp.getPrimaryStage(), transactionListView.getTransactionList());
    }

    public void handleRemoveButtonAction(ActionEvent event) {
        ListView<AbstractTransaction> transactionList = transactionListView.getTransactionList();
        int selectedIndex =  transactionList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            transactionList.getItems().remove(selectedIndex);
            Account.getInstance().removeTransaction(selectedIndex);
        }
    }
}
