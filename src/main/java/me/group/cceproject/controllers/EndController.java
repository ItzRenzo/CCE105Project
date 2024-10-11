package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EndController {

    private static final String ORDER_FILE = "orders.txt";
    String paymentType;

    @FXML
    Text Reminder;

    @FXML
    private Text OrderNumber;

    @FXML
    public void initialize() {
        if (OrderNumber != null) {
            updateOrderNumber();
        } else {
            System.err.println("OrderNumber Text is null. Check your FXML file.");
        }
    }

    private void updateOrderNumber() {
        int orderNumber = getNextOrderNumber();
        OrderNumber.setText(String.format("%04d", orderNumber));  // Ensure it's 4 digits
    }

    private int getNextOrderNumber() {
        try {
            // Ensure file exists before reading
            if (!Files.exists(Paths.get(ORDER_FILE))) {
                Files.createFile(Paths.get(ORDER_FILE));
                return 1;
            }

            List<String> lines = Files.readAllLines(Paths.get(ORDER_FILE));
            if (lines.isEmpty()) {
                return 1;  // If file is empty, start from order number 1
            }

            // Loop through the file from the end to find the last valid order number
            for (int i = lines.size() - 1; i >= 0; i--) {
                String line = lines.get(i).trim();
                if (line.matches("\\d{4}: Order Items")) {
                    return Integer.parseInt(line.split(":")[0]) + 1;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;  // Fallback to 1 if any issues occur
    }

    public void saveOrder(List<OrderItem> orderItems) {
        System.out.println("saveOrder called with " + orderItems.size() + " items.");

        // If orderItems list is empty, skip saving
        if (orderItems == null || orderItems.isEmpty()) {
            System.err.println("No order items to save.");
            return;
        }

        // Get the order number and the order type
        int orderNumber = Integer.parseInt(OrderNumber.getText());
        String orderType = OrderMenuController.getOrderType();  // Retrieve the order type (Dine In or Take Out)
        double totalPrice = orderItems.stream()
                .mapToDouble(item -> Double.parseDouble(item.getMealPrice().replaceAll("[^\\d.]", "")) * item.getQuantity())
                .sum();

        // Using BufferedWriter for efficient writing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE, true))) {
            writer.write(String.format("%04d: Order Items", orderNumber));
            writer.newLine();
            writer.write("  Order Type: " + orderType);  // Write the order type (Dine In or Take Out)
            writer.newLine();
            for (OrderItem item : orderItems) {
                writer.write("  Food Code: " + item.getFoodCode());
                writer.newLine();
                writer.write("  Meal Name: " + item.getMealName());
                writer.newLine();
                writer.write("  Price: " + item.getMealPrice());
                writer.newLine();
                writer.write("  Quantity: " + item.getQuantity());
                writer.newLine();
            }
            writer.write("  Total Price: " + String.format("%.2f", totalPrice));
            writer.newLine();
            writer.write("  Status: Pending");
            writer.newLine();
            writer.newLine();  // Blank line to separate orders
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        updatePaymentTypeLabel();
    }

    private void updatePaymentTypeLabel() {
        if (paymentType.equals("PayCounter")) {  // Ensure correct string comparison
            Reminder.setText("Pay for your order\nat the cashier");
        } else {
            Reminder.setText("Pay using your card\nat the cashier");
        }
    }

    // Example method to return current order items. You need to replace this with actual implementation
    private List<OrderItem> getCurrentOrderItems() {
        return OrderMenuController.staticOrderItems;  // Assuming you have a static list of order items
    }

    // Mouse event handler for creating an order
    @FXML
    private void CreateOrderClicked(MouseEvent event) {
        // Get the current order items
        List<OrderItem> orderItems = getCurrentOrderItems();

        // Save the order to the file
        saveOrder(orderItems);

        // Reset the order by clearing the static order items list
        OrderMenuController.staticOrderItems.clear();

        // Redirect to the StartMenu.fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/StartMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
