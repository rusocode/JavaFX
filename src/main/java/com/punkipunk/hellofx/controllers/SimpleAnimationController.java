package com.punkipunk.hellofx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import com.punkipunk.hellofx.animation.SimpleAnimationTimer;

public class SimpleAnimationController implements Initializable {

    public VBox backgroundPane;
    public Label fpsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SimpleAnimationTimer timer = new SimpleAnimationTimer() {
            @Override
            public void tick() {
            }
        };
        timer.start();
        /* Vincula bidirecionalmente la propiedad de texto del FPSLabel con la propiedad frameRate del temporizador, usando un
         * NumberStringConverter para formatear el texto como "FPS: X". */
        fpsLabel.textProperty().bindBidirectional(timer.frameRateProperty(), new NumberStringConverter("FPS: "));
    }

    @FXML
    private void handleGitButtonClicked(ActionEvent event) {
        new Application() {
            @Override
            public void start(Stage stage) {
            }
        }.getHostServices().showDocument("https://github.com/rusocode/javafx/");
        event.consume();
    }

    @FXML
    private void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

}
