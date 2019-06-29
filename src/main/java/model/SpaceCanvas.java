package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SpaceCanvas {
    private static SpaceCanvas ourInstance = new SpaceCanvas();
    public static SpaceCanvas getInstance() {
        return ourInstance;
    }

    private Canvas canvas;
    private GraphicsContext graphicsContext;

    private SpaceCanvas() {
        this.canvas = new Canvas(600, 650);
        this.graphicsContext = canvas.getGraphicsContext2D();
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


    public void writeInformation(Player player) {
        writeScore(player);
        writeLivesCredits(player);
    }

    private void writeScore(Player player) {
        int x = 20;
        int y = 20;

        //Clear
        graphicsContext.clearRect(0, 0, canvas.getWidth(), 30);

        //Draw
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText(
                "SCORE " + player.getScore(),
                x,
                y,
                100);
    }

    private void writeLivesCredits(Player player) {
        int x = 20;
        int y = (int) (canvas.getHeight() - 10);

        //Clear
        graphicsContext.clearRect(0, y - 10, canvas.getWidth(), 30);

        //Draw
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText(
                getTextLives( player ),
                x,
                y);
    }

    private String getTextLives(Player player) {
        String lTextLives;
        int lLives = player.getLives();
        if(lLives == 1 || lLives == 0) { lTextLives = lLives + " LIFE"; }
        else { lTextLives = lLives + " LIVES"; }

        return lTextLives;
    }
}
