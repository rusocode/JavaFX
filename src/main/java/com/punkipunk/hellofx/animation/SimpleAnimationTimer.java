package com.punkipunk.hellofx.animation;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * <p>
 * AnimationTimer() no corre a un numero fijo de FPS. En su lugar, se sincroniza con la frecuencia de actualizacion del monitor,
 * lo que se conoce como "vsync" (sincronizacion vertical).
 * <p>
 * AnimationTimer en JavaFX ejecuta su metodo {@code handle()} una vez por cada frame de la animacion, lo que se conoce como un
 * "pulso". La velocidad maxima esta limitada por la tasa de refresco de la pantalla, por lo que nunca se ejecutara mas rapido que
 * esta. JavaFX determina la tasa de frames verificando primero la propiedad basada en la tasa de refresco de la pantalla, luego
 * la propiedad de pulso preferida del sistema, y finalmente usa 60 FPS por defecto si nada mas esta definido. Se puede calcular
 * la tasa de frames real extendiendo AnimationTimer y midiendo el tiempo entre llamadas al metodo handle(), guardando el tiempo
 * del ultimo frame y calculando la diferencia para obtener los FPS. Internamente, el Quantum Toolkit maneja la integracion entre
 * la parte de ventanas (“Glass”) y el motor grafico ("Prism"). Esto es relevante para nosotros porque <b><i>Glass Windowing
 * Toolkit</i></b> ejecuta los pulsos de animacion y <b><i>Prism</i></b> maneja la representacion de la ventana, mientras que el
 * {@code AbstractMasterTimer} sincroniza todos los temporizadores del programa. Aunque no se puede acceder directamente a la
 * configuracion interna para cambiar la tasa de frames, se puede medir y monitorear usando la tecnica descrita. En resumen,
 * AnimationTimer se ejecuta tan rapido como la tasa de refresco de la pantalla lo permita, tipicamente con un maximo de 60 FPS,
 * pero puede variar segun el hardware y la configuracion del sistema.
 * <p>
 * Links: <a href="https://edencoding.com/animation-timer-speed/">How fast is the JavaFX animation timer – let’s test it!</a>
 */

public abstract class SimpleAnimationTimer extends AnimationTimer {

    private long lastFrame; // Tiempo del frame anterior en nanosegundos
    private long delta;
    private final IntegerProperty frameRate = new SimpleIntegerProperty(); // Almacena la tasa de frames actual

    public IntegerProperty frameRateProperty() {
        return frameRate;
    }

    /**
     * <p>
     * Se llama en cada frame de la animacion.
     */
    @Override
    public void handle(long now) {
        updateFrameTime(now);
        updateFrameRate();
        tick();
    }

    /**
     * <p>
     * Calcula el tiempo transcurrido (delta) entre el frame actual y el anterior.
     * <p>
     * El delta es crucial para mantener la animacion consistente, independientemente de la velocidad de renderizado.
     *
     * @param now tiempo actual en nanosegundos.
     */
    protected void updateFrameTime(long now) {
        delta = now - lastFrame;
        lastFrame = now; // Actualiza lastFrame con el tiempo actual para el proximo calculo
    }

    /**
     * <p>
     * Actualizada la propiedad frameRate que se muestra en la interfaz de usuario.
     */
    protected void updateFrameRate() {
        // Redondea la tasa de frames actual al entero mas cercano y actualiza la propiedad frameRate con este valor
        frameRate.set((int) Math.round(getFrameRateHertz()));
    }

    /**
     * <p>
     * Calcula la tasa de frames en hertz (cuadros por segundo).
     * <p>
     * En la expresion {@code (1d / delta)} el {@code 1d} no es una division, sino una forma de especificar un numero de punto
     * flotante (double) en Java. Al usar 1d en lugar de simplemente 1, nos aseguramos de que la division se realice en punto
     * flotante, no como una division entera.
     * <p>
     * Para ilustrar la diferencia:
     * <ul>
     * <li>Si {@code delta} es 2.000.000.000 nanosegundos (0.5 FPS):
     * <ul>
     * <li>Con {@code 1 / delta}: el resultado seria 0 (division entera)
     * <li>Con {@code 1d / delta}: el resultado seria 0,0000000005
     * </ul>
     * </ul>
     * Luego, al multiplicar por 1e9, obtenemos:
     * <ul>
     * <li>0 * 1e9 = 0 FPS (incorrecto)
     * <li>0,0000000005 * 1e9 = 0.5 FPS (correcto)
     * </ul>
     * <p>
     * En nuestro caso, si delta es 16.666.666 nanosegundos (lo que equivale a 60 FPS):
     * <pre>{@code
     * (1 / 16.666.666) * 1.000.000.000 = 60 Hz
     * }</pre>
     *
     * @return la cantidad de frames por segundo.
     */
    public double getFrameRateHertz() {
        return (1d / delta) * 1e9;
    }

    public long getDelta() {
        return delta;
    }

    public abstract void tick();

}