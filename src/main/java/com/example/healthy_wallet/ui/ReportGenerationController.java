package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.Account;
import com.example.healthy_wallet.MainApplication;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;

public class ReportGenerationController {
    private MainApplication mainApp;

    public ReportGenerationController(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void handleBackButtonAction(ActionEvent event) {
        Account.clearInstance();
        mainApp.showMainMenuView();
    }
}
