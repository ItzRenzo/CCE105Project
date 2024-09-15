package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainController {

    @FXML
    private void dineinClick(MouseEvent event) {
        loadOrderMenu("Dine In", event);
    }

    @FXML
    private void takeoutClick(MouseEvent event) {
        loadOrderMenu("Take Out", event);
    }

    private void loadOrderMenu(String orderType, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/OrderMenu.fxml"));
            Parent orderMenuRoot = loader.load();

            OrderMenuController orderMenuController = loader.getController();
            orderMenuController.setOrderType(orderType);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene orderMenuScene = new Scene(orderMenuRoot);
            stage.setScene(orderMenuScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading OrderMenu.fxml: " + e.getMessage());
        }
    }
}