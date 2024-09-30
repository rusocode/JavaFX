package com.punkipunk.hellofx.animation;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class GameLoop extends AnimationTimer {

    private long pauseStart, animationStart, lastFrame;

    private boolean isPaused, isActived;

    private final DoubleProperty animationDuration = new SimpleDoubleProperty();

    /**
     * Comprueba si el temporizador esta pausado.
     *
     * @return true si el temporizador esta pausado o false.
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Comprueba si el temporizador esta activado.
     *
     * @return true si el temporizador esta activado o false.
     */
    public boolean isActived() {
        return isActived;
    }

    /**
     * Obtiene el objeto DoubleProperty.
     *
     * @return el objeto DoubleProperty.
     */
    public DoubleProperty animationDurationProperty() {
        return animationDuration;
    }

    /**
     * Pausa el temporizador.
     */
    public void pause() {
        if (!isPaused && isActived) {
            isPaused = true;
            pauseStart = System.nanoTime();
        }
    }

    /**
     * Reanuda el temporizador.
     */
    public void play() {
        if (isPaused) {
            isPaused = false;
            animationStart += (System.nanoTime() - pauseStart);
        }
    }

    /**
     * Inicia el temporizador.
     */
    @Override
    public void start() {
        super.start();
        isActived = true;
        isPaused = false;
        animationStart = System.nanoTime();
    }

    /**
     * Detiene el temporizador y reinicia la duracion.
     */
    @Override
    public void stop() {
        super.stop();
        isActived = false;
        isPaused = false;
        animationDuration.set(0);
    }

    @Override
    public void handle(long now) {
        if (isActived && !isPaused) {
            long animDuration = now - animationStart;
            animationDuration.set(animDuration / 1e9);

            float secondsSinceLastFrame = (float) ((now - lastFrame) / 1e9);
            lastFrame = now;
            tick(secondsSinceLastFrame);
        }
    }

    public abstract void tick(float secondsSinceLastFrame);

}
