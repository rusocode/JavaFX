package com.craiver.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class Controller {

    @FXML
    Button myButton;
    @FXML
    ImageView myImageView;

    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/2003.png")));

    public void displayImage() {

        myImageView.setImage(image);

    }


}
