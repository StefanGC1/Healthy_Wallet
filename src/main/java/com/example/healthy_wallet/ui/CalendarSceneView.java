package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.MainApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfxtras.scene.control.CalendarPicker;

import java.time.LocalDate;
import java.util.Calendar;

public class CalendarSceneView {
    private StackPane root;
    private MainApplication mainApp;
    private LocalDate selectedDate;

    public CalendarSceneView(MainApplication mainApp) {
        this.mainApp = mainApp;
        this.selectedDate = null;
        createView();
    }

    private void createView() {
        root = new StackPane();

        Pane bgPane = new Pane();
        BorderPane uiPane = new BorderPane();

        setBackground(bgPane);

        VBox contentBox = new VBox();
        contentBox.getStyleClass().add("content-box");
        contentBox.setMaxSize(800, 400);

        Text someText = new Text("Calendar View Page");
        someText.setFill(Color.WHITE);
        someText.setFont(Font.font(14));

        CalendarPicker calendarPicker = new CalendarPicker();
        calendarPicker.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        calendarPicker.setPadding(new Insets(10, 10, 10, 10));
        addListenerToCalendarPicker(calendarPicker);

        VBox buttonBox = new VBox();
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        Button transactionsButton = new Button("View Transaction for selected date");

        CalendarSceneController calendarSceneController = new CalendarSceneController(mainApp);
        backButton.setOnAction(calendarSceneController::handleBackButtonAction);
        transactionsButton.setOnAction(e -> calendarSceneController.handleTransactionsDateButtonAction(this.selectedDate));
        calendarSceneController.setHighlightedDates(calendarPicker);

        buttonBox.getChildren().addAll(transactionsButton, backButton);
        contentBox.getChildren().addAll(someText, calendarPicker, buttonBox);

        uiPane.setCenter(contentBox);

        root.getChildren().addAll(bgPane, uiPane);

        root.getStylesheets().add("file:src/main/resources/com/example/healthy_wallet/css/generalStyles.css");
        root.getStylesheets().add("file:src/main/resources/com/example/healthy_wallet/css/calendarPageStyles.css");
    }

    private void setBackground(Region bgRegion) {
        Image bgImage = new Image("file:src/main/resources/com/example/healthy_wallet/background.png", 1792, 1024, false, true);
        BackgroundSize backgroundSize = new BackgroundSize(100.0, 100.0, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        bgRegion.setBackground(new Background(backgroundImage));

        // Set up blur
        GaussianBlur blur = new GaussianBlur(10);

        bgRegion.setEffect(blur);
    }

    private void addListenerToCalendarPicker(CalendarPicker calendarPicker) {
        calendarPicker.calendarProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Calendar selectedCalendar = newValue;
                this.selectedDate = LocalDate.of(selectedCalendar.get(Calendar.YEAR),
                        selectedCalendar.get(Calendar.MONTH) + 1,
                        selectedCalendar.get(Calendar.DAY_OF_MONTH));
            } else {
                this.selectedDate = null;
            }
        });
    }

    public StackPane getView() { return root; }
}
