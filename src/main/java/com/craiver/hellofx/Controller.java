package com.craiver.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    @FXML
    private Label label;
    @FXML
    private CheckBox checkBox;
    @FXML
   private ImageView imageView;

   Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sword.png")));
    Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/axe.png")));

    public void change(ActionEvent event) {
        if (checkBox.isSelected()){

            label.setText("ON");
            System.out.println("ON");
            imageView.setImage(image1);
        }
        else{
            label.setText("0FF");
            System.out.println("OFF");
            imageView.setImage(image2);
        }
    }


}
