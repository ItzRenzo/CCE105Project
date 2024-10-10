package me.group.cceproject.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
    private TableView<OrderSummary> orderTableView;
    @FXML
    private TableColumn<OrderSummary, String> orderNumberColumn;
    @FXML
    private TableColumn<OrderSummary, String> orderTotalColumn;
    @FXML
    private TableColumn<OrderSummary, String> orderStatusColumn;

    private static final String ORDER_FILE = "orders.txt";

    @FXML
    public void initialize() {
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        orderTotalColumn.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        loadOrders();
    }

    private void loadOrders() {
        ObservableList<OrderSummary> orders = FXCollections.observableArrayList();
        try {
            List<String> lines = Files.readAllLines(Paths.get(ORDER_FILE));
            OrderSummary currentOrder = null;
            for (String line : lines) {
                if (line.matches("\\d{3}:")) {
                    if (currentOrder != null) {
                        orders.add(currentOrder);
                    }
                    currentOrder = new OrderSummary(line.substring(0, 3));
                } else if (line.startsWith("  Total Price:")) {
                    currentOrder.setOrderTotal(line.substring(14));
                } else if (line.startsWith("  Status:")) {
                    currentOrder.setOrderStatus(line.substring(9));
                }
            }
            if (currentOrder != null) {
                orders.add(currentOrder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        orderTableView.setItems(orders);
    }

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
