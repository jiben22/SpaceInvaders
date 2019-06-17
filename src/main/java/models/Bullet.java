package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Bullet extends AnimatedComponent {
    public static final String imgPath = "images/bullet.gif";
    private static final int xSpeed = 0;
    public static final int offsetX = 591;
    public static final int offsetY = 331;
    public static final int offsetX1 = 617;
    public static final int offsetY1 = 394;
    public static final int imgWidth = offsetX1 - offsetX;
    public static final int imgHeight = offsetY1 - offsetY;

    public Bullet(int x, int y, Sprite sprite, int width, int height, int ySpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }
}
