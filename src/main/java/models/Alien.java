package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Alien extends AnimatedComponent {
    public static final String imgPath = "images/invader.png";
    private static final int ySpeed = 0;
    public static final int offsetX = 5;
    public static final int offsetY = 222;
    public static final int offsetX1 = 69;
    public static final int offsetY1 = 240;
    public static final int imgWidth = offsetX1 - offsetX;
    public static final int imgHeight = offsetY1 - offsetY;

    private Alien(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }

    public static Alien Alien1() {
        return new Alien();
    }

}
