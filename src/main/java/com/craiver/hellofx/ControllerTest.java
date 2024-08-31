package com.craiver.hellofx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ControllerTest {

    @FXML
    private Button btnNewGame;

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
