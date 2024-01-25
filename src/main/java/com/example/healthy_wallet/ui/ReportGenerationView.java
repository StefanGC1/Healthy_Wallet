package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.MainApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ReportGenerationView {
    private StackPane root;
    private MainApplication mainApp;

    public ReportGenerationView(MainApplication mainApp) {
        this.mainApp = mainApp;
        createView();
    }

    private void createView() {
        root = new StackPane();

        Pane bgPane = new Pane();
        BorderPane uiPane = new BorderPane();

        setBackground(bgPane);

        setOptionScene(uiPane);

        root.getChildren().addAll(bgPane, uiPane);

        root.getStylesheets().add("file:src/main/resources/com/example/healthy_wallet/css/generalStyles.css");
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

    private void setOptionScene(BorderPane uiPane) {
        VBox contentBox = new VBox();
        contentBox.getStyleClass().add("content-box");
        contentBox.setMaxSize(400, 200);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(20);

        Button generateReportButton = new Button("Generate Report");
        Button backButton = new Button("Back");

        ReportGenerationController reportGenerationController = new ReportGenerationController(this.mainApp);
        generateReportButton.setOnAction(e -> setReportScene(uiPane));
        backButton.setOnAction(reportGenerationController::handleBackButtonAction);

        contentBox.getChildren().addAll(generateReportButton, backButton);

        uiPane.getChildren().clear();
        uiPane.setCenter(contentBox);
    }

    private void setReportScene(BorderPane uiPane) {
        VBox contentBox = new VBox();
        contentBox.getStyleClass().add("content-box");
        contentBox.setMaxSize(400, 200);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(20);

        Text someText = new Text("Report Generated");
        Button generateReportButton = new Button("Generate Another Report");
        Button backButton = new Button("Back");

        ReportGenerationController reportGenerationController = new ReportGenerationController(this.mainApp);
        generateReportButton.setOnAction(e -> setOptionScene(uiPane));
        backButton.setOnAction(reportGenerationController::handleBackButtonAction);

        contentBox.getChildren().addAll(generateReportButton, backButton);

        uiPane.getChildren().clear();
        uiPane.setCenter(contentBox);
    }

    public StackPane getView() {
        return root;
    }
}
