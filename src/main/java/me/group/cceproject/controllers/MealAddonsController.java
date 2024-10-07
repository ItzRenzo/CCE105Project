package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MealAddonsController {

    // Drink Panes
    @FXML
    private Pane CokePane;

    @FXML
    private Pane SpritePane;

    @FXML
    private Pane MtnDewPane;

    @FXML
    private Pane ChocolateFudgePane;

    @FXML
    private Pane ChocolatePiePane;

    @FXML
    private Pane RedVelvetPane;

    @FXML
    private Pane NoThanksPane;

    // Size Buttons
    @FXML
    private Button SmallButton;

    @FXML
    private Button MediumButton;

    @FXML
    private Button LargeButton;

    @FXML
    private ImageView MealPicture;

    @FXML
    private Text MealName;

    @FXML
    private Text MealPrice;

    @FXML
    private Button AddtoOrderButton;

    @FXML
    private Button CancelButton;

    private String foodCode;

    // Styles for drink panes
    private final String selectedPaneStyle = "-fx-border-color: #DB383D; -fx-border-width: 2; -fx-border-radius: 5;";
    private final String defaultPaneStyle = ""; // Default style for deselected panes

    // Styles for size buttons
    private final String selectedButtonStyle = "-fx-background-color: #DB383D; -fx-border-color: #DB383D; -fx-border-radius: 5; -fx-text-fill: white; -fx-font-weight: bold;";
    private final String defaultButtonStyle = "-fx-background-color: #FFFFFF; -fx-border-color: #DB383D; -fx-border-radius: 5; -fx-text-fill: black; -fx-font-weight: normal;";

    // Method to initialize default selections
    @FXML
    public void initialize() {
        // Set CokePane and NoThanksPane as default selected panes
        CokePane.setStyle(selectedPaneStyle);
        NoThanksPane.setStyle(selectedPaneStyle);

        // Set SmallButton as the default selected button
        SmallButton.setStyle(selectedButtonStyle);
    }

    public void setMealDetails(String mealName, String mealPrice, String imagePath, String foodCode) {
        // Store the foodCode
        this.foodCode = foodCode;
        MealName.setText(mealName);
        MealPrice.setText(mealPrice);

        try {
            // Build the correct resource path
            String resourcePath = "/me/group/cceproject/images/" + imagePath;
            Image image = new Image(getClass().getResource(resourcePath).toExternalForm());

            if (image.isError()) {
                throw new IllegalArgumentException("Image failed to load from path: " + resourcePath);
            }

            MealPicture.setImage(image);
            System.out.println("Successfully loaded image from: " + resourcePath);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
        }
    }



    // Method for drink selection
    @FXML
    private void CokeClicked(MouseEvent event) {
        selectDrinkPane(CokePane);
    }

    @FXML
    private void SpriteClicked(MouseEvent event) {
        selectDrinkPane(SpritePane);
    }

    @FXML
    private void MtnDewClicked(MouseEvent event) {
        selectDrinkPane(MtnDewPane);
    }

    // Addons Method
    @FXML
    private void CFClicked(MouseEvent event) {
        selectAddonPane(ChocolateFudgePane);
    }

    @FXML
    private void CPClicked(MouseEvent event) {
        selectAddonPane(ChocolatePiePane);
    }

    @FXML
    private void RVClicked(MouseEvent event) {
        selectAddonPane(RedVelvetPane);
    }

    @FXML
    private void NTClicked(MouseEvent event) {
        selectAddonPane(NoThanksPane);
    }

    private void selectDrinkPane(Pane selectedDrinkPane) {
        // Reset all drink panes
        CokePane.setStyle(defaultPaneStyle);
        SpritePane.setStyle(defaultPaneStyle);
        MtnDewPane.setStyle(defaultPaneStyle);

        // Apply selected style to the clicked pane
        selectedDrinkPane.setStyle(selectedPaneStyle);
    }

    private void selectAddonPane(Pane selectedAddonPane) {
        // Reset all addon panes
        ChocolateFudgePane.setStyle(defaultPaneStyle);
        ChocolatePiePane.setStyle(defaultPaneStyle);
        RedVelvetPane.setStyle(defaultPaneStyle);
        NoThanksPane.setStyle(defaultPaneStyle);

        // Apply selected style to the clicked pane
        selectedAddonPane.setStyle(selectedPaneStyle);
    }

    // Method for size selection
    @FXML
    private void SmallButtonClicked(MouseEvent event) {
        selectSize(SmallButton);
    }

    @FXML
    private void MediumButtonClicked(MouseEvent event) {
        selectSize(MediumButton);
    }

    @FXML
    private void LargeButtonClicked(MouseEvent event) {
        selectSize(LargeButton);
    }

    private void selectSize(Button selectedButton) {
        // Reset all size buttons
        SmallButton.setStyle(defaultButtonStyle);
        MediumButton.setStyle(defaultButtonStyle);
        LargeButton.setStyle(defaultButtonStyle);

        // Apply selected style to the clicked button
        selectedButton.setStyle(selectedButtonStyle);
    }
    @FXML
    private void AddtoOrderClicked(MouseEvent event) {
        try {
            String selectedSize = getSelectedSize();
            String selectedDrink = getSelectedDrink();
            String selectedAddon = getSelectedAddon();

            // Calculate final price based on selections
            double basePrice = extractPrice(MealPrice.getText());
            double finalPrice = calculateFinalPrice(basePrice, selectedSize, selectedAddon);

            String completeMealName = String.format("%s\n%s (%s) \n%s",
                    MealName.getText(),
                    selectedDrink,
                    selectedSize,
                    selectedAddon.equals("No Thanks") ? "" : selectedAddon);

            // Format the final price
            String formattedPrice = String.format("₱ %.2f", finalPrice);

            // Add to the order table
            OrderMenuController.addOrderItem(completeMealName, formattedPrice, foodCode);
            System.out.println("Order added: " + completeMealName + " - " + formattedPrice);

            // Load back the OrderMenu.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/OrderMenu.fxml"));
            Parent orderMenuRoot = loader.load();

            // Get the controller and set the order type
            OrderMenuController controller = loader.getController();
            controller.setOrderType(OrderMenuController.getOrderType());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene orderMenuScene = new Scene(orderMenuRoot);
            stage.setScene(orderMenuScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error adding order to table: " + e.getMessage());
        }
    }

    // Helper method to extract price from string (e.g., "₱ 99" -> 99.0)
    private double extractPrice(String priceStr) {
        try {
            return Double.parseDouble(priceStr.replaceAll("[^\\d.]", ""));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing price: " + priceStr);
            return 0.0;
        }
    }

    // Calculate final price based on selections
    private double calculateFinalPrice(double basePrice, String size, String addon) {
        double finalPrice = basePrice;

        // Add size premium
        switch (size) {
            case "Medium":
                finalPrice += 20; // Add ₱20 for medium
                break;
            case "Large":
                finalPrice += 40; // Add ₱40 for large
                break;
        }

        // Add addon price
        switch (addon) {
            case "Chocolate Fudge":
                finalPrice += 39;
                break;
            case "Chocolate Pie":
                finalPrice += 49;
                break;
            case "Red Velvet":
                finalPrice += 59;
                break;
        }

        return finalPrice;
    }

    private String getSelectedSize() {
        if (SmallButton.getStyle().contains("DB383D")) return "Small";
        if (MediumButton.getStyle().contains("DB383D")) return "Medium";
        if (LargeButton.getStyle().contains("DB383D")) return "Large";
        return "Small"; // Default
    }

    private String getSelectedDrink() {
        if (CokePane.getStyle().contains("DB383D")) return "Coke";
        if (SpritePane.getStyle().contains("DB383D")) return "Sprite";
        if (MtnDewPane.getStyle().contains("DB383D")) return "Mountain Dew";
        return "Coke"; // Default
    }

    private String getSelectedAddon() {
        if (ChocolateFudgePane.getStyle().contains("DB383D")) return "Chocolate Fudge";
        if (ChocolatePiePane.getStyle().contains("DB383D")) return "Chocolate Pie";
        if (RedVelvetPane.getStyle().contains("DB383D")) return "Red Velvet";
        if (NoThanksPane.getStyle().contains("DB383D")) return "No Thanks";
        return "No Thanks"; // Default
    }

    @FXML
    private void CancelClicked(MouseEvent event) {
        try {
            // Load back the OrderMenu.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/OrderMenu.fxml"));
            Parent orderMenuRoot = loader.load();

            // Get the controller and set the order type
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
