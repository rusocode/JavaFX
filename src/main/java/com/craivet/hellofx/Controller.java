package com.craivet.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

/**
 * Controlador de eventos.
 * <p>
 * La anotacion {@code @FXML} inyecta todos los valores que se encuentran detro del archivo .fxml en el controlador.
 */

public class Controller {

    @FXML
    private Circle circle;
    private double x, y;

    public void up(ActionEvent e) {
        circle.setCenterY(y -= 10);
    }

    public void down(ActionEvent e) {
        circle.setCenterY(y += 10);
    }

    public void left(ActionEvent e) {
        circle.setCenterX(x -= 10);
    }

    public void right(ActionEvent e) {
        circle.setCenterX(x += 10);
    }

}