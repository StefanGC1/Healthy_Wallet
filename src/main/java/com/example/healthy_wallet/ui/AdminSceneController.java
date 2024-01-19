package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.MainApplication;
import javafx.event.ActionEvent;

public class AdminSceneController {
    private MainApplication mainApp;

    public void handleBackButtonAction(ActionEvent event) {
        mainApp.showLoginView();
    }
}
