package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Alien extends AnimatedComponent {
    private static final int ySpeed = 10;

    private Alien(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }

    public static Alien alien1(int x, int y, int xSpeed) {
        Sprite sprite = new Sprite(343, 199, 66, 21, 1, 2);
        return new Alien(x, y, sprite, sprite.getWidth() / sprite.getNbFrames(), sprite.getHeight(), xSpeed);
    }

    public static Alien alien2(int x, int y, int xSpeed) {
        Sprite sprite = new Sprite(410, 199, 66, 21, 1, 2);
        return new Alien(x, y, sprite, sprite.getWidth() / sprite.getNbFrames(), sprite.getHeight(), xSpeed);
    }
}
