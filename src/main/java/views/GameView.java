package views;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import lombok.Getter;
import lombok.Setter;
import models.SpaceCanvas;

@Setter @Getter
public class GameView {
    private static GameView ourInstance = new GameView();
    public static GameView getInstance() {
        return ourInstance;
    }

    private Pane gameLayer = new Pane();
    private Canvas canvas = SpaceCanvas.getInstance().getCanvas();

    private GameView() {
        initGameLayer();
    }

    private void initGameLayer() {
        //Add background image to game layer
        BackgroundImage backgroundImage= new BackgroundImage(
                new Image("./images/wallpapers/galaxy1.jpg",
                        canvas.getWidth(),canvas.getHeight(),
                        false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //Set background image to pane
        gameLayer.setBackground(new Background(backgroundImage));
        //Add canvas to root
        gameLayer.getChildren().add(canvas);
    }
}
