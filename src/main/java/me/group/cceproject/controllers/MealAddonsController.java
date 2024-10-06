package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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

    public void setMealDetails(String mealName, String mealPrice, String imagePath) {
        // Set the meal name and price
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
}
