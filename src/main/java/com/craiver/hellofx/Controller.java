package com.craiver.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {

    @FXML
    Button logoutButton;
    @FXML
    AnchorPane scenePane;

    Stage stage;

    public void logout(ActionEvent e) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save configuration before exiting?:");

        // Si se presiono OK
        if (alert.showAndWait().get() == ButtonType.OK) {
            // Obtiene la escena actual en la que se esta trabajando
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("You successfully logged out");
            stage.close();
        }


    }


}
