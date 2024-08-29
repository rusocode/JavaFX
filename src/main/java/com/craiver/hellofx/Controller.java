package com.craiver.hellofx;

import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * Controlador de eventos que maneja la logica de la interfaz y las interacciones del usuario.
 * <p>
 * La anotacion {@code @FXML} inyecta todos los valores que se encuentran detro del archivo .fxml en el controlador.
 */

public class Controller {

    @FXML
    public Button button;

    public void iniciarAventura() {
        System.out.println("Aventura iniciada!");
    }

    /**
     * <p>
     * Este metodo es parte del ciclo de vida de JavaFX y se llama automaticamente cuando se carga el FXML, siempre que este
     * correctamente anotado con {@code @FXML}.
     */
    @FXML
    private void initialize() {
        System.out.println("Initializing controller...");
        loadFont();
    }

    private void loadFont() {
        /* Especificar el tamañio de la fuente desde CSS tiene mayor prioridad que especificarlo desde JavaFX con el metodo
         * loadFont(). Por lo tanto seria innecesario especificar el tamaño desde aca, pero como el metodo loadFont de la clase
         * Font admite dos parametros, no es posible omitirlo. */
        int fontSize = 24;
        String fontPath = "/medieval2.ttf";  // El "/" al principio indica que debe buscar desde la raiz de la carpeta resources
        InputStream input = getClass().getResourceAsStream(fontPath);
        if (input == null) System.err.println("Could not find font file: " + fontPath);
        else {
            Font font = Font.loadFont(input, fontSize);
            if (font != null) System.out.println("The font " + font.getName() + " loaded successfully!");
            else System.err.println("Could not load font from: " + fontPath);
        }
    }

}
