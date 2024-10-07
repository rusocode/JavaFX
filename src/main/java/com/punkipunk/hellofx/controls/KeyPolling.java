package com.punkipunk.hellofx.controls;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

/**
 * <h2>¿Que es un evento?</h2>
 * <p>
 * <b>Un evento en JavaFX es cualquier cambio de estado en un dispositivo de entrada, una accion del usuario o una tarea en
 * segundo plano, que puede ocurrir en elementos complejos como {@code TableView} o {@code ListView}.</b> El objeto {@code Event}
 * es una clase simple con pocos parametros que actua como una señal de que algo ha cambiado, sin contener ni ejecutar codigo por
 * si mismo. JavaFX proporciona soporte para ejecutar codigo definido separadamente a traves de la clase {@code EventHandler} en
 * respuesta a estos cambios de estado. Existen 90 tipos diferentes de eventos en JavaFX, y es posible extender la clase Event
 * para definir funcionalidades personalizadas adicionales. Los eventos son una forma versatil y estable de impulsar cambios en el
 * hilo de aplicacion de JavaFX, permitiendo actualizar la interfaz en segundo plano en respuesta a acciones como ordenar
 * columnas, cambiar vistas o editar celdas.
 * <h2>Como funcionan los eventos</h2>
 * <p>
 * Un evento en JavaFX tiene cuatro propiedades principales: <b>Source</b> (la fuente que causa el evento), <b>Target</b> (el nodo
 * sobre el que actua el evento), <b>Type</b> (el tipo de evento basado en su origen y naturaleza) y <b>Consumed</b> (un booleano
 * que indica si el evento ha sido consumido). La fuente del evento puede ser cualquier cambio de estado, como movimientos del
 * raton o activacion de botones. El objetivo debe implementar la interfaz {@code EventTarget}, lo que permite a JavaFX disparar
 * eventos en cualquier elemento de la interfaz de usuario. Los tipos de eventos se definen usando la clase {@code EventType} para
 * garantizar la seguridad de tipos y diferenciarlos de otros sistemas como AWT. La propiedad "consumed" es crucial para controlar
 * el flujo de eventos, permitiendo detener su propagacion en cualquier punto. JavaFX maneja los eventos haciendolos pasar por el
 * <b>grafico de escena</b>, desde la ventana hasta el nodo objetivo y de vuelta, lo que permite un control detallado sobre como
 * se procesan y responden los eventos en una aplicacion. Este sistema proporciona una forma estable y flexible de actualizar
 * elementos complejos de la interfaz de usuario y es fundamental en el desarrollo basado en eventos.
 * <h2>Como definir codigo ejecutable en un EventHandler</h2>
 * <p>
 * Los {@code EventHandlers} se usan tanto para filtros como para manejadores de eventos y se pueden crear como clases anonimas
 * internas o expresiones lambda. EventHandler es una interfaz <b>parametrizada</b> que requiere especificar el tipo de evento a
 * manejar. Para añadir un manejador de eventos, se define el EventHandler, se añade al nodo con {@code addEventHandler()}
 * especificando el tipo de evento, y se puede remover despues con {@code removeEventHandler()}. Los filtros de eventos se añaden
 * y remueven de manera similar con {@code addEventFilter()} y {@code removeEventFilter()}. Existen metodos de conveniencia como
 * {@code setOnKeyPressed()} para definir manejadores de eventos mas facilmente, siguiendo el patron setOnTipoDeEvento. Estos se
 * pueden remover asignando null y se ejecutan despues de otros manejadores del mismo tipo en el nodo. Al remover un manejador o
 * filtro, es importante usar el metodo correcto y especificar el mismo tipo de evento que se uso al añadirlo. Este resumen abarca
 * las principales formas de definir y gestionar la respuesta a eventos en JavaFX, ofreciendo flexibilidad en la implementacion y
 * control del comportamiento de la interfaz de usuario.
 * <h2>Tipos de eventos</h2>
 * <p>
 * Los tipos de eventos en JavaFX se dividen en dos categorias principales:
 * <ol>
 * <li>Eventos generados por el sistema operativo (eventos de entrada)
 * <li>Eventos especificos de JavaFX
 * </ol>
 * <p>
 * Eventos generados por el sistema operativo (eventos de entrada):
 * <ul>
 * <li>Son proporcionados por el sistema operativo y procesados por JavaFX.
 * <li>Incluyen eventos de raton, teclado y gestos.
 * <li>JavaFX identifica el nodo correcto para aplicar el evento.
 * <li>La integracion con el sistema operativo permite caracteristicas como el area de "histeresis" para evitar clics
 * accidentales.
 * </ul>
 * <p>
 * Caracteristicas importantes de los eventos de teclado:
 * <ul>
 * <li>Generados cuando una tecla cambia de estado (presionada o liberada).
 * <li>Almacenan parametros adicionales sobre las teclas actualmente presionadas.
 * <li>Ofrecen metodos de conveniencia para detectar teclas multiplataforma (ej. Ctrl en Windows, Command en Mac).
 * </ul>
 * <p>
 * Configuracion del sistema operativo:
 * <ul>
 * <li>El retraso de repeticion y la tasa de repeticion son manejados por el sistema operativo.
 * <li>La frecuencia subsiguiente de los eventos de repeticion del teclado (frecuencia de repeticion).
 * </ul>
 * <p>
 * Seleccion del objetivo:
 * <ul>
 * <li>El objetivo de un evento de teclado es el {@code Control} actualmente enfocado o la Scene si no hay ningun Control presente.
 * <li>Solo un Control puede tener el foco de una escena.
 * </ul>
 * <p>
 * Tipos de eventos de teclado:
 * <ol>
 * <li>KEY_PRESSED: Se activa cada vez que se presiona una tecla o si se mantiene presionada una tecla durante mas tiempo que el
 * <i>retardo de repeticion</i> definido por el sistema operativo. Despues del retardo de repeticion, se genera un evento
 * presionado a la frecuencia de repeticion hasta que se suelta la tecla.
 * <li>KEY_RELEASED: Cuando se libera una tecla.
 * <li>KEY_TYPED: Solo se genera cuando se presiona y se suelta una tecla generadora de caracteres. Al invocar el metodo
 * {@code getCharacter()} se devolvera la letra que se escribio, teniendo en cuenta las teclas modificadoras, como la tecla Shift,
 * en los casos en que las letras pueden tener mayusculas y minusculas.
 * </ol>
 * <p>
 * Como usar KeyEvent:
 * <ul>
 * <li>Se pueden adjuntar a cualquier clase que extienda EventTarget.
 * <li>Metodos de conveniencia: setOnKeyPressed(), setOnKeyReleased(), setOnKeyTyped().
 * <li>Metodos alternativos: addEventFilter() y addEventHandler().
 * </ul>
 * <p>
 * Comportamiento:
 * <ul>
 * <li>JavaFX maneja los eventos de teclado de manera inteligente para minimizar los eventos innecesarios.
 * <li>Los eventos de teclas no especiales en controles de entrada se consumen despues del manejador de eventos del objetivo.
 * <li>Las teclas especiales (Ctrl, Alt, Shift, Esc, Enter, Tab) hacen que el evento recorra todo el grafico de escena.
 * </ul>
 * <h2>Eventos originados en JavaFX</h2>
 * <p>
 * JavaFX tiene la capacidad de crear y despachar sus propios eventos, incluyendo Eventos de Accion, Eventos de Trabajador y
 * Eventos de Edicion, Modificacion y Ordenamiento. Los Eventos de Accion estan diseñados para abarcar multiples tipos de entrada,
 * definiendo un solo fragmento de codigo ejecutable para una accion especifica del usuario. Estos eventos <b>proporcionan soporte
 * para tomar acciones basadas en las expectativas consistentes del usuario</b>, como enviar un formulario presionando la tecla
 * Enter en el ultimo campo de texto. El objetivo de un evento de accion es el nodo que fue clickeado, tocado o enfocado. La clase
 * {@code ActionEvent} solo tiene un {@code EventType} llamado <b>{@code ACTION}</b>. Los eventos de accion se agregan comunmente
 * usando el metodo de conveniencia {@code setOnAction()}. Tambien pueden definirse en FXML. Es importante tener en cuenta que un
 * evento de accion y el evento de entrada que lo desencadena no son mutuamente excluyentes. Los eventos de accion se pueden
 * filtrar usando {@code addEventFilter()} en nodos superiores de la escena, aunque no es comun. JavaFX proporciona una capa de
 * funcionalidad sobre la interfaz de usuario que puede abarcar multiples entradas del usuario, permitiendo manejar acciones como
 * hacer clic en un boton, presionar Enter cuando el boton esta enfocado, presionar Enter en un TextField, armar o desarmar un
 * boton de alternancia, o hacer clic en un boton de menu, todo con el mismo manejador de eventos.
 * <h3>Como utilizar eventos de accion</h3>
 * <p>
 * Lo mas comun es agregar controladores de eventos de accion mediante el metodo {@code setOnAction()}. Rara vez se requieren
 * multiples funciones, por lo que anular otros controladores de eventos es una preocupacion menor.
 * <pre>{@code
 * textField.setOnAction(event -> {
 *     // Accion de evento definida con lambda
 * });
 * }</pre>
 * <p>
 * Al igual que con cualquier evento, se pueden configurar utilizando los metodos addEventHandler() y addEventFilter().
 * <p>
 * Finalmente, se pueden definir en FXML utilizando la siguiente sintaxis:
 * <pre>{@code
 * <TextField onAction="#handleTextCommit" fx:id="textField"/>
 * }</pre>
 * <p>
 * El simbolo "#" es una bandera para que {@code FXMLLoader} inyecte el metodo {@code handleTextCommit(ActionEvent event)} en la
 * propiedad onAction del {@code TextField}. El metodo {@code handleTextCommit()} debe estar presente en el controlador (con el
 * argumento {@code ActionEvent} correcto) o se lanzara una excepcion de carga.
 * <h2>Creacion de un sistema de teclas compatible con el juego</h2>
 * <p>
 * JavaFX ofrece un poderoso sistema de eventos capaz de rastrear eventos de portapapeles, arrastre, raton y teclado con
 * facilidad. Sin embargo, este sistema se basa en <b>eventos</b> en lugar de <b>polling</b>, lo que significa que si no
 * capturamos el evento en el momento que <i>ocurre</i>, lo perdemos. En sistemas de desarrollo de juegos como LWJGL, es mas comun
 * proporcionar a los usuarios la funcionalidad de verificar cuando deseen si una tecla especifica esta presionada. Para crear
 * esta funcionalidad en JavaFX, es necesario envolver el manejo de eventos en un polling-friendly wrapper. Esto implica
 * configurar un <b>listener</b> que se ejecute cada vez que se presiona una tecla y llevar un registro de las teclas por nuestra
 * cuenta. Como no es necesario mantener mas de un conjunto de teclas, se puede implementar la clase {@code KeyPolling} como un
 * Singleton. Esta aproximacion permite convertir el manejo de eventos basado en eventos a uno basado en polling, lo que puede ser
 * mas util en el contexto del desarrollo de juegos.
 * <p>
 * Links: <a href="https://edencoding.com/javafx-events/">A Definitive Guide To JavaFX Events</a>
 */

public class KeyPolling {

    private static KeyPolling instance;
    private static Scene scene;
    // Uso de EnumSet para mejor rendimiento
    private static final Set<KeyCode> keysCurrentlyDown = EnumSet.noneOf(KeyCode.class); // Conjunto de teclas actualmente presionadas

    private KeyPolling() {
    }

    /**
     * Obtiene una nueva instancia de la clase.
     *
     * @return una nueva instancia de la clase.
     */
    public static KeyPolling getInstance() {
        if (instance == null) instance = new KeyPolling();
        return instance;
    }

    /**
     * Configura la escena para el polling de teclas.
     *
     * @param scene escena a configurar.
     * @throws IllegalArgumentException si la escena es nula.
     */
    public void pollScene(Scene scene) {
        if (scene == null) throw new IllegalArgumentException("The scene cannot be null!");
        clearKeys();
        removeCurrentKeyHandlers();
        setScene(scene);
    }

    /**
     * Verifica si una tecla especifica esta presionada.
     *
     * @param keyCode codigo de la tecla a verificar.
     * @return true si la tecla esta presionada, false en caso contrario.
     */
    public boolean isDown(KeyCode keyCode) {
        return keysCurrentlyDown.contains(keyCode);
    }

    /**
     * Limpia las teclas.
     */
    private void clearKeys() {
        keysCurrentlyDown.clear();
    }

    /**
     * Elimina los manejadores de eventos actuales.
     */
    private void removeCurrentKeyHandlers() {
        if (scene != null) {
            scene.setOnKeyPressed(null);
            scene.setOnKeyReleased(null);
        }
    }

    /**
     * Configura una nueva escena.
     *
     * @param newScene nueva escena.
     */
    private void setScene(Scene newScene) {
        scene = newScene;
        scene.setOnKeyPressed((keyEvent -> keysCurrentlyDown.add(keyEvent.getCode())));
        scene.setOnKeyReleased((keyEvent -> keysCurrentlyDown.remove(keyEvent.getCode())));
    }

    /**
     * Muestra informacion sobre las teclas presionadas.
     */
    @Override
    public String toString() {
        StringBuilder keysDown = new StringBuilder("KeyPolling en escena (").append(scene).append("): ");
        keysCurrentlyDown.forEach(code -> keysDown.append(code.getName()).append(" "));
        return keysDown.toString();
    }

}
