package com.punkipunk.hellofx.models;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * <p>
 * Las entidades pueden ser absolutamente cualquier cosa que se mueva en el juego: mobs, player, etc. Cada entidad debe tener una
 * imagen, tamaño (scale), posicion y rotacion.
 * <h2>Point2D</h2>
 * <p>
 * Point2D en JavaFX es una clase que forma parte del paquete {@code javafx.geometry} y se utiliza para representar un punto en un
 * espacio bidimensional (2D) mediante coordenadas <i>x</i> e <i>y</i>. Esta clase se caracteriza por ser inmutable, lo que
 * significa que una vez creado un objeto Point2D, sus coordenadas no pueden modificarse. La clase proporciona diversos metodos
 * utiles para realizar operaciones geometricas, como {@code getX()} y {@code getY()} para obtener las coordenadas individuales,
 * {@code add()} para crear un nuevo punto sumando las coordenadas del punto actual con las del punto proporcionado,
 * {@code subtract()} para restar coordenadas, y {@code distance()} para calcular la distancia euclidiana entre dos puntos. Los
 * objetos Point2D se crean utilizando el constructor {@code Point2D(double x, double y)} y son ampliamente utilizados en
 * aplicaciones graficas y de interfaz de usuario para realizar calculos geometricos, manipular coordenadas y trabajar con
 * elementos visuales en el espacio bidimensional. La clase tambien ofrece funcionalidades para realizar operaciones mas avanzadas
 * como interpolacion, normalizacion y calculo de angulos entre puntos.
 * <h3>Trigonometria</h3>
 * <p>
 * La conversion de grados a radianes es necesaria porque las funciones trigonometricas en Java (como {@code Math.sin()} y
 * {@code Math.cos()}) esperan sus argumentos en radianes, no en grados.
 * <p>
 * En muchos sistemas graficos 2D, incluyendo probablemente el usado aqui, el eje Y crece hacia abajo en la pantalla y la rotacion
 * positiva se considera en sentido horario. Sin embargo, en el circulo trigonometrico estandar, el eje Y crece hacia arriba y la
 * rotacion positiva se considera en sentido antihorario. Al negar la rotacion, estamos "invirtiendo" el sentido de la rotacion
 * para que coincida con el circulo trigonometrico estandar usado por las funciones sin y cos. Esto asegura que cuando calculamos
 * el nuevo vector en {@code polarToCartesian()}, la direccion resultante sea coherente con la rotacion visual de la entidad en la
 * pantalla. Por ejemplo, si rotation es 90 grados (apuntando a la derecha en la pantalla), -rotation sera -90 grados. Cuando esto
 * se pasa a polarToCartesian(), resultara en un vector que apunta a la derecha (cos(-90°) = 0, sin(-90°) = -1). Esta negacion es
 * una tecnica comun para reconciliar las diferencias entre los sistemas de coordenadas de la pantalla y el sistema de coordenadas
 * matematico estandar usado en trigonometria.
 * <p>
 * Nota: Los escalares son magnitudes que solo tienen magnitud, mientras que los vectores tienen magnitud y direccion.
 * <p>
 * Links: <a
 * href="https://www.khanacademy.org/math/precalculus/x9e81a4f98389efdf:vectors/x9e81a4f98389efdf:vectors-intro/v/introduction-to-vectors-and-scalars">Intro
 * to vectors & scalars</a>
 */

public class Entity {

    private final Image image;
    private final double width;
    private final double height;
    private float scale;

    private Point2D position; // Posicion actual
    private Point2D newPosition;

    private float rotation; // Orientacion actual
    private float newRotation;

    public Entity(Image image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        newPosition = new Point2D(0, 0);
    }

    /**
     * Actualiza la entidad.
     */
    public void update() {
        applyDrag();
        updatePosition();
        updateRotation();
    }

    /**
     * <p>
     * Simula la friccion o resistencia que experimenta la entidad cuando "frena". Su proposito es reducir gradualmente la
     * velocidad de movimiento (magnitud de newPosition) y la velocidad de rotacion. Utiliza diferentes valores de arrastre
     * dependiendo de la velocidad actual, lo que resulta en un comportamiento mas realista donde el arrastre es mas pronunciado a
     * altas velocidades.
     * <p>
     * Si la entidad se mueve rapido (valor alto), la reduccion sera mas notable. Si la entidad se mueve lento (valor bajo), la
     * reduccion sera menor. Cuando la velocidad es muy baja (menor que el modificador), el movimiento se detiene completamente.
     * <p>
     * Al establecer valores muy pequeños directamente a cero, se evitan micro-movimientos o vibraciones no deseadas cuando la
     * entidad esta casi detenida.
     * <p>
     * Implementacion:
     * <ul>
     * <li>Para el movimiento lineal:
     *  <ul>
     *     <li>El metodo magnitude() calcula la magnitud del vector desde el origen (0,0) hasta el punto representado por el Point2D</li>
     *     <li>Si la magnitud es menor que 0.5, se aplica un arrastre suave de 0.01</li>
     *     <li>Si la magnitud es mayor o igual a 0.5, se aplica un arrastre mas fuerte de 0.07</li>
     *     </ul>
     * </li>
     * <li>Para la rotacion:
     *     <ul>
     *     <li>Si la velocidad de rotacion es menor que 0.2, se aplica un arrastre de 0.05</li>
     *     <li>Si la velocidad de rotacion es mayor o igual a 0.2, se aplica un arrastre de 0.1</li>
     *     </ul>
     * </li>
     * <li>La funcion reduceTowardsZero() se aplica a:
     *     <ul>
     *     <li>Cada componente (X e Y) del vector de newPosition, reduciendo gradualmente su magnitud</li>
     *     <li>La velocidad de rotacion, reduciendo gradualmente su valor</li>
     *     </ul>
     * </li>
     * </ul>
     */
    private void applyDrag() {
        // Calcula el arrastre para la nueva posicion y rotacion
        float velocityDrag = newPosition.magnitude() < 0.5 ? 0.01f : 0.07f;
        float rotationDrag = newRotation < 0.2f ? 0.05f : 0.1f;
        newPosition = new Point2D(reduceTowardsZero((float) newPosition.getX(), velocityDrag), reduceTowardsZero((float) newPosition.getY(), velocityDrag));
        newRotation = reduceTowardsZero(newRotation, rotationDrag);
    }

    /**
     * <p>
     * Actualiza la posicion de la entidad mediante una operacion de suma vectorial. Desde el punto de vista fisico, este metodo
     * opera con dos vectores fundamentales. El primero es el vector de posicion ({@code position}) que define la ubicacion actual
     * en el espacio 2D, con componentes (x, y) medidas en pixeles desde el origen ubicado en la esquina superior izquierda de la
     * pantalla. El segundo es el vector de desplazamiento ({@code newPosition}) que define el cambio de posicion con componentes
     * (Δx, Δy) en pixeles. Este vector de desplazamiento se genera en el metodo {@code applyThrust()} a partir de dos escalares:
     * la magnitud, que se calcula como el producto del empuje ({@code thrust}) por el tiempo transcurrido ({@code deltaTime}), y
     * la direccion, determinada por el angulo de rotacion ({@code rotation}). La operacion vectorial realizada es una suma donde
     * el vector de posicion final es igual al vector de posicion inicial mas el vector de desplazamiento. En terminos de
     * componentes, esto significa que la posicion final en x es igual a la posicion inicial en x mas el desplazamiento en x, y de
     * manera similar para la componente y. Este proceso representa un caso clasico de suma vectorial donde la posicion es un
     * vector que indica la ubicacion en el espacio, el desplazamiento es un vector que indica el cambio en esa ubicacion, y la
     * suma vectorial produce la nueva ubicacion en el espacio.
     */
    private void updatePosition() {
        position = position.add(newPosition);
    }

    /**
     * Actualiza la rotacion.
     */
    private void updateRotation() {
        rotation += newRotation;
    }

    /**
     * Reduce gradualmente un valor hacia cero.
     *
     * @param value    valor actual que se quiere reducir.
     * @param modifier cantidad por la que se quiere reducir el valor.
     * @return el nuevo valor despues de aplicar la reduccion.
     */
    private float reduceTowardsZero(float value, float modifier) {
        float newValue = 0; // Si value esta entre -modifier y modifier, el resultado es 0
        if (value > modifier) newValue = value - modifier;
        else if (value < -modifier) newValue = value + modifier;
        return newValue;
    }

    /**
     * Aplica rotacion.
     * <p>
     * La rotacion esta limitada por {@code MAX_SPEED} para evitar rotaciones excesivamente rapidas. La velocidad de rotacion
     * resultante mantiene el mismo limite tanto para rotaciones en sentido horario (valores positivos) como antihorario (valores
     * negativos).
     * <p>
     * Si la nueva velocidad de rotacion excede el limite maximo, se ajusta al valor maximo permitido manteniendo el sentido de la
     * rotacion.
     *
     * @param rotation rotaciona a aplicar. Un valor positivo genera una rotacion en sentido horario, mientras que un valor
     *                 negativo genera una rotacion en sentido antihorario.
     */
    public void applyRotation(float rotation) {
        final float MAX_SPEED = 5;
        float newRotationSpeed = newRotation + rotation;
        if (rotation > 0) newRotation = Math.min(newRotationSpeed, MAX_SPEED); // Rotacion en sentido horario
        else newRotation = Math.max(newRotationSpeed, -MAX_SPEED); // Rotacion en sentido antihorario
    }

    /**
     * Aplica empuje.
     * <p>
     * Este proceso consta de tres pasos principales: primero se realiza una conversion de la magnitud (thrust) y direccion
     * (rotation) para obtener un vector cartesiano, luego se procede a sumar este vector al vector de la nueva posicion, y
     * finalmente se limita la velocidad (magnitud) resultante en caso de que llegue al limite maximo. Para llevar a cabo estos
     * calculos, el metodo hace uso de coordenadas polares al determinar el vector de la nueva posicion, realizando una conversion
     * de la rotacion de la entidad a radianes y aplicando una negacion para que se ajuste correctamente al sistema de coordenadas
     * que se utiliza en la pantalla.
     *
     * @param thrust empuje a aplicar.
     */
    public void applyThrust(float thrust) {
        newPosition = newPosition.add(polarToCartesian(thrust, Math.toRadians(-rotation)));
        newPosition = limitSpeed(newPosition);
    }

    /**
     * <p>
     * Convierte las coordenadas polares (thrust y rotation) a un vector cartesiano. El empuje (thrust) representa la magnitud y
     * rotation, obviamente, la direccion.
     *
     * @param thrust   magnitud del vector.
     * @param rotation direccion del vector.
     * @return un vector cartesiano que represena la nueva posicion.
     */
    private Point2D polarToCartesian(float thrust, double rotation) {
        return new Point2D((float) (Math.sin(rotation) * thrust), (float) (Math.cos(rotation) * thrust));
    }

    /**
     * Limita la velocidad del vector.
     *
     * @param vector vector.
     * @return el vector con la velocidad limitada, o el vector sin limitar.
     */
    private Point2D limitSpeed(Point2D vector) {
        final float MAX_SPEED = 5;
        // Obtiene la velocidad
        float speed = (float) vector.magnitude();
        if (speed > MAX_SPEED) {
            /* Normaliza el vector (lo convierte en un vector unitario) y lo multiplica por el limite para conservar la direccion
             * original mientras se ajusta su magnitud a los limites deseados. */
            return vector.normalize().multiply(MAX_SPEED);
        }
        return vector;
    }

    public Point2D getPosition() {
        return position;
    }

    /**
     * Establece la posicion de la entidad en el eje de coordenadas.
     *
     * @param x posicion en la coordenada x.
     * @param y posicion en la coordenada y.
     */
    public void setPosition(float x, float y) {
        position = new Point2D(x, y);
    }

    /**
     * Obtiene el centro de la entidad.
     *
     * @return el centro de la entidad.
     */
    public Point2D getCenter() {
        return new Point2D(position.getX() + width / 2, position.getY() + height / 2);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Image getImage() {
        return image;
    }

    public double getWidth() {
        return width * getScale();
    }

    public double getHeight() {
        return height * getScale();
    }

    public float getRotation() {
        return rotation;
    }

}
