package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndController {

    String paymentType;

    @FXML
    Text Reminder;

    @FXML
    private void CreateOrderClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/StartMenu.fxml"));
            Parent startMenuRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene startMenuScene = new Scene(startMenuRoot);
            stage.setScene(startMenuScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error returning to start menu: " + e.getMessage());
        }
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        updatePaymentTypeLabel();
    }

    private void updatePaymentTypeLabel() {
        if (paymentType == "PayCounter") {
            Reminder.setText("Pay for your order\n" +
                    "at the cashier");
        } else {
            Reminder.setText("Pay using your card\n" +
                    "at the cashier");
        }
    }
}
