package com.craiver.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Controlador de eventos.
 * <p>
 * La anotacion {@code @FXML} inyecta todos los valores que se encuentran detro del archivo .fxml en el controlador.
 */

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchScene1(ActionEvent e) throws IOException {
        root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/scene1.fxml"))));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene2(ActionEvent e) throws IOException {
        root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/scene2.fxml"))));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}