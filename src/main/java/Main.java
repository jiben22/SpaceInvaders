import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Pane root = new Pane();
    private Canvas canvas = new Canvas( 650, 700 );
    private Scene scene;
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Spaceship spaceship;
    private List<Alien> aliens = new ArrayList<>();

    private void writeHeader() {
        int heightText = 20;

        gc.setFill(Color.WHITE);
        gc.fillText(
                "SCORE < 1 >" + "\n" +
                        "0",
                0,
                heightText);

        gc.fillText(
                "HIGH SCORE" + "\n" +
                "0",
                Math.round(canvas.getWidth() / 2),
                heightText);

        gc.fillText(
                "SCORE < 2 >" + "\n" +
                        "0",
                Math.round(canvas.getWidth() - 100),
                heightText);
    }

    private void writeFooter() {
        double heightText =  Math.round(canvas.getHeight());

        gc.setFill(Color.WHITE);
        gc.fillText(
                getTextLives(),
                0,
                heightText);

        gc.fillText(
                getTextCredits(),
                Math.round(canvas.getWidth() - 100),
                heightText);
    }

    private void addAnimatedComponents() {
        //Todo: getInstance() of Spaceship (Singleton)

        //Create new Spaceship
        spaceship = new Spaceship(
                (int) Math.round(canvas.getWidth() / 2),
                (int) Math.round(canvas.getHeight() - 40),
                new Sprite(Spaceship.imgPath, 50, 40),
                50,
                40,
                15
        );
        spaceship.draw(canvas);

        //Create new Alien
        int widthAlien = 50;
        int heightAlien = 50;

        int xAlien = 0;
        int yAlien = 150;

        for(int i = 0; i < 5; i++) {
            xAlien += widthAlien;
            aliens.add(
                    new Alien(
                            xAlien,
                            yAlien,
                            new Sprite(Alien.imgPath, widthAlien, heightAlien),
                            widthAlien,
                            heightAlien,
                            1
                    )
            );
        }

        for(Alien alien: aliens) {
            alien.draw(canvas);
        }

        //AnimationTimer for Aliens
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for(Alien alien: aliens) {
                    if(alien.getX() >= canvas.getWidth()) {
                        alien.setX(0);
                        alien.setY(alien.getY() + alien.getHeight());
                    }

                    alien.moveRight(canvas);
                }
            }
        }.start();
    }

    @Override
    public void start(Stage primaryStage) {
        //Add Canvas to Parent and AnimatedComponents
        root.getChildren().add(canvas);
        root.setStyle("-fx-background-color: black");
        writeHeader();
        writeFooter();
        addAnimatedComponents();

        //Add Parent to Scene
        Scene scene = new Scene(root);
        //Add Event Actions for keyboard Events
        keyboardEvents(scene);

        primaryStage.setTitle("SpaceInvaders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void keyboardEvents(Scene scene) {
        //Keyboard events
        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();

            //Spaceship
            if(code.contains("LEFT")) { spaceship.moveLeft(canvas); }
            else if(code.contains("RIGHT")) { spaceship.moveRight(canvas); }

            //Bullet
            else if(code.contains("SPACE")) {
                //Create Bullet
                int bulletWidth = 20;
                int bulletHeight = 20;

                Bullet bullet = new Bullet(
                        spaceship.getX() + spaceship.getWidth()/2 - bulletWidth/2,
                        spaceship.getY() - spaceship.getHeight()/2,
                        new Sprite(Bullet.imgPath, 20, 20),
                        bulletWidth,
                        bulletHeight,
                        1
                );

                //AnimationTimer for Bullet
                new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        bullet.moveUp(canvas);
                    }
                }.start();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
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
