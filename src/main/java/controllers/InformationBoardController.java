package controllers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import models.Player;

public class InformationBoardController {
    private static InformationBoardController informationBoardController = new InformationBoardController();
    public static InformationBoardController getInstance() {
        return informationBoardController;
    }
    private InformationBoardController() {}

    private Canvas canvas = CanvasController.getInstance().getCanvas();
    private GraphicsContext graphicsContext = CanvasController.getInstance().getCanvas().getGraphicsContext2D();

    private static final String mScore1 = "SCORE < 1 >";
    private static final String mHiScore = "HIGH SCORE";
    private static final String mScore2 = "SCORE < 2 >";
    private static final String mLife = "LIFE";
    private static final String mLives = "LIVES";
    private static final String mCredits = "CREDITS";

    public void addScores(Player player) {
        graphicsContext.setFill(Color.WHITE);

        //Score1
        graphicsContext.setTextAlign(TextAlignment.LEFT);
        graphicsContext.fillText(
                getTextScore1(player),
                10,
                20);

        //HiScore
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.fillText(
                getTextHiScore(),
                Math.round( canvas.getWidth() / 2 ) ,
                20);

        //Score2
        graphicsContext.setTextAlign(TextAlignment.LEFT);
        graphicsContext.fillText(
                getTextScore2(player),
                Math.round( canvas.getWidth() - 90 ),
                20);
    }

    public void addPlayerInformation(Player player) {
        graphicsContext.setFill(Color.WHITE);
        //Lives
        graphicsContext.fillText(
                getTextLives(player),
                10,
                Math.round( canvas.getHeight() ));
        //Credits
        graphicsContext.fillText(
                getTextCredits(player),
                Math.round( canvas.getWidth() - 90 ),
                Math.round( canvas.getHeight() ));
    }

    public String getTextScore1(Player player) {
        return (
                mScore1 + "\n" +
                        player.getScore()
        );
    }

    public String getTextHiScore() {
        //Todo: max between two players
        return (
                mHiScore + "\n" +
                        "0"
        );
    }

    public String getTextScore2(Player player) {
        return (
                mScore2 + "\n" +
                        player.getScore()
        );
    }

    public String getTextLives(Player player) {
        String lTextLives;
        int lLives = player.getLives();
        if(lLives == 1) { lTextLives = lLives + " " + mLife; }
        else { lTextLives = lLives + " " + mLives; }

        return lTextLives;
    }

    public String getTextCredits(Player player) {
        return (
                mCredits + " " +
                        player.getCredits()
        );
    }
}
