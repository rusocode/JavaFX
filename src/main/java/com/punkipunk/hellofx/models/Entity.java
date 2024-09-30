package com.punkipunk.hellofx.models;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * <p>
 * Las entidades pueden ser absolutamente cualquier cosa que se mueva en el juego: enemigos, jugadores e incluso recursos.
 * <p>
 * Una entidad debe tener un tamaño, una posicion y una rotacion. Incluso si no se va a mover, necesitamos saber en que parte del
 * mundo colocarla.
 */

public class Entity {

    Point2D position;
    float rotation, scale = 1;
    double width, height;

    Image image;

    public Entity(Image image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    /* ************************************************************************************************************
     *                                                  POSITIONAL                                                *
     ************************************************************************************************************ */

    public Point2D getDrawPosition() {
        return position;
    }

    public void setDrawPosition(float x, float y) {
        position = new Point2D(x, y);
    }

    private void rotate(float rotation) {
        this.rotation += rotation;
    }

    private void move(Point2D vector) {
        position = position.add(vector);
    }

    public float getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public Point2D getCenter() {
        Point2D pos = getDrawPosition();
        return new Point2D(pos.getX() + width / 2, pos.getY() + height / 2);
    }

    /* ************************************************************************************************************
     *                                                    IMAGE                                                   *
     ************************************************************************************************************ */

    public Image getImage() {
        return image;
    }

    public double getWidth() {
        return width * getScale();
    }

    public double getHeight() {
        return height * getScale();
    }

    /* ************************************************************************************************************
     *                                                   MOVEMENT                                                 *
     ************************************************************************************************************ */

    // Vector de empuje actual
    private float MAX_SPEED = 5f;
    private Point2D currentThrustVector = new Point2D(0, 0);

    // Fuerza de torsion actual
    private float MAX_TORQUE = 5f;
    private float currentTorqueForce = 0;

    // Giro
    public void addTorque(float torqueForce) {
        float newTorque = currentTorqueForce + torqueForce;
        if (torqueForce > 0) currentTorqueForce = Math.min(newTorque, MAX_TORQUE);
        else currentTorqueForce = Math.max(newTorque, -MAX_TORQUE);
    }

    public void addThrust(double scalar) {
        addThrust(scalar, getRotation());
    }

    private void addThrust(double scalar, double angle) {
        Point2D thrustVector = calculateNewThrustVector(scalar, Math.toRadians(-angle));
        currentThrustVector = currentThrustVector.add(thrustVector);
        currentThrustVector = clampToMaxSpeed(currentThrustVector);
    }

    private Point2D calculateNewThrustVector(double scalar, double angle) {
        return new Point2D((float) (Math.sin(angle) * scalar), (float) (Math.cos(angle) * scalar));
    }

    private Point2D clampToMaxSpeed(Point2D thrustVector) {
        if (thrustVector.magnitude() > MAX_SPEED) return currentThrustVector = thrustVector.normalize().multiply(MAX_SPEED);
        else return currentThrustVector = thrustVector;
    }

    private void applyDrag() {
        float movementDrag = currentThrustVector.magnitude() < 0.5 ? 0.01f : 0.07f;
        float rotationDrag = currentTorqueForce < 0.2f ? 0.05f : 0.1f;
        currentThrustVector = new Point2D(
                reduceTowardsZero((float) currentThrustVector.getX(), movementDrag),
                reduceTowardsZero((float) currentThrustVector.getY(), movementDrag));
        currentTorqueForce = reduceTowardsZero(currentTorqueForce, rotationDrag);
    }

    private float reduceTowardsZero(float value, float modifier) {
        float newValue = 0;
        if (value > modifier) newValue = value - modifier;
        else if (value < -modifier) newValue = value + modifier;
        return newValue;
    }

    public void update() {
        applyDrag();
        move(currentThrustVector);
        rotate(currentTorqueForce);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

}
