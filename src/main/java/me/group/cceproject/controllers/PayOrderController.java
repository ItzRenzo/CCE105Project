package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PayOrderController {

    @FXML
    private void PayCounterClicked(MouseEvent event) {
        PaymentType("PayCounter", event);
    }

    @FXML
    private void PayCardClicked(MouseEvent event) {
        PaymentType("PayCard", event);
    }

    private void PaymentType(String paymentType, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/End.fxml"));
            Parent endRoot = loader.load();

            EndController endController = loader.getController();
            endController.setPaymentType(paymentType);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene endScene = new Scene(endRoot);
            stage.setScene(endScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading End.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void BackClicked(MouseEvent event) {
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

}
