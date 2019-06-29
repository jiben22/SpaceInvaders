package app.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Explosion extends AnimatedComponent {
    private static final int xSpeed = 0;
    private static final int ySpeed = 0;


    public Explosion(int x, int y, Sprite sprite, int width, int height) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }

    public static Explosion explosion1(int x, int y) {
        Sprite sprite = new Sprite("images/components/explosion.png", 512, 512);
        return new Explosion(x, y, sprite, sprite.getWidth(), sprite.getHeight());
    }
}
