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
    public void B1Clicked(MouseEvent event) { loadMealAddons("Dine In", event);;
    }

    // Chicken Wings Category
    // Spicy CW/C
    @FXML
    public void C1Clicked(MouseEvent event) { loadMealAddons("Dine In", event);
    }

        private void loadMealAddons(String Addons, MouseEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/MealAddons.fxml"));
                Parent MealAddonsRoot = loader.load();

                MealAddonsController AddonsController = loader.getController();
                AddonsController.setAddons(Addons);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene MealAddonsScene = new Scene(MealAddonsRoot);
                stage.setScene(MealAddonsScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading MealAddons.fxml: " + e.getMessage());
            }

    }
}