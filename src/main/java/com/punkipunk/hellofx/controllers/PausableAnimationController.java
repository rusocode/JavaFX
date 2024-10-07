package com.punkipunk.hellofx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import com.punkipunk.hellofx.animation.PausableAnimationTimer;

/**
 * Este controlador utiliza PausableAnimationTimer y maneja los eventos de la interfaz de usuario.
 */

public class PausableAnimationController implements Initializable {

    public VBox backgroundPane;
    public Button playPauseSwitch;
    // Componente de tipo Label para mostrar el tiempo de la animacion
    public Label timerClock;

    PausableAnimationTimer timer = new PausableAnimationTimer() {
        @Override
        public void tick(long elapsed) {
        }
    };

    /**
     * <p>
     * Establece un enlace bidireccional entre la propiedad de texto del Label {@code timerClock} y la propiedad
     * {@code animationTime} del objeto timer. Primero accede a la propiedad de texto del Label desde {@code textProperty()}.
     * Luego establece un enlace bidireccional con el metodo {@code bindBidirectional()}. Esto significa que los cambios en
     * cualquiera de las dos propiedades se reflejaran automaticamente en la otra. El objeto
     * {@code new NumberStringConverter("Animation time: ")} es un convertidor que se encarga de transformar el valor numerico de
     * animationTime en una cadena de texto para el Label, y viceversa. El prefijo "Animation time: " se añadira al valor numerico
     * cuando se muestre en el Label.
     * <p>
     * Cuando el valor de animationTime cambia (por ejemplo, durante la animacion), el texto del Label se actualiza
     * automaticamente para reflejar el nuevo valor. Si por alguna razon el texto del Label se modificara directamente (aunque es
     * menos comun), el valor de animationTime se actualizaria en consecuencia.
     * <p>
     * Las ventajas de este enfoque resultan en una sincronizacion automatica entre el modelo de datos (animationTime) y la vista
     * (timerClock). Ademas, elimina la necesidad de actualizar manualmente el Label cada vez que cambia el tiempo de la animacion
     * y proporciona una separacion clara entre la logica de la animacion y la presentacion en la interfaz de usuario.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timerClock.textProperty().bindBidirectional(timer.getAnimationTime(), new NumberStringConverter("Animation time: "));
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

    /**
     * Detiene y reinicia el temporizador.
     */
    public void resetTimer() {
        timer.stop();
        timer.start();
        updatePlayPauseButton(true);
        // event.consume();
    }

    /**
     * Detiene el temporizador.
     */
    public void stopTimer() {
        timer.stop();
        updatePlayPauseButton(false);
        // event.consume();
    }

    /**
     * Alterna entre iniciar, pausar y reanudar el temporizador.
     */
    public void playPauseSwitchPressed() {
        if (!timer.isActivated()) timer.start();
        else if (timer.isPaused()) timer.play();
        else timer.pause();
        updatePlayPauseButton(!timer.isPaused());
    }

    /**
     * Actualiza el estilo del boton segun el estado del temporizador.
     *
     * @param isPlaying estado del temporizador.
     */
    private void updatePlayPauseButton(boolean isPlaying) {
        playPauseSwitch.getStyleClass().removeAll("play", "pause");
        playPauseSwitch.getStyleClass().add(isPlaying ? "pause" : "play");
    }

}