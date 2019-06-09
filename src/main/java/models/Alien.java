package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Alien extends AnimatedComponent {
    public static final String imgPath = "images/alien.gif";

    public Alien(int x, int y, Sprite sprite, int width, int height, int xSpeed, int ySpeed) {
        super(x, y, sprite, width, height, xSpeed, ySpeed);
    }
}
