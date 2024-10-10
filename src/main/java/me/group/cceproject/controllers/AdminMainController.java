package me.group.cceproject.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
        // Bind the columns to the OrderSummary properties
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        orderTotalColumn.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        // Populate the ComboBox with status options
        orderStatusComboBox.getItems().addAll("Pending", "In Progress", "Completed");

        // Set an event handler for when the user presses Enter on the inputOrderNumber field
        inputOrderNumber.setOnAction(event -> loadOrderDetails());

        // Load existing orders into the TableView
        loadOrders();
    }

    private void loadOrders() {
        ObservableList<OrderSummary> orders = FXCollections.observableArrayList();

        try {
            List<String> lines = Files.readAllLines(Paths.get(ORDER_FILE));
            OrderSummary currentOrder = null;

            for (String line : lines) {
                line = line.trim();  // Trim whitespace for consistency

                if (line.matches("\\d{4}: Order Items")) {
                    // If there is an order in progress, add it to the list
                    if (currentOrder != null) {
                        orders.add(currentOrder);
                    }
                    // Start a new order summary with the order number
                    String orderNumber = line.split(":")[0];
                    currentOrder = new OrderSummary(orderNumber);
                } else if (line.startsWith("Total Price:")) {
                    if (currentOrder != null) {
                        currentOrder.setOrderTotal(line.substring(12).trim());  // Extract total price
                    }
                } else if (line.startsWith("Status:")) {
                    if (currentOrder != null) {
                        currentOrder.setOrderStatus(line.substring(7).trim());  // Extract order status
                    }
                }
            }

            // Add the last order to the list
            if (currentOrder != null) {
                orders.add(currentOrder);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the orders in the TableView
        orderTableView.setItems(orders);
    }

    // Load the order details (total price and status) when an order number is entered
    private void loadOrderDetails() {
        String orderNumberInput = inputOrderNumber.getText();
        if (orderNumberInput.isEmpty()) {
            System.out.println("No order number entered.");
            return;
        }

        // Find the order in the TableView by matching the order number
        for (OrderSummary order : orderTableView.getItems()) {
            if (order.getOrderNumber().equals(orderNumberInput)) {
                // Update the UI with the selected order's total and status
                orderTotalText.setText(order.getOrderTotal());
                orderStatusComboBox.setValue(order.getOrderStatus());
                return;
            }
        }

        // If no matching order is found
        System.out.println("Order number " + orderNumberInput + " not found.");
    }

    // This method will be called when the "Update Status" button is clicked
    @FXML
    private void UpdateStatusClicked(MouseEvent event) {
        String orderNumberInput = inputOrderNumber.getText();
        String newStatus = orderStatusComboBox.getValue();

        if (orderNumberInput.isEmpty() || newStatus == null) {
            System.out.println("Order number or new status not selected.");
            return;
        }

        // Find the order and update its status
        for (OrderSummary order : orderTableView.getItems()) {
            if (order.getOrderNumber().equals(orderNumberInput)) {
                // Update the status
                order.setOrderStatus(newStatus);
                // Save changes to file
                saveOrdersToFile();

                // Print the update for confirmation
                System.out.println("Updated order status for " + orderNumberInput + " to " + newStatus);
                return;
            }
        }

        System.out.println("Order number " + orderNumberInput + " not found for updating status.");
    }

    // Save the updated orders back to the file
    private void saveOrdersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE))) {
            for (OrderSummary order : orderTableView.getItems()) {
                writer.write(order.getOrderNumber() + ": Order Items");
                writer.newLine();
                writer.write("  Total Price: " + order.getOrderTotal());
                writer.newLine();
                writer.write("  Status: " + order.getOrderStatus());
                writer.newLine();
                writer.newLine();  // Blank line to separate orders
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
