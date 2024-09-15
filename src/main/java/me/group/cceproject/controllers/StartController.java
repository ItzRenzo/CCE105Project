package me.group.cceproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private void StartClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/group/cceproject/Main.fxml"));
            Parent mainRoot = loader.load();

            Stage stage = null;
            if (event.getSource() instanceof Node) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            }

            if (stage != null) {
                Scene mainScene = new Scene(mainRoot);
                stage.setScene(mainScene);
                stage.show();
            } else {
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Main.fxml: " + e.getMessage());
        }
    }
}
