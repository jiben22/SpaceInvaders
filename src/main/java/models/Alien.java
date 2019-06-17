package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Alien extends AnimatedComponent {
    private static final int ySpeed = 0;

    private Alien(int x, int y, Sprite sprite, int xSpeed) {
        super(x, y, sprite, xSpeed, ySpeed);
    }

    public static Alien getAlien1(int x, int y, int xSpeed) {
        Sprite sprite = new Sprite(5, 222, 64, 18);
        return new Alien(x, y, sprite, xSpeed);
    }

}
