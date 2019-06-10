package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Spaceship extends AnimatedComponent {
    public static final String imgPath = "images/ship.gif";
    private static final int ySpeed = 0;
    public static final int offsetX = 409;
    public static final int offsetY = 311;
    public static final int offsetX1 = 475;
    public static final int offsetY1 = 330;
    public static final int imgWidth = offsetX1 - offsetX;
    public static final int imgHeight = offsetY1 - offsetY;

    // TODO: Singleton
    public Spaceship(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }
}
