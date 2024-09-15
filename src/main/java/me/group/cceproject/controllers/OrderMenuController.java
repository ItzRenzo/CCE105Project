package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderMenuController {

    @FXML
    private Label orderTypeLabel;

    private String orderType;

    @FXML
    public void initialize() {
        System.out.println("OrderMenuController initialized");
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
        updateOrderTypeLabel();
    }

    private void updateOrderTypeLabel() {
        if (orderTypeLabel != null) {
            orderTypeLabel.setText("( " + orderType + " )");
        } else {
            System.err.println("Unable to set order type label. Label is null.");
        }
    }

    // ... other methods ...
}