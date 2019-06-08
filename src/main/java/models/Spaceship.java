package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Spaceship extends AnimateComponent {
    public Spaceship(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        super(x, y, sprite, width, height, xSpeed);
    }
}
