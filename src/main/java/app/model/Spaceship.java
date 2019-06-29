package app.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Spaceship extends AnimatedComponent {
    private static final int ySpeed = 0;

    private Spaceship(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }

    public static Spaceship spaceship1(int x, int y, int xSpeed) {
        Sprite sprite = new Sprite(409, 311, 70, 19, 1, 2);
        return new Spaceship(x, y, sprite, sprite.getWidth() / sprite.getNbFrames(), sprite.getHeight(), xSpeed);
    }
}
