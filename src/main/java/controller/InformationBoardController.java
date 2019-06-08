package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import models.Player;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InformationBoardController implements Initializable {
    @FXML private Canvas informationCanvas;
    private GraphicsContext gc;

    @FXML private void drawCanvas(ActionEvent event) {
        gc.setFill(Color.AQUA);
        gc.fillRect(300,300,300,300);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = informationCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillText(
                getTextLives(),
                Math.round(informationCanvas.getWidth() / 4),
                Math.round(informationCanvas.getHeight() / 2)
        );

        gc.fillText(
                getTextCredits(),
                Math.round(informationCanvas.getWidth() / 2),
                Math.round(informationCanvas.getHeight() / 2)
        );
    }

    public String getTextLives() {
        String lTextLives;
        int lLives = new Player().getLives();
        if(lLives == 1 || lLives == 0) { lTextLives = lLives + " LIFE"; }
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
