package models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class SpaceCanvas {
    private static SpaceCanvas ourInstance = new SpaceCanvas();
    public static SpaceCanvas getInstance() {
        return ourInstance;
    }

    private Canvas canvas;
    private GraphicsContext graphicsContext;

    private SpaceCanvas() {
        //TODO: which size ?
        //this.canvas = new Canvas();
    }

    public void clear(AnimatedComponent animatedComponent) {
        graphicsContext.clearRect(
                animatedComponent.getX(),
                animatedComponent.getY(),
                animatedComponent.getWidth(),
                animatedComponent.getHeight()
        );
    }

    public void draw(AnimatedComponent animatedComponent) {
        graphicsContext.drawImage(
                animatedComponent.getSprite().getImage(),
                animatedComponent.getSprite().getDx(),
                animatedComponent.getSprite().getDy(),
                animatedComponent.getSprite().getWidth(),
                animatedComponent.getSprite().getHeight(),
                animatedComponent.getX(),
                animatedComponent.getY(),
                animatedComponent.getWidth(),
                animatedComponent.getHeight()
        );
    }
}
