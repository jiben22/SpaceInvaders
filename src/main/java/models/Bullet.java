package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Bullet extends AnimateComponent {
    public Bullet(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        super(x, y, sprite, width, height, xSpeed);
    }
}
