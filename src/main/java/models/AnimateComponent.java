package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AnimateComponent {
    private int x;
    private int y;
    private Sprite sprite;
    private int width;
    private int height;
    private int xSpeed;

    public AnimateComponent(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.xSpeed = xSpeed;
    }
}
