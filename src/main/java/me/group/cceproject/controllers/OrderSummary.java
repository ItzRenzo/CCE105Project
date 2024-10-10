package me.group.cceproject.controllers;

public class OrderSummary {
    private String orderNumber;
    private String orderTotal;
    private String orderStatus;

    public OrderSummary(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    // Getters and setters
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public String getOrderTotal() { return orderTotal; }
    public void setOrderTotal(String orderTotal) { this.orderTotal = orderTotal; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
}
