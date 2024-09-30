package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MealAddonsController {

    @FXML
    void CheeseBurgerClicked(MouseEvent event) {
        System.out.println("Cheese Burger clicked");
    }

    @FXML
    void KenBurgerClicked(MouseEvent event) {
        System.out.println("Ken Burger clicked");
    }

    @FXML
    void WhopperClicked(MouseEvent event) {
        System.out.println("Whopper clicked");
    }

    private String Addons;


    public void setAddons(String addons) {
        this.Addons = Addons;
    }

    public String getAddons() {
        return Addons;
    }
}
