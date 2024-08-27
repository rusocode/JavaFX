package com.craiver.hellofx;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * <h3>JavaFX</h3>
 * <p>
 * JavaFX es una plataforma de software que combina caracteristicas de una biblioteca y un framework, diseñada para el desarrollo
 * de aplicaciones de escritorio, aplicaciones web ricas (RIAs) e incluso algunas aplicaciones moviles utilizando Java. Sus
 * principales aspectos son:
 * <ol>
 * <li>Proposito:
 * <ul>
 * <li>Creacion de interfaces graficas de usuario (GUI) modernas y ricas en contenido.
 * <li>Desarrollo de aplicaciones multiplataforma con una sola base de codigo.
 * </ul>
 * <li>Caracteristicas principales:
 * <ul>
 * <li>Graficos 2D y 3D de alto rendimiento.
 * <li>Soporte para audio y video.
 * <li>Integracion web mediante WebView.
 * <li>Uso de CSS para estilos.
 * <li>FXML para definicion declarativa de interfaces.
 * <li>Animaciones y efectos visuales.
 * </ul>
 * <li>Arquitectura:
 * <ul>
 * <li>Basada en un modelo de grafo de escena para la construccion de interfaces.
 * <li>Utiliza un patron MVC (Modelo-Vista-Controlador) para la organizacion del codigo.
 * </ul>
 * <li>Componentes:
 * <ul>
 * <li>Proporciona una amplia gama de controles y layouts predefinidos.
 * <li>Permite la creacion de componentes personalizados.
 * </ul>
 * <li>Tecnologias relacionadas:
 * <ul>
 * <li>Se integra con Java Standard Edition (Java SE).
 * <li>Puede utilizarse junto con otras tecnologias Java como Spring, Hibernate, etc.
 * </ul>
 * <li>Desarrollo y mantenimiento:
 * <ul>
 * <li>Originalmente desarrollado por Sun Microsystems, ahora mantenido por Oracle y la comunidad OpenJFX.
 * <li>Sigue un ciclo de lanzamiento regular con versiones LTS (soporte a largo plazo).
 * </ul>
 * <li>Casos de uso:
 * <ul>
 * <li>Aplicaciones de escritorio empresariales.
 * <li>Herramientas de visualizacion de datos.
 * <li>Juegos 2D y aplicaciones multimedia.
 * <li>Prototipado rapido de interfaces de usuario.
 * </ul>
 * </ol>
 * <p>
 * JavaFX se posiciona como una solucion moderna y flexible para el desarrollo de aplicaciones graficas en Java, ofreciendo
 * capacidades avanzadas de interfaz de usuario y multimedia.
 * <h4>Capas del JavaFX</h4>
 * <p>
 * JavaFX utiliza una estructura jerarquica para organizar los elementos de la interfaz de usuario. En el nivel superior se
 * encuentra el {@code Stage}, que es similar a un JFrame en Swing y representa la ventana principal de la aplicacion. Dentro del
 * Stage se aloja la {@code Scene}, que actua como una superficie de dibujo donde se colocan todos los elementos visuales,
 * comparable a un JPanel pero con capacidades mas avanzadas. La Scene contiene el <b>Scene Graph</b>, que es un arbol jerarquico
 * de nodos que representa la estructura de la interfaz de usuario. Este arbol comienza con un <i>root node</i>, que suele ser un
 * contenedor como Group o Pane. A partir de este nodo raiz, se ramifican los <i>branch nodes</i>, que pueden contener otros nodos
 * y ayudan a organizar la estructura, como VBox, HBox o GridPane. Finalmente, en las puntas del arbol se encuentran los <i>leaf
 * nodes</i>, que representan los componentes finales de la interfaz como botones, etiquetas o campos de texto. Esta estructura
 * permite una organizacion eficiente y flexible de los elementos de la interfaz, facilitando su manipulacion, estilizado y la
 * gestion de eventos en aplicaciones JavaFX.
 * <pre>{@code
 * Stage
 * └── Scene
 *     └── Root Node (ej. Pane)
 *         ├── Branch Node (ej. HBox)
 *         │   ├── Leaf Node (ej. Button)
 *         │   └── Leaf Node (ej. TextField)
 *         └── Branch Node (ej. GridPane)
 *             ├── Leaf Node (ej. Label)
 *             └── Leaf Node (ej. ImageView)
 * }</pre>
 * <p>
 * Puedes pensar esto como un sandwich, en donde Stage es el pan, Scene la carne y todos los nodos son aderezos y condimentos.
 * <h3>FXML</h3>
 * <p>
 * El archivo FXML en JavaFX es un formato de archivo basado en XML utilizado para definir la estructura y el diseño de las
 * interfaces de usuario. FXML proporciona una forma declarativa de describir la interfaz grafica de una aplicacion JavaFX,
 * separando la definicion de la interfaz de usuario de la logica de la aplicacion.
 * <p>
 * Caracteristicas clave del FXML:
 * <ol>
 * <li><b>Separacion de preocupaciones</b>: Permite separar la definicion de la interfaz de usuario de la logica de la aplicacion,
 * facilitando el mantenimiento y la colaboracion entre diseñadores y desarrolladores.
 * <li><b>Sintaxis basada en XML</b>: Utiliza una estructura similar a HTML, lo que lo hace intuitivo para quienes estan
 * familiarizados con lenguajes de marcado.
 * <li><b>Vinculacion con controladores</b>: Permite vincular elementos de la interfaz con metodos en una clase controladora Java,
 * facilitando la gestion de eventos y la logica de la aplicacion.
 * <li><b>Uso de estilos CSS</b>: Soporta la aplicacion de estilos CSS directamente en el archivo FXML o mediante hojas de estilo
 * externas.
 * <li><b>Reutilizacion de componentes</b>: Facilita la creacion de componentes personalizados y su reutilizacion en diferentes
 * partes de la aplicacion.
 * <li><b>Carga dinamica</b>: Los archivos FXML pueden cargarse dinamicamente en tiempo de ejecucion, permitiendo cambios en la
 * interfaz sin necesidad de recompilar el codigo.
 * </ol>
 * <p>
 * Ejemplo basico de un archivo FXML:
 * <pre>{@code
 * <?xml version="1.0" encoding="UTF-8"?>
 *
 * <?import javafx.scene.control.Button?>
 * <?import javafx.scene.control.Label?>
 * <?import javafx.scene.layout.VBox?>
 *
 * <VBox xmlns:fx="http://javafx.com/fxml" fx:controller="com.craivet.hellofx.Controller">
 *     <Label fx:id="welcomeText"/>
 *     <Button text="Hello!" onAction="#onHelloButtonClick"/>
 * </VBox>
 *
 * }</pre>
 * <p>
 * En este ejemplo, se define una interfaz simple con un {@code Label} y un {@code Button} dentro de un {@code VBox}. El atributo
 * {@code fx:controller} especifica la clase Java que actuara como controlador para esta interfaz.
 * <p>
 * El uso de FXML en JavaFX proporciona una clara separacion entre la definicion de la interfaz de usuario y la logica de la
 * aplicacion, lo que puede resultar en un codigo mas limpio y mantenible, especialmente en aplicaciones mas grandes y complejas.
 */

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/scene.fxml"))));
        Scene scene = new Scene(root);
        stage.setTitle("Title");
        stage.setScene(scene);
        stage.show();

        // Al cerrar desde la cruz
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume(); // Evita que el programa se cierre cuando se presiona cancelar
            logout(stage);
        });
    }

    public void logout(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save configuration before exiting?:");

        // Si se presiono OK
        if (alert.showAndWait().get() == ButtonType.OK) {
            // Obtiene la escena actual en la que se esta trabajando
            System.out.println("You successfully logged out");
            stage.close();
        }


    }

    public static void main(String[] args) {
        launch();
    }

}