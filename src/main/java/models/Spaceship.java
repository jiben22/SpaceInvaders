package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Spaceship extends AnimatedComponent {
    public static final String imgPath = "images/ship.gif";
    private static final int ySpeed = 0;

    // TODO: Singleton
    public Spaceship(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }
}
