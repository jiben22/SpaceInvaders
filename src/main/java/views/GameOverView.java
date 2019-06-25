package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import models.SpaceCanvas;

@Getter @Setter
public class GameOverView {

    private static GameOverView ourInstance = new GameOverView();
    public static GameOverView getInstance() {
        return ourInstance;
    }

    private Scene gameOverScene;
    private Pane gameOverLayer = new Pane();
    private Button restartButton = new Button("Restart");
    private Button exitGame = new Button("Exit");
    private Label gameOver = new Label("Game Over !");

    private GameOverView() {
        initGameOverLayer();
        this.gameOverScene = new Scene( this.gameOverLayer );
    }

    private void initGameOverLayer() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);
        vBox.setPrefSize(600, 600);

        gameOver.setId("gameover");

        //Add components to VBox
        vBox.getChildren().addAll(gameOver, restartButton, exitGame);

        //Add vbox to menu layer
        gameOverLayer.getChildren().add(vBox);
        //Set background color to menu layer
        gameOverLayer.setStyle("-fx-background-color: black");
    }
}