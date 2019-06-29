package app.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Bullet extends AnimatedComponent {
    private static final int xSpeed = 0;

    private Bullet(int x, int y, Sprite sprite, int width, int height, int ySpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }

    public static Bullet bullet1(int x, int y, int ySpeed) {
        Sprite sprite = new Sprite(591, 331, 26, 63, 1, 3);
        return new Bullet(x, y, sprite, sprite.getWidth(), sprite.getHeight() / sprite.getNbFrames(), ySpeed);
    }
}
