package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Bullet extends AnimatedComponent {
    public static final String imgPath = "images/bullet.gif";
    private static final int xSpeed = 0;

    public Bullet(int x, int y, Sprite sprite, int width, int height, int xSpeed, int ySpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }
}
