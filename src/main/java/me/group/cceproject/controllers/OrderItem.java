package me.group.cceproject.controllers;

public class OrderItem {
    private String mealName;
    private String mealPrice;

    public OrderItem(String mealName, String mealPrice) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
    }

    // Getters
    public String getMealName() {
        return mealName;
    }

    public String getMealPrice() {
        return mealPrice;
    }

    // Setters
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }
}