package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Alien extends AnimatedComponent {
    private static final int ySpeed = 4;

    private Alien(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }

    public static Alien alien1(int x, int y, int xSpeed) {
        Sprite sprite = new Sprite(5, 222, 64, 18, 1, 2);
        return new Alien(x, y, sprite, sprite.getWidth() / sprite.getNbFrames(), sprite.getHeight(), xSpeed);
    }

}
