package com.craiver.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button button;
    @FXML
    private Label label;

    private BigDecimal progress = new BigDecimal(String.format("%.2f", 0.0).replace(",", "."));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progressBar.setStyle("-fx-accent: #00FF00;");
    }

    public void increaseProgress(ActionEvent event) {
        if (progress.doubleValue() < 1) {
            progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 0.1).replace(",", "."));
            progressBar.setProgress(progress.doubleValue());
            label.setText(progress.doubleValue() * 100 + "%");
        }
    }


}
