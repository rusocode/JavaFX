package com.craiver.hellofx;

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
        // button.setOnAction(event -> System.out.println("¡La aventura comienza!"));
    }

    /**
     * <p>
     * Este metodo es parte del ciclo de vida de JavaFX y se llama automaticamente cuando se carga el FXML, siempre que este
     * correctamente anotado con {@code @FXML}.
     */
    @FXML
    private void initialize() {
        System.out.println("Inicializando el controlador...");
        // loadAndApplyFont();
    }

    /* String fontPath = "/medieval.ttf";
    InputStream is = getClass().getResourceAsStream(fontPath);
        if (is == null) System.err.println("No se pudo encontrar el archivo de fuente: " + fontPath);
        else {
        Font font = Font.loadFont(is, 14);
        if (font == null) System.err.println("No se pudo cargar la fuente desde: " + fontPath);
        else System.out.println("Fuente cargada exitosamente: " + font.getName());
    } */

    private void loadAndApplyFont() {
        String fontPath = "/medieval.ttf";  // El "/" al principio indica que debe buscar desde la raiz de la carpeta de resources
        try {
            Font medievalFont = Font.loadFont(getClass().getResourceAsStream(fontPath), 18);
            if (medievalFont != null) {
                button.setFont(medievalFont);
                System.out.println("Fuente cargada y aplicada exitosamente: " + medievalFont.getName());
            } else {
                System.err.println("No se pudo cargar la fuente desde: " + fontPath);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar la fuente: " + e.getMessage());
        }
    }

}
