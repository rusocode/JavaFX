package com.craiver.hellofx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class Controller implements Initializable {

    @FXML
    private ListView<String> listView;
    @FXML
    private Label label;

    private final String[] food = {"locro", "asado", "milas"};

    String currentFood;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(food);
        // Agrega un oyente al componente ListView
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentFood = listView.getSelectionModel().getSelectedItem();
                label.setText(currentFood);
            }
        });
    }

}
