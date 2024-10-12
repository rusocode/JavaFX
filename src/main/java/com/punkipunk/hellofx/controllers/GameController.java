package com.punkipunk.hellofx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import com.punkipunk.hellofx.rendering.Renderer;
import com.punkipunk.hellofx.controls.KeyPolling;
import com.punkipunk.hellofx.models.Entity;
import com.punkipunk.hellofx.animation.GameLoop;
import com.punkipunk.hellofx.utils.Utils;

public class GameController implements Initializable {

    private static final float PLAYER_INITIAL_X = 350;
    private static final float PLAYER_INITIAL_Y = 200;
    private static final float PLAYER_INITIAL_SCALE = 0.5f;
    private static final float THRUST = 20;
    private static final float ROTATION = 90;

    @FXML
    public Canvas gameCanvas;
    @FXML
    public AnchorPane gameAnchor;
    @FXML
    public Label fpsLabel;

    /* Para configurar la clase KeyPolling, solo necesitamos configurar la escena que queremos rastrear en la clase principal.
     * Como es estatica, podemos acceder a el facilmente en nuestro GameController, y como toda la logica esta correctamente
     * encapsulada en la clase KeyPolling, ¡de lo unico que debemos preocuparnos es de preguntar si nuestras teclas de entrada de
     * usuario estan presionadas actualmente! */
    private KeyPolling keys;

    /* El renderizador imprime todas las entidades en el canvas en cada fotograma. Aunque en este juego solo hay una entidad, es
     * util crear una lista para cuando se agregen mas elementos como enemigos o recursos. El proceso consiste en imprimir cada
     * entidad utilizando su imagen almacenada, junto con su posicion, rotacion y escala correspondientes. */
    private Entity player;
    private Renderer renderer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        keys = KeyPolling.getInstance();
        player = new Entity(Utils.loadImage("/textures/ship.png"));
        renderer = new Renderer(gameCanvas); // Le pasa el canvas antes del GameLoop para que pueda dibujar en el en cada fotograma

        initCanvas();
        initPlayer();
        initRenderer();
        startGameLoop();
    }

    /**
     * <p>
     * Inicializa el canvas con cambio de tamaño automatico mediante el enlazamiento con el ancho y alto del AnchorPane para
     * garantizar que nunca haya areas en blanco en la ventana de juego.
     */
    private void initCanvas() {
        gameCanvas.widthProperty().bind(gameAnchor.widthProperty());
        gameCanvas.heightProperty().bind(gameAnchor.heightProperty());
    }

    private void initPlayer() {
        player.setPosition(PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
        player.setScale(PLAYER_INITIAL_SCALE);
    }

    private void initRenderer() {
        renderer.addEntity(player);
        renderer.setBackground(Utils.loadImage("/textures/SpaceBackground.jpg"));
    }

    /**
     * Ejecuta el flujo de renderizado (rendering pipeline).
     */
    private void startGameLoop() {
        GameLoop gameLoop = new GameLoop(this::tick);
        // El fpsLabel se vincula a la propiedad FPS del GameLoop (asi de sencillo!)
        fpsLabel.textProperty().bind(gameLoop.fpsProperty().concat(" FPS"));
        gameLoop.start();
    }

    private void tick(float fixedDeltaTime) {
        renderer.prepare();
        updatePlayerMovement(fixedDeltaTime);
        renderer.render();
    }

    /**
     * Actualiza el movimiento del player.
     *
     * @param fixedDeltaTime tiempo fijo entre actualizaciones de fisica.
     */
    private void updatePlayerMovement(float fixedDeltaTime) {
        if (keys.isPressed(KeyCode.W)) player.applyThrust(THRUST * fixedDeltaTime);
        else if (keys.isPressed(KeyCode.S)) player.applyThrust(-THRUST * fixedDeltaTime);
        if (keys.isPressed(KeyCode.D)) player.applyRotation(ROTATION * fixedDeltaTime);
        else if (keys.isPressed(KeyCode.A)) player.applyRotation(-ROTATION * fixedDeltaTime);
        player.update();
    }

}
