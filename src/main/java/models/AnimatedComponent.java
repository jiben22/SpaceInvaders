package models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

    public AnimatedComponent(int x, int y, Sprite sprite, int width, int height, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage( this.sprite.getImage(), this.x, this.y );
        //System.out.println("Draw " + "x: "+x + " y: " + y);
    }

    public void clear(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //System.out.println("Clear " + "x: "+x + " y: " + y + " width: " + width + " height: " + height);
        gc.clearRect( this.x, this.y, this.width + 50, this.height );
    }

    public void moveUp(Canvas canvas) {
        clear(canvas);
        this.y -= this.ySpeed;
        draw(canvas);
    }

    public void moveRight(Canvas canvas) {
        clear(canvas);
        this.x += this.xSpeed;
        draw(canvas);
    }

    public void moveDown(Canvas canvas) {
        clear(canvas);
        this.y += this.ySpeed;
        draw(canvas);
    }

    public void moveLeft(Canvas canvas) {
        clear(canvas);
        this.x -= this.xSpeed;
        draw(canvas);
    }
}
