package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.AbstractTransaction;
import com.example.healthy_wallet.Account;
import com.example.healthy_wallet.MainApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class TransactionListView {
    private ListView<AbstractTransaction> transactionList;
    private StackPane root;
    private MainApplication mainApp;

    public TransactionListView(MainApplication mainApp) {
        this.mainApp = mainApp;
        transactionList = new ListView<>();
        setupTransactionListView(transactionList);

        createView();
    }

    private void createView() {
        root = new StackPane();

        Pane bgPane = new Pane();
        BorderPane uiPane = new BorderPane();

        setBackground(bgPane);

        VBox contentBox = new VBox();
        contentBox.getStyleClass().add("content-box");
        contentBox.setMaxSize(600, 400);
        contentBox.setAlignment(Pos.CENTER);

        Text someText = new Text("Transaction View Page");
        someText.setFill(Color.web("#858585"));
        someText.setFont(Font.font(14));
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button addBtn = new Button("Add");
        Button removeBtn = new Button("Remove");
        Button backBtn = new Button("Back");

        // Set the controller to handle button actions
        TransactionListController controller = new TransactionListController(mainApp, this);
        backBtn.setOnAction(controller::handleBackButtonAction);
        addBtn.setOnAction(controller::handleAddButtonAction);
        removeBtn.setOnAction(controller::handleRemoveButtonAction);

        buttonBox.getChildren().addAll(addBtn, removeBtn, backBtn);

        contentBox.getChildren().addAll(transactionList, someText, buttonBox);

        uiPane.setCenter(contentBox);
        root.getChildren().addAll(bgPane, uiPane);

        root.getStylesheets().add("file:src/main/resources/com/example/healthy_wallet/css/generalStyles.css");
        root.getStylesheets().add("file:src/main/resources/com/example/healthy_wallet/css/transactionListPageStyle.css");
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

    private void setupTransactionListView(ListView<AbstractTransaction> transactionList) {
        transactionList.setCellFactory(new Callback<ListView<AbstractTransaction>, ListCell<AbstractTransaction>>() {
            @Override
            public ListCell<AbstractTransaction> call(ListView<AbstractTransaction> listView) {
                return new ListCell<AbstractTransaction>() {
                    @Override
                    protected void updateItem(AbstractTransaction item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getType() + ", " + item.getAmount() + ", " + item.getDate() + ", " + item.getDescription() + ", " + item.getCategory() + ", " + item.getPriority());
                        }
                    }
                };
            }
        });

        try {
            Account account = Account.getInstance();
            transactionList.getItems().addAll(account.getTransactions());
        } catch (Exception e) {
            // TODO: Handle Possible Exceptions
        }
    }

    public ListView<AbstractTransaction> getTransactionList() {
        return transactionList;
    }

    public StackPane getView() {
        return root;
    }
}
