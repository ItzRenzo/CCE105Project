package me.group.cceproject.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    @FXML
    public void IfBurgersClicked(MouseEvent event) {
        System.out.println("Burgers button clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/Burgers.fxml"));
            Parent burgersRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene burgersScene = new Scene(burgersRoot);
            stage.setScene(burgersScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading Burgers.fxml: " + e.getMessage());
        }
    }


}