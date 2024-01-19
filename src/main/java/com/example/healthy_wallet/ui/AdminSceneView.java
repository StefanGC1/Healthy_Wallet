package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.MainApplication;
import com.example.healthy_wallet.ui.admin_scene_classes.UserInfo;
import com.example.healthy_wallet.utils.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class AdminSceneView {
    private BorderPane root;
    private TableView<UserInfo> userTable;
    private MainApplication mainApp;

    public AdminSceneView(MainApplication mainApp) {
        this.mainApp = mainApp;
        this.userTable = new TableView<>();
        setupUserInfoTable();
        createView();
    }

    private void createView() {
        root = new BorderPane();
        root.setPadding(new Insets(20, 20, 20, 20));

        HBox buttonBox = new HBox();
        Button viewTransactionsButton = new Button("See User Transactions");
        Button backButton = new Button("Log out");

        AdminSceneController controller = new AdminSceneController();
        backButton.setOnAction(controller::handleBackButtonAction);

        userTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> viewTransactionsButton.setDisable(newSelection == null)
        );

        buttonBox.getChildren().addAll(viewTransactionsButton, backButton);

        root.setCenter(userTable);
        root.setBottom(buttonBox);
    }

    private void setupUserInfoTable() {
        ObservableList<UserInfo> userData = FXCollections.observableArrayList();

        TableColumn<UserInfo, Integer> userIDColumn = new TableColumn<>("User ID");
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        TableColumn<UserInfo, String> usernameColumn = new TableColumn<>("User Name");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<UserInfo, Integer> amountColumn = new TableColumn<>("Transaction in last 24h");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amountInLastDay"));

        userTable.getColumns().addAll(userIDColumn, usernameColumn, amountColumn);

        userData.addAll(Utilities.fetchUserInfoFromDatabase());

        userTable.setItems(userData);
    }

    public BorderPane getView() {
        return root;
    }
}
