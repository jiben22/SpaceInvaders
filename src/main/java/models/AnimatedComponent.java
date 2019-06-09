package models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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


}
