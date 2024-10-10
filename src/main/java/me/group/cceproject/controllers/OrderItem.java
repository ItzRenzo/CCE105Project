package me.group.cceproject.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderItem {
    private final SimpleStringProperty mealName;
    private final SimpleStringProperty mealPrice;
    private final SimpleStringProperty foodCode;
    private final SimpleIntegerProperty quantity;

    public OrderItem(String mealName, String mealPrice, String foodCode, int quantity) {
        this.mealName = new SimpleStringProperty(mealName);
        this.mealPrice = new SimpleStringProperty(mealPrice);
        this.foodCode = new SimpleStringProperty(foodCode);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    // Getters
    public String getMealName() { return mealName.get(); }
    public String getMealPrice() { return mealPrice.get(); }
    public String getFoodCode() { return foodCode.get(); }
    public int getQuantity() { return quantity.get(); }

    // Setters
    public void setMealName(String mealName) {
        this.mealName.set(mealName);
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice.set(mealPrice);
    }

    public void setFoodCode(String foodCode) {
        this.foodCode.set(foodCode);
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    // Property getters
    public SimpleStringProperty mealNameProperty() { return mealName; }
    public SimpleStringProperty mealPriceProperty() { return mealPrice; }
    public SimpleStringProperty foodCodeProperty() { return foodCode; }
    public SimpleIntegerProperty quantityProperty() { return quantity; }
}