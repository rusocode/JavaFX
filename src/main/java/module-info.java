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
 */
module com.craivet.hellofx { // Define el nombre del modulo
    // Indican que el modulo depende de estos modulos de JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    // Permite que JavaFX acceda a las clases en ese paquete para la carga de FXML
    opens com.craivet.hellofx to javafx.fxml;
    // Hace que las clases publicas en ese paquete sean accesibles desde fuera del modulo
    exports com.craivet.hellofx;
}