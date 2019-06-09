import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.*;
import views.GameBoardView;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application{

    GameBoardView gameBoardView;

    public Main() {
        this.gameBoardView = new GameBoardView();
    }

    @Override
    public void start(Stage primaryStage) {
        //Add Canvas to Parent and AnimatedComponents
        this.gameBoardView.getRoot().getChildren().add(this.gameBoardView.getCanvas());
        this.gameBoardView.getRoot().setStyle("-fx-background-color: black");
        this.gameBoardView.writeHeader();
        this.gameBoardView.writeFooter();
        this.gameBoardView.addAnimatedComponents();

        //Add Parent to Scene
        Scene scene = new Scene(this.gameBoardView.getRoot());
        //Add Event Actions for keyboard Events
        this.gameBoardView.keyboardEvents(scene);

        primaryStage.setTitle("SpaceInvaders");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {


        launch(args);
    }


}
