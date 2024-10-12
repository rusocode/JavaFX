package com.punkipunk.hellofx.animation;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameLoop extends AnimationTimer {

    private static final double FIXED_TIME_STEP = 1.0f / 60.0f; // 60 actualizaciones por segundo

    private long pauseStart, animationStart, lastFrameTime;
    private boolean paused, activated;
    private final StringProperty fpsProperty = new SimpleStringProperty();

    private long frameCount;
    private long lastFPSTime = System.nanoTime();


    private final GameLoopCallback callback;

    /**
     * <p>
     * Un callback es un concepto importante en programacion, especialmente en el contexto de eventos y operaciones asincronas.
     * Vamos a explicarlo en detalle:
     * <ol>
     * <li>Definicion de Callback: Un callback es una funcion que se pasa como argumento a otra funcion y que se ejecuta despues
     * de que ocurra un evento especifico o cuando se complete una tarea particular.
     * <li>En el contexto del {@code GameLoop}: El callback es una forma de permitir que la clase {@code GameController} defina
     * que debe suceder en cada "tick" del juego, sin que GameLoop necesite conocer los detalles especificos de la logica del
     * juego.
     * <li>Implementacion en el codigo: En esta implementacion, usamos una interfaz funcional para definir el callback, que seria
     * esta interfaz {@code GameLoopCallback}.
     * <li>Como se usa: En la clase GameController, pasamos el metodo {@code tick} como callback al crear el GameLoop:
     * <pre>{@code
     * private void startGameLoop() {
     *     gameLoop = new GameLoop(this::tick);
     * }
     *
     * private void tick(float deltaTime) {
     *     renderer.prepare();
     *     updatePlayerMovement(deltaTime);
     *     renderer.render();
     * }
     * }</pre>
     * <li>Ventajas de usar callbacks:
     * <ul>
     * <li>Flexibilidad: Permite que GameLoop sea mas generico y reutilizable.
     * <li>Separacion de preocupaciones: GameLoop maneja el timing y los FPS, mientras que GameController maneja la logica
     * especifica del juego.
     * <li>Inversion de control: GameLoop no necesita conocer los detalles de como se actualiza el juego, solo llama al callback
     * cuando es necesario.
     * </ul>
     * </ol>
     * <p>
     * En resumen, el callback en el GameLoop es una forma elegante de permitir que la logica especifica del juego (definida en
     * GameController) se ejecute en cada frame, mientras que GameLoop se encarga de manejar el timing y el calculo de FPS. Esto
     * hace que el codigo sea mas modular y facil de mantener.
     */
    public interface GameLoopCallback {

        /**
         * Actualiza el estado del juego para el frame actual.
         * <p>
         * Este metodo es llamado en cada frame de la animacion.
         */
        void tick(float fixedDeltaTime);

    }

    public GameLoop(GameLoopCallback callback) {
        this.callback = callback;
    }

    /**
     * Comprueba si el temporizador esta pausado.
     *
     * @return true si el temporizador esta pausado o false en caso contrario.
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Comprueba si el temporizador esta activado.
     *
     * @return true si el temporizador esta activado o false en caso contrario.
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Pausa el temporizador.
     */
    public void pause() {
        if (!paused && activated) {
            paused = true;
            pauseStart = System.nanoTime();
        }
    }

    /**
     * Reanuda el temporizador.
     */
    public void play() {
        if (paused) {
            paused = false;
            animationStart += (System.nanoTime() - pauseStart);
        }
    }

    /**
     * Inicia el temporizador.
     */
    @Override
    public void start() {
        super.start();
        activated = true;
        paused = false;
        animationStart = System.nanoTime();
    }

    /**
     * Detiene el temporizador y reinicia la duracion.
     */
    @Override
    public void stop() {
        super.stop();
        activated = false;
        paused = false;
    }

    /**
     * La sincronizacion con la tasa de refresco de la pantalla esta siendo manejada implicitamente por AnimationTimer.
     */
    @Override
    public void handle(long now) {

        if (!activated || paused) return;

        callback.tick((float) FIXED_TIME_STEP);

        // Monitorea la cantidad de frames por segundo
        frameCount++;
        if (now - lastFPSTime >= 1_000_000_000) { // 1 segundo
            fpsProperty.set(String.valueOf((int) frameCount));
            frameCount = 0;
            lastFPSTime = now;
        }

    }

    /**
     * Obtiene la propiedad que contiene el valor actual de FPS como una cadena.
     *
     * @return StringProperty que contiene el valor actual de FPS.
     */
    public StringProperty fpsProperty() {
        return fpsProperty;
    }

}