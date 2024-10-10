package me.group.cceproject.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPanelController {

    @FXML
    private TextField Username; // FXML ID for the TextField
    @FXML
    private PasswordField Password; // FXML ID for the PasswordField
    @FXML
    private Button Login;

    @FXML


    // Helper method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void AdminLoad(String AdminType, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/AdminMain.fxml"));
            Parent AdminRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene AdminScene = new Scene(AdminRoot);
            stage.setScene(AdminScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading OrderMenu.fxml: " + e.getMessage());
        }
    }


    public void loginclicked(MouseEvent event) {



            String username = Username.getText(); // Get the text from the Username field
            String password = Password.getText(); // Get the text from the Password field

            // Simple login validation (you can replace this with your own logic)
            if (username.equals("admin") && password.equals("password")) { // Replace with your validation logic
                showAlert("Login Successful", "Welcome, " + username + "!");
                AdminLoad("", event);
            } else {
                showAlert("Login Failed", "Invalid username or password.");

        }
    }
}
