package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class CartController {
    @FXML
    private ScrollPane cartScrollPane;

    @FXML
    private Text totalPriceText;

    @FXML
    private VBox cartItemsContainer;

    @FXML
    public void initialize() {
        // Initialize the VBox to hold cart items
        cartItemsContainer = new VBox(10); // 10 pixels spacing between items
        cartItemsContainer.setPadding(new Insets(10));
        cartScrollPane.setContent(cartItemsContainer);

        // Load items from OrderMenuController's static list
        loadCartItems();
    }

    private void loadCartItems() {
        if (OrderMenuController.staticOrderItems != null) {
            cartItemsContainer.getChildren().clear();
            double totalPrice = 0.0;

            for (OrderItem item : OrderMenuController.staticOrderItems) {
                HBox itemContainer = createCartItemContainer(item);
                cartItemsContainer.getChildren().add(itemContainer);

                // Add to total price
                String priceStr = item.getMealPrice().replaceAll("[^\\d.]", "");
                totalPrice += Double.parseDouble(priceStr);
            }

            // Update total price display
            totalPriceText.setText(String.format("₱ %.2f", totalPrice));
        }
    }

    private HBox createCartItemContainer(OrderItem item) {
        HBox container = new HBox(10);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPadding(new Insets(10));
        container.setStyle("-fx-background-color: #f8f8f8; -fx-background-radius: 5;");

        // Item details
        VBox detailsBox = new VBox(5);
        Text nameText = new Text(item.getMealName());
        nameText.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text priceText = new Text(item.getMealPrice());
        priceText.setStyle("-fx-fill: #DB383D;");
        detailsBox.getChildren().addAll(nameText, priceText);

        // Quantity controls
        HBox quantityBox = new HBox(5);
        quantityBox.setAlignment(Pos.CENTER);
        Button decreaseBtn = new Button("-");
        Text quantityText = new Text("1");
        Button increaseBtn = new Button("+");

        // Style the buttons
        String buttonStyle = "-fx-background-color: #FFFFFF; -fx-border-color: #DB383D; " +
                "-fx-border-radius: 5; -fx-min-width: 30; -fx-min-height: 30;";
        decreaseBtn.setStyle(buttonStyle);
        increaseBtn.setStyle(buttonStyle);

        quantityBox.getChildren().addAll(decreaseBtn, quantityText, increaseBtn);

        // Remove button
        Button removeBtn = new Button("Remove");
        removeBtn.setStyle("-fx-background-color: #DB383D; -fx-text-fill: white; " +
                "-fx-border-radius: 5; -fx-background-radius: 5;");

        // Add all components to container
        container.getChildren().addAll(detailsBox, quantityBox, removeBtn);
        HBox.setHgrow(detailsBox, Priority.ALWAYS);

        // Event handlers
        decreaseBtn.setOnAction(e -> updateQuantity(quantityText, -1, item));
        increaseBtn.setOnAction(e -> updateQuantity(quantityText, 1, item));
        removeBtn.setOnAction(e -> removeItem(container, item));

        return container;
    }

    private void updateQuantity(Text quantityText, int change, OrderItem item) {
        int currentQuantity = Integer.parseInt(quantityText.getText());
        int newQuantity = Math.max(1, currentQuantity + change);
        quantityText.setText(String.valueOf(newQuantity));

        // Update price based on quantity
        updateTotalPrice();
    }

    private void removeItem(HBox container, OrderItem item) {
        cartItemsContainer.getChildren().remove(container);
        OrderMenuController.staticOrderItems.remove(item);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double total = 0.0;
        for (OrderItem item : OrderMenuController.staticOrderItems) {
            String priceStr = item.getMealPrice().replaceAll("[^\\d.]", "");
            total += Double.parseDouble(priceStr);
        }
        totalPriceText.setText(String.format("₱ %.2f", total));
    }

    @FXML
    private void backToOrderMenu(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/OrderMenu.fxml"));
            Parent orderMenuRoot = loader.load();

            OrderMenuController controller = loader.getController();
            controller.setOrderType(OrderMenuController.getOrderType());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene orderMenuScene = new Scene(orderMenuRoot);
            stage.setScene(orderMenuScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error returning to order menu: " + e.getMessage());
        }
    }

    @FXML
    private void proceedToCheckout(MouseEvent event) {
        // TODO: Implement checkout functionality
        System.out.println("Proceeding to checkout...");
    }
}