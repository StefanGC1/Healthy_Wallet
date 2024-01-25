package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.AbstractTransaction;
import com.example.healthy_wallet.CalendarManager;
import com.example.healthy_wallet.MainApplication;
import com.example.healthy_wallet.utils.Utilities;
import javafx.event.ActionEvent;
import jfxtras.scene.control.CalendarPicker;

import java.time.LocalDate;
import java.util.List;

public class CalendarSceneController {
    private MainApplication mainApp;
    private CalendarManager calendarManager;

    public CalendarSceneController(MainApplication mainApp) {
        this.mainApp = mainApp;
        this.calendarManager = new CalendarManager();
    }

    public void handleBackButtonAction(ActionEvent event) { mainApp.showMainMenuView(); }

    public void handleTransactionsDateButtonAction(LocalDate selectedDate) {
        if (selectedDate == null) {
            Utilities.showErrorAlert("No selected date!");
            return;
        }

        List<AbstractTransaction> transactionsForDate = calendarManager.getTransactionsForDate(selectedDate);
        if (transactionsForDate.isEmpty()) {
            return;
        }

        TransactionsByDateDialog.display(mainApp.getPrimaryStage(), transactionsForDate);

    }

    public void setHighlightedDates(CalendarPicker calendar) {
        Utilities.addLocalDatesToCalendarPicker(calendar, calendarManager.getAllTransactionDates());
    }
}
