package me.group.cceproject.controllers;

public class OrderItem {
    private String mealName;
    private String mealPrice;
    private String foodCode;  // New property

    public OrderItem(String mealName, String mealPrice, String foodCode) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.foodCode = foodCode;
    }

    // Add getter for foodCode
    public String getFoodCode() {
        return foodCode;
    }

    // Existing getters and setters
    public String getMealName() {
        return mealName;
    }

    public String getMealPrice() {
        return mealPrice;
    }

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