package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Alien extends AnimatedComponent {
    public static final String imgPath = "images/alien.gif";
    private static final int ySpeed = 0;

    public Alien(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }
}
