package com.punkipunk.hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.punkipunk.hellofx.controls.KeyPolling;

import java.util.Objects;

public class SpaceShooter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/GameView.fxml")));
        Scene scene = new Scene(root);

        /* Para configurar nuestra clase KeyPolling, solo necesitamos configurar la escena que queremos rastrear en la clase
         * principal. Como es estatico, podemos acceder a el facilmente en nuestro GameController, y como toda la logica esta
         * correctamente encapsulada en la clase KeyPolling, ¡de lo unico que debemos preocuparnos es de preguntar si nuestras
         * teclas de entrada de usuario estan presionadas actualmente! */
        KeyPolling.getInstance().pollScene(scene);

        stage.setScene(scene);
        stage.setTitle("SpaceShooter");
        stage.getIcons().add(new Image("logo.png"));
        stage.setScene(scene);
        stage.show();

    }

}
