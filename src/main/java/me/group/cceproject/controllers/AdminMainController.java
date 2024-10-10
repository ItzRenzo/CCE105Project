package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdminMainController {

    private Queue<OrderSummary> orderQueue = new LinkedList<>();

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
        orderNumberColumn.setCellValueFactory(cellData -> cellData.getValue().orderNumberProperty());
        orderTotalColumn.setCellValueFactory(cellData -> cellData.getValue().orderTotalProperty());
        orderStatusColumn.setCellValueFactory(cellData -> cellData.getValue().orderStatusProperty());

        // Populate the ComboBox with status options
        orderStatusComboBox.getItems().addAll("Pending", "In Progress", "Completed");

        // Set an event handler for when the user presses Enter on the inputOrderNumber field
        inputOrderNumber.setOnAction(event -> loadOrderDetails());

        // Load existing orders into the TableView
        loadOrders();
    }

    private void loadOrders() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(ORDER_FILE));
            OrderSummary currentOrder = null;

            for (String line : lines) {
                line = line.trim();  // Trim whitespace for consistency

                if (line.matches("\\d{4}: Order Items")) {
                    // If there is an order in progress, add it to the list and queue
                    if (currentOrder != null) {
                        orderTableView.getItems().add(currentOrder);
                        orderQueue.add(currentOrder);  // Enqueue the order
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

            // Add the last order to the list and queue
            if (currentOrder != null) {
                orderTableView.getItems().add(currentOrder);
                orderQueue.add(currentOrder);  // Enqueue the order
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void processNextOrder() {
        if (!orderQueue.isEmpty()) {
            OrderSummary nextOrder = orderQueue.poll();  // Dequeue the first order
            System.out.println("Processing order: " + nextOrder.getOrderNumber());
            // Perform processing, such as updating its status
        } else {
            System.out.println("No more orders in the queue.");
        }
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

        // Iterate through the queue to find and update the order
        for (OrderSummary order : orderQueue) {
            if (order.getOrderNumber().equals(orderNumberInput)) {
                // Update the status
                order.setOrderStatus(newStatus);
                orderTableView.refresh();  // Refresh the TableView to show the updated status

                // If the new status is "Completed", remove it from both the queue and the TableView
                if (newStatus.equals("Completed")) {
                    orderQueue.remove(order);  // Remove from the queue
                    orderTableView.getItems().remove(order);  // Remove from the TableView
                    System.out.println("Order " + orderNumberInput + " has been completed and removed.");
                }

                // Save changes to file
                saveOrdersToFile();

                return;  // Exit once the matching order is found and updated
            }
        }

        System.out.println("Order number " + orderNumberInput + " not found.");
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
