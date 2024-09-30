package com.punkipunk.hellofx.controllers;

import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * <p>
 * Controla el entorno, añadiendo funcionalidad a los elementos definidos en el archivo FXML. En el entorno web, es el equivalente
 * a JavaScript.
 * <p>
 * Tambien se puede utilizar para agregar elementos personalizados, aunque es un poco menos limpio.
 */

public class Controller {

    // La anotacion {@code @FXML} inyecta todos los valores que se encuentran detro del archivo .fxml en el controlador
    @FXML
    private Button btnNewGame;
    @FXML
    private Button btnLoadGame;
    @FXML
    private Button btnQuit;

    @FXML
    private VBox mainMenu;

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
        String fontPath = "/fonts/Teeny Tiny Pixls.ttf";
        InputStream input = getClass().getResourceAsStream(fontPath);
        if (input == null) System.err.println("Could not find font file: " + fontPath);
        else {
            Font font = Font.loadFont(input, fontSize);
            if (font != null) System.out.println("The font " + font.getName() + " loaded successfully!");
            else System.err.println("Could not load font from: " + fontPath);
        }
    }

    /**
     * <p>
     * JavaFX tiene dos tipos de {@code FontSmoothingTypes} diferentes, <b>GRAY</b> y <b>LCD</b>. De forma predeterminada, JavaFX
     * usa LCD para mostrar texto (esto solo es cierto hasta un cierto tamaño de fuente, pero no se el limite exacto). Si utiliza
     * el objeto de texto dentro del boton, puede configurar el tipo de suavizado en su codigo fuente con
     * {@code text.setFontSmoothingType(FontSmoothingType.GRAY);}, generalmente el gris parece tener un efecto mas fuerte.
     */
    private void applySmoothing() {
        BoxBlur blur = new BoxBlur(); // Desenfoque
        blur.setHeight(3);
        blur.setWidth(3);
        DropShadow dropShadow = new DropShadow(10, Color.BLACK); // Sombra paralela
        InnerShadow innerShadow = new InnerShadow(10, Color.BLACK); // Sombra interna
        // Crea un Text node con el mismo contenido que el boton
        Text text = new Text(btnNewGame.getText());
        text.setFont(btnNewGame.getFont());
        // text.setFontSmoothingType(FontSmoothingType.GRAY);
        // text.setFill(btnIniciarAventura.getTextFill());

        // Aplicar un ligero desenfoque gaussiano
        // text.setEffect(new GaussianBlur(0.68));
        // Aplicar suavizado al Text node
        // text.setSmooth(true);

        // Reemplaza el texto del boton con el Text node
        btnNewGame.setGraphic(text);
        // btnIniciarAventura.setText(null);
    }

}
