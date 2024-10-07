package com.punkipunk.hellofx.utils;

import javafx.scene.image.Image;

import java.util.Objects;

public final class Utils {

    private Utils(){}

    public static Image loadImage(String path) {
        try {
            return new Image(Objects.requireNonNull(Utils.class.getResourceAsStream(path)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image: " + path, e);
        }
    }

}
