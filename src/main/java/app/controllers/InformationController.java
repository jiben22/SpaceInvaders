package app.controllers;

import app.models.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class InformationController {

    private GraphicsContext graphicsContext;
    private Canvas canvas;

    public InformationController(GraphicsContext graphicsContext, Canvas canvas) {
        this.graphicsContext = graphicsContext;
        this.canvas = canvas;
    }

    public void writeInformation(Player player, List aliens) {
        writeScore(player);
        writeLivesCredits(player);
        writeAliensNumber(aliens);
        graphicsContext.setFont(new Font("Press Start 2P", 10));

    }

    private void writeScore(Player player) {
        int x = 20;
        int y = 20;

        //Clear
        graphicsContext.clearRect(0, 0, canvas.getWidth(), 30);

        if(player.getScore() < 0) {
            player.setScore(0);
        }
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

    private void writeAliensNumber(List aliens) {

        String lTextAliensNumber;
        int lAliensNumber = aliens.size();
        if(lAliensNumber == 1) {
            lTextAliensNumber = lAliensNumber + " ALIEN LEFT";
        } else {
            lTextAliensNumber = lAliensNumber + " ALIENS LEFT";
        }

        int x = (int) canvas.getWidth() - 150;
        int y = 20;

        //Clear
        graphicsContext.clearRect(x, y, canvas.getWidth(), 30);

        //Draw
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText(
                lTextAliensNumber,
                x,
                y);


    }
}
