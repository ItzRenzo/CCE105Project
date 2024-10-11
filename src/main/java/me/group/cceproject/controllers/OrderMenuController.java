package me.group.cceproject.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderMenuController {
    @FXML
    private Text orderTypeLabel;
    @FXML
    private Button Cancel;
    @FXML
    private AnchorPane BurgerPane;

    @FXML
    private AnchorPane ChickenWingsPane;

    private String orderType;

    @FXML
    private TableView<OrderItem> OrderTable;

    @FXML
    private TableColumn<OrderItem, String> MealNameTable;

    @FXML
    private TableColumn<OrderItem, String> MealPriceTable;

    @FXML
    private TableColumn<OrderItem, Integer> QuantityTable;

    @FXML
    private Text TotalPriceText;

    static ObservableList<OrderItem> staticOrderItems;

    private static OrderMenuController instance;

    private static String staticOrderType;

    @FXML
    public void initialize() {

        // Set up the table columns
        MealNameTable.setCellValueFactory(new PropertyValueFactory<>("mealName"));
        MealPriceTable.setCellValueFactory(new PropertyValueFactory<>("mealPrice"));
        QuantityTable.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Store instance for access from MealAddonsController
        instance = this;

        // Initialize the ObservableList if it's null
        if (staticOrderItems == null) {
            staticOrderItems = FXCollections.observableArrayList();
        }

        // Set the items to the table
        OrderTable.setItems(staticOrderItems);

        // Print current items for debugging
        System.out.println("Current items in table: " + staticOrderItems.size());
        for (OrderItem item : staticOrderItems) {
            System.out.println("Item: " + item.getMealName() + " - " + item.getMealPrice());
        }

        // Add listener to update total price when items change
        staticOrderItems.addListener((ListChangeListener.Change<? extends OrderItem> change) -> {
            updateTotalPrice();
        });

        // Update total price initially
        updateTotalPrice();

        // Restore order type if it exists
        if (staticOrderType != null && orderTypeLabel != null) {
            orderTypeLabel.setText("Your Order ( " + staticOrderType + " ):");
        }
        // Set initial visibility
        BurgerPane.setVisible(true);
        ChickenWingsPane.setVisible(false);
    }

    // Static method to add order item
    public static void addOrderItem(String mealName, String mealPrice, String foodCode, int quantity) {
        System.out.println("Adding order item: " + mealName + " - " + mealPrice + " - " + foodCode);
        if (staticOrderItems == null) {
            staticOrderItems = FXCollections.observableArrayList();
        }
        staticOrderItems.add(new OrderItem(mealName, mealPrice, foodCode, quantity));
    }

    private void updateTotalPrice() {
        double total = 0.0;
        for (OrderItem item : staticOrderItems) {
            String priceStr = item.getMealPrice().replaceAll("[^\\d.]", "");
            double itemPrice = Double.parseDouble(priceStr);
            total += itemPrice * item.getQuantity();
        }
        TotalPriceText.setText(String.format("₱ %.2f", total));
    }

    public static OrderMenuController getInstance() {
        return instance;
    }

    public void setOrderType(String orderType) {
        staticOrderType = orderType; // Store in static variable
        updateOrderTypeLabel();
    }

    private void updateOrderTypeLabel() {
        if (orderTypeLabel != null) {
            orderTypeLabel.setText("Your Order ( " + staticOrderType + " ):");
        } else {
            System.err.println("Unable to set order type label. Label is null.");
        }
    }

    // Get the current order type
    public static String getOrderType() {
        return staticOrderType;
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
        String imagePath = "B1.png";
        String foodCode = "B1";

        loadMealAddons(mealName, mealPrice, imagePath, foodCode, event);
    }

    // Chicken Wings Category
    // Spicy Chicken Wings/C1
    @FXML
    public void C1Clicked(MouseEvent event) {
        String mealName = "Spicy Chicken Wings";
        String mealPrice = "₱ 159";
        String imagePath = "C1.png";
        String foodCode = "C1";

        loadMealAddons(mealName, mealPrice, imagePath, foodCode, event);
    }

    private void loadMealAddons(String mealName, String mealPrice, String imagePath, String foodCode, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/MealAddons.fxml"));
            Parent mealAddonsRoot = loader.load();

            MealAddonsController mealAddonsController = loader.getController();
            // Pass the foodCode along with other details
            mealAddonsController.setMealDetails(mealName, mealPrice, imagePath, foodCode);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene mealAddonsScene = new Scene(mealAddonsRoot);
            stage.setScene(mealAddonsScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading MealAddons.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void PayforOrderClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/PayOrder.fxml"));
            Parent payOrderRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene payOrderScene = new Scene(payOrderRoot);
            stage.setScene(payOrderScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error returning to pay order menu: " + e.getMessage());
        }
    }

    @FXML
    private void ViewCartClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/ShoppingCart.fxml"));
            Parent shopCartRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene shopCartScene = new Scene(shopCartRoot);
            stage.setScene(shopCartScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error going to shopping cart: " + e.getMessage());
        }
    }

    public void CancelClicked(MouseEvent event) throws IOException {

        if (staticOrderItems != null) {
            staticOrderItems.clear(); // This clears the table
        }

        // Load the main screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/Main.fxml"));
        Parent mainRoot = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene mainScene = new Scene(mainRoot);
        stage.setScene(mainScene);
        stage.show();
    }




}

