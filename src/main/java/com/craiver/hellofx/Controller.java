package com.craiver.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {

    @FXML
    private Label myLabel;
    @FXML
    private TextField myTextField;
    @FXML
    private Button myButton;

    private int age;


    public void submit(ActionEvent event) {
        try {
            age = Integer.parseInt(myTextField.getText());
            if (age >= 18) {
                myLabel.setText("You are now signed up!");
            } else myLabel.setText("You must be 18+");
            System.out.println(age);
        } catch (NumberFormatException e) {
            myLabel.setText("Enter only numbers plz");
        } catch (Exception e) {
            myLabel.setText("Error");
        }
    }


}
