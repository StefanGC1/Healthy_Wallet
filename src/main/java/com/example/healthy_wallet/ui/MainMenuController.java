package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.Account;
import com.example.healthy_wallet.MainApplication;
import javafx.event.ActionEvent;

public class MainMenuController {
    private MainApplication mainApp;

    public MainMenuController(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void handleBackButtonAction(ActionEvent event) {
        Account.clearInstance();
        mainApp.showLoginView();
    }

    public void handleTransactionButtonAction(ActionEvent event) {
        mainApp.showTransactionListview();
    }

    public void handleCalendarButtonAction(ActionEvent event) { mainApp.showCalendarSceneView(); }

    public void handleReportGenButtonAction(ActionEvent event) { mainApp.showReportGenerationVIew();}
}
