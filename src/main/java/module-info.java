/**
 * <p>
 * El archivo {@code module-info.java} es parte del sistema de modulos de Java (Java Platform Module System o JPMS), introducido
 * en Java 9. En el contexto de un proyecto JavaFX creado en IntelliJ, este archivo juega un papel importante en la definicion y
 * configuracion del modulo de la aplicacion.
 * <p>
 * El uso de {@code module-info.java} en proyectos JavaFX ayuda a:
 * <ul>
 * <li>Mejorar la modularidad y encapsulacion del codigo.
 * <li>Facilitar la gestion de dependencias.
 * <li>Permitir una mejor organizacion de aplicaciones grandes.
 * <li>Preparar la aplicacion para su distribucion como un modulo Java.
 * </ul>
 * <p>
 * Links:
 * <a href="https://edencoding.com/javafx-maven/">How To Effortlessly Setup JavaFX With Maven (+ debugging tips!) in IntelliJ</a>
 */
module com.punkipunk.hellofx { // Define el nombre del modulo
    // Indican que el modulo depende de estos modulos de JavaFX
    requires javafx.controls; // Ya 'requires' javafx.base y javafx.graphics, por lo tanto no necesitamos agregarlos. Es un tipo especial de declaracion de requisitos, llamada requires transitive
    requires javafx.fxml; // Ya 'requires' javafx.base y javafx.graphics, por lo tanto no necesitamos agregarlos
    requires java.desktop;
    requires jdk.unsupported.desktop;
    /* Usaremos FXMLLoader para cargar nuestros controladores en la interfaz de usuario. Por esa razon, tambien debemos
     * asegurarnos de que el modulo fxml de JavaFX pueda acceder a nuestros controladores, por lo que agregaremos la siguiente
     * linea debajo de las declaraciones requeridas. */
    opens com.punkipunk.hellofx.controllers to javafx.fxml;
    /* La ultima linea asegurara que la parte grafica de JavaFX pueda acceder a nuestra aplicacion. Esto es necesario porque
     * nuestra aplicacion extiende la clase Application de JavaFX, que esta involucrada en el grafico de escena de la aplicacion.
     * Sin el, nuestra aplicacion no se ejecutara. */
    exports com.punkipunk.hellofx; // Hace que las clases publicas en ese paquete sean accesibles desde fuera del modulo
}