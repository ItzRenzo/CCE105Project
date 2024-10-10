package me.group.cceproject.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderSummary {
    private final StringProperty orderNumber;
    private final StringProperty orderTotal;
    private final StringProperty orderStatus;

    public OrderSummary(String orderNumber) {
        this.orderNumber = new SimpleStringProperty(orderNumber);
        this.orderTotal = new SimpleStringProperty();
        this.orderStatus = new SimpleStringProperty();
    }

    public String getOrderNumber() {
        return orderNumber.get();
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber.set(orderNumber);
    }

    public StringProperty orderNumberProperty() {
        return orderNumber;
    }

    public String getOrderTotal() {
        return orderTotal.get();
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal.set(orderTotal);
    }

    public StringProperty orderTotalProperty() {
        return orderTotal;
    }

    public String getOrderStatus() {
        return orderStatus.get();
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus.set(orderStatus);
    }

    public StringProperty orderStatusProperty() {
        return orderStatus;
    }
}

