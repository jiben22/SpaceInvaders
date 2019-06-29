package model;

import javafx.geometry.Rectangle2D;
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
    private Rectangle2D rectangle;

    private SpaceCanvas spaceCanvas = SpaceCanvas.getInstance();

    public AnimatedComponent(int x, int y, Sprite sprite, int width, int height, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.rectangle = new Rectangle2D(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void moveUp() {
        spaceCanvas.clear(this);
        this.y -= this.ySpeed;
        spaceCanvas.draw(this);
        this.setRectangle(new Rectangle2D(this.x, this.y, this.width, this.height));
    }

    public void moveRight() {
        spaceCanvas.clear(this);
        this.x += this.xSpeed;
        spaceCanvas.draw(this);
        this.setRectangle(new Rectangle2D(this.x, this.y, this.width, this.height));
    }

    public void moveDown() {
        spaceCanvas.clear(this);
        this.y += this.getRectangle().getHeight() / 3;
        spaceCanvas.draw(this);
        this.setRectangle(new Rectangle2D(this.x, this.y, this.width, this.height));
    }

    public void moveLeft() {
        spaceCanvas.clear(this);
        this.x -= this.xSpeed;
        spaceCanvas.draw(this);
        this.setRectangle(new Rectangle2D(this.x, this.y, this.width, this.height));
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.x, this.y, this.width, this.height);
    }

    public boolean intersects(AnimatedComponent a) {
        return a.getBoundary().intersects(this.getBoundary());
    }

}
