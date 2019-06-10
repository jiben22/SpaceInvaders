package controllers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import models.AnimatedComponent;

@Getter
public class CanvasController {
    private static CanvasController canvasController = new CanvasController();
    public static CanvasController getInstance() {
        return canvasController;
    }
    private CanvasController() {}

    private Canvas canvas = new Canvas( 650, 700 );
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    public void draw(AnimatedComponent animatedComponent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(animatedComponent.getSprite().getImage(), animatedComponent.getX(), animatedComponent.getY());
//        System.out.println("Draw " + "x: "+x + " y: " + y);
    }

    public void clear(AnimatedComponent animatedComponent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //System.out.println("Clear " + "x: "+x + " y: " + y + " width: " + width + " height: " + height);
        gc.clearRect(animatedComponent.getX(), animatedComponent.getY(), animatedComponent.getWidth(), animatedComponent.getHeight());
    }
}
