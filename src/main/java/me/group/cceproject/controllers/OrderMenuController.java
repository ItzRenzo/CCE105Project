package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

public class OrderMenuController {
    @FXML
    private Text orderTypeLabel;

    @FXML
    private AnchorPane BurgerPane;

    @FXML
    private AnchorPane ChickenWingsPane;

    private String orderType;

    @FXML
    public void initialize() {
        System.out.println("OrderMenuController initialized");
        // Set initial visibility
        BurgerPane.setVisible(true);
        ChickenWingsPane.setVisible(false);
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
        updateOrderTypeLabel();
    }

    private void updateOrderTypeLabel() {
        if (orderTypeLabel != null) {
            orderTypeLabel.setText("Your Order ( " + orderType + " ):");
        } else {
            System.err.println("Unable to set order type label. Label is null.");
        }
    }

    @FXML
    public void BurgerCategoryClicked(MouseEvent event) {
        BurgerPane.setVisible(true);
        ChickenWingsPane.setVisible(false);
    }

    @FXML
    public void ChickenWingsCategoryClicked(MouseEvent event) {
        BurgerPane.setVisible(false);
        ChickenWingsPane.setVisible(true);
    }

    // Burger Category
    // Yumburger/B1
    @FXML
    public void B1Clicked(MouseEvent event) {
    }

    // Chicken Wings Category
    // Spicy CW/C1
    @FXML
    public void C1Clicked(MouseEvent event) {
    }
}