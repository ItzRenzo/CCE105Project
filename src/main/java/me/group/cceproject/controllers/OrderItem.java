package me.group.cceproject.controllers;

public class OrderItem {
    private String mealName;
    private String mealPrice;
    private String foodCode;
    private int quantity;  // New property

    public OrderItem(String mealName, String mealPrice, String foodCode) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.foodCode = foodCode;
        this.quantity = 1;  // Default quantity is 1
    }

    // Add getter and setter for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Existing getters and setters...
    public String getMealName() { return mealName; }
    public String getMealPrice() { return mealPrice; }
    public String getFoodCode() { return foodCode; }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }
}