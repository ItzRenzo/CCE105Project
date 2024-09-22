package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class BurgerController {

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
}
