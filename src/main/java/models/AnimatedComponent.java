package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AnimatedComponent {
    private int x;
    private int y;
    private Sprite sprite;
    private int width;
    private int height;
    private int xSpeed;
    private int ySpeed;
    private boolean moveable;

    private SpaceCanvas spaceCanvas = SpaceCanvas.getInstance();

    public AnimatedComponent(int x, int y, Sprite sprite, int width, int height, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void moveUp() {
        spaceCanvas.clear(this);
        this.y -= this.getYSpeed();
        spaceCanvas.draw(this);
    }

    public void moveRight() {
        spaceCanvas.clear(this);
        this.x += this.getXSpeed();
        spaceCanvas.draw(this);
    }

    public void moveDown() {
        spaceCanvas.clear(this);
        this.y += this.getYSpeed();
        spaceCanvas.draw(this);
    }

    public void moveLeft() {
        spaceCanvas.clear(this);
        this.x -= this.getXSpeed();
        spaceCanvas.draw(this);
    }
}
