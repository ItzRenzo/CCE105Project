package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AdminMainController {

    @FXML
    private TextField inputOrderNumber;

    @FXML
    private Text orderTotalText;

    @FXML
    private ComboBox<String> orderStatusComboBox;

    @FXML
    private TabPane MainTab;

    @FXML
    private TabPane InputTab;

    @FXML
    private Tab OrderQueueTab;

    @FXML
    private Tab OrdersTab;

    @FXML
    private Tab OrderDetails;

    @FXML
    private Tab OrderInput;

    @FXML
    private TableColumn<OrderItem, String> ProductID;

    @FXML
    private TableColumn<OrderItem, String> OrderName;

    @FXML
    private TableColumn<OrderItem, String> OrderPrice;

    @FXML
    private TableColumn<OrderItem, Integer> OrderQuantity;

    @FXML
    private TextField OrderNumberInput;

    @FXML
    private TableView<OrderItem> orderTableViewInOrdersTab;

    @FXML
    private void QueueClicked(MouseEvent event) {
        MainTab.getSelectionModel().select(OrderQueueTab);
        InputTab.getSelectionModel().select(OrderDetails);
    }

    @FXML
    private void OrdersClicked(MouseEvent event) {
        MainTab.getSelectionModel().select(OrdersTab);
        InputTab.getSelectionModel().select(OrderInput);
    }

    @FXML
    private void UpdateStatusClicked(MouseEvent event) {;
    }

}
