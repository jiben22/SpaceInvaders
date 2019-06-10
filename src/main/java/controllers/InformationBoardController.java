package controllers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import models.Player;

public class InformationBoardController {
    private static InformationBoardController informationBoardController = new InformationBoardController();
    public static InformationBoardController getInstance() {
        return informationBoardController;
    }
    private InformationBoardController() {}

    private CanvasController canvasController = CanvasController.getInstance();
    private GraphicsContext graphicsContext = canvasController.getCanvas().getGraphicsContext2D();

    public void writeHeader() {
        int heightText = 20;

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText(
                "SCORE < 1 >" + "\n" +
                        "0",
                0,
                heightText);

        graphicsContext.fillText(
                "HIGH SCORE" + "\n" +
                        "0",
                Math.round(canvasController.getCanvas().getWidth() / 2),
                heightText);

        graphicsContext.fillText(
                "SCORE < 2 >" + "\n" +
                        "0",
                Math.round(canvasController.getCanvas().getWidth() - 100),
                heightText);
    }

    public void writeFooter() {
        double heightText =  Math.round(canvasController.getCanvas().getHeight());

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText(
                getTextLives(),
                0,
                heightText);

        graphicsContext.fillText(
                getTextCredits(),
                Math.round(canvasController.getCanvas().getWidth() - 100),
                heightText);
    }

    public String getTextLives() {
        String lTextLives;
        int lLives = new Player().getLives();
        if(lLives == 1) { lTextLives = lLives + " LIFE"; }
        else { lTextLives = lLives + " LIVES"; }

        return lTextLives;
    }

    public String getTextCredits() {
        return (
                "CREDITS " +
                        new Player().getCredits()
        );
    }
}
