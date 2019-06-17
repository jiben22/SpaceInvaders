package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Spaceship extends AnimatedComponent {
    private static final int ySpeed = 0;

    private Spaceship(int x, int y, Sprite sprite, int xSpeed) {
        super(x, y, sprite, xSpeed, ySpeed);
    }

    private Spaceship spaceship1(int x, int y, int xSpeed) {
        Sprite sprite = new Sprite(409, 311, 66, 19);
        return new Spaceship(x, y, sprite, xSpeed);
    }
}
