package views;

import javafx.scene.Scene;
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

    private Scene gameScene;
    private Pane gameLayer = new Pane();
    private Canvas canvas = SpaceCanvas.getInstance().getCanvas();

    private GameView() {
        initGameLayer();
        this.gameScene = new Scene( this.gameLayer );
    }

    private void initGameLayer() {
        //Add background image to game layer
        BackgroundImage backgroundImage= new BackgroundImage(
                new Image("images/wallpapers/backlit.jpg",
                        canvas.getWidth(),canvas.getHeight(),
                        false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        //Set background image to pane
        gameLayer.setBackground(new Background(backgroundImage));
        //Add canvas to root
        gameLayer.getChildren().add(canvas);
    }
}
