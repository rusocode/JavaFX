package com.craiver.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Controlador de eventos.
 * <p>
 * La anotacion {@code @FXML} inyecta todos los valores que se encuentran detro del archivo .fxml en el controlador.
 */

public class Scene1Controller {

    @FXML
    TextField nameText;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent e) throws IOException {

        String username = nameText.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene2.fxml"));
        root = loader.load();

        Scene2Controller scene2Controller = loader.getController();
        scene2Controller.displayName(username);

        // root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/scene2.fxml"))));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}