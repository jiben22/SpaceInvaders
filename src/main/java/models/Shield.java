package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Shield extends AnimatedComponent {
    private static final int xSpeed = 0;
    private static final int ySpeed = 0;

    private Shield(int x, int y, Sprite sprite, int width, int height) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }

    private Shield shield1(int x, int y) {
        Sprite sprite = new Sprite(0, 334, 332, 36, 1, 4);
        return new Shield(x, y, sprite, sprite.getWidth() / sprite.getNbFrames(), sprite.getHeight());
    }
}
