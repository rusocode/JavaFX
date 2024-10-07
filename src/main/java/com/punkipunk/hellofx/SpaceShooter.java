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

        KeyPolling.getInstance().pollScene(scene);

        stage.setScene(scene);
        stage.setTitle("SpaceShooter");
        stage.setResizable(false);
        stage.getIcons().add(new Image("logo.png"));
        stage.setScene(scene);
        stage.show();

    }

}
