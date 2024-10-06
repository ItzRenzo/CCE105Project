package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
        String mealName = "Yumburger";
        String mealPrice = "₱ 99";
        String imagePath = "B1.png"; // Just the filename is fine now

        System.out.println("B1 Clicked: Loading Meal Addons...");
        loadMealAddons(mealName, mealPrice, imagePath, event);
    }

    // Chicken Wings Category
    // Spicy Chicken Wings/C1
    @FXML
    public void C1Clicked(MouseEvent event) {
        String mealName = "Spicy Chicken Wings";
        String mealPrice = "₱ 159";
        String imagePath = "Sweet__Spicy.png"; // Updated to match your actual image name

        loadMealAddons(mealName, mealPrice, imagePath, event);
    }

    private void loadMealAddons(String mealName, String mealPrice, String imagePath, MouseEvent event) {
        try {
            // Load MealAddons.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/MealAddons.fxml"));
            Parent mealAddonsRoot = loader.load();

            // Get the MealAddonsController
            MealAddonsController mealAddonsController = loader.getController();

            // Pass meal details to MealAddonsController
            mealAddonsController.setMealDetails(mealName, mealPrice, imagePath);

            // Switch scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene mealAddonsScene = new Scene(mealAddonsRoot);
            stage.setScene(mealAddonsScene);
            stage.show();

            System.out.println("Meal Addons loaded successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading MealAddons.fxml: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error setting meal details: " + e.getMessage());
        }
    }
}