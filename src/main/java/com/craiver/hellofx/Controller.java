package com.craiver.hellofx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Slider volumen;
    @FXML
    private Label label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText(String.valueOf((int )volumen.getValue()));
        volumen.valueProperty().addListener((observableValue, number, t1) -> label.setText(String.valueOf((int)volumen.getValue())));
    }
}
