package com.punkipunk.hellofx;

import java.util.Objects;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Se puede pausar un temporizador de animacion extendiendo la clase AnimationTimer para registrar las marcas de tiempo o la
 * duracion de un evento de pausa. Al reiniciar la animacion, la duracion total de la pausa se resta de cada marca de tiempo para
 * dar la apariencia de una animacion continua.
 */

public class App2 extends Application {

    private double xOffset, yOffset;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainView.fxml")));

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            event.consume();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
            event.consume();
        });

        Scene scene = new Scene(root);
        scene.getRoot().setEffect(new DropShadow(10, Color.rgb(100, 100, 100)));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("logo.png"));
        stage.setScene(scene);
        stage.show();

    }

}
