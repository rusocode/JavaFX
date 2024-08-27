package com.craiver.hellofx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class Controller implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ChoiceBox<String> choiceBox;


    private final String[] food = {"pizza", "sushi", "empanada de carne"};

    public void getFood(ActionEvent e) {
        label.setText(choiceBox.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(food);
        choiceBox.setOnAction(this::getFood);
    }


}
