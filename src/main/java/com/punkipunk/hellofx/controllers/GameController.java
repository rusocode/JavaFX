package com.punkipunk.hellofx.controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import com.punkipunk.hellofx.rendering.Renderer;
import com.punkipunk.hellofx.controls.KeyPolling;
import com.punkipunk.hellofx.models.Entity;
import com.punkipunk.hellofx.animation.GameLoop;

public class GameController implements Initializable {

    public Canvas gameCanvas;
    public AnchorPane gameAnchor;

    KeyPolling keys = KeyPolling.getInstance();

    private final Entity player = new Entity(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/textures/ship.png"))));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialiseCanvas();

        player.setDrawPosition(350, 200);
        player.setScale(0.5f);

        // Antes del GameLoop, le pasa el Canvas para que pueda dibuje en el en cada fotograma
        Renderer renderer = new Renderer(gameCanvas);
        /* En cada fotograma, el renderizador imprimira todas sus entidades en el canvas. Solo tenemos una entidad para este
         * juego, pero a medida que agregas enemigos, recursos y mas, es util hacer una lista. Luego imprimimos cada una usando su
         * imagen almacenada, posicion, rotacion y escala. */
        renderer.addEntity(player);
        renderer.setBackground(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/textures/SpaceBackground.jpg"))));

        // Ejecuta el flujo de renderizado (rendering pipeline)
        new GameLoop() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();
                updatePlayerMovement(secondsSinceLastFrame);
                // Una vez actualizada la posicion del jugador, dibuja el fondo y el jugador en un solo metodo
                renderer.render();
            }
        }.start();

    }

    /**
     * <p>
     * Inicializa el canvas con cambio de tamanio automatico mediante el enlazamiento con el ancho y alto del AnchorPane para
     * garantizar que nunca haya areas en blanco en la ventana de juego.
     */
    private void initialiseCanvas() {
        gameCanvas.widthProperty().bind(gameAnchor.widthProperty());
        gameCanvas.heightProperty().bind(gameAnchor.heightProperty());
    }

    /**
     * Actualiza el movimiento del player.
     *
     * @param frameDuration duracion del frame.
     */
    private void updatePlayerMovement(float frameDuration) {
        if (keys.isDown(KeyCode.W)) player.addThrust(20 * frameDuration);
        else if (keys.isDown(KeyCode.S)) player.addThrust(-20 * frameDuration);
        if (keys.isDown(KeyCode.D)) player.addTorque(120f * frameDuration);
        else if (keys.isDown(KeyCode.A)) player.addTorque(-120f * frameDuration);
        player.update();
    }

}
