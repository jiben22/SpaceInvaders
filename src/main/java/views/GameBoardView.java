package views;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;
import models.*;

import java.util.ArrayList;
import java.util.List;

// canvas class
@Getter
@Setter
public class GameBoardView extends WindowView{

    private Canvas canvas = new Canvas( 650, 700 );
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Spaceship spaceship;
    private List<Alien> aliens = new ArrayList<>();

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

    public void writeHeader() {
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

    public void writeFooter() {
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

    public void addAnimatedComponents() {
        //Todo: getInstance() of Spaceship (Singleton)

        //Create new Spaceship
        spaceship = new Spaceship(
                (int) Math.round(canvas.getWidth() / 2),
                (int) Math.round(canvas.getHeight() - 40),
                new Sprite(50, 40, 40, 40),
                40,
                40,
                30,
                0
        );
        this.draw(spaceship);

        //Create new Alien
        int widthAlien = 33;
        int heightAlien = 22;

        int xAlien = 0;
        int yAlien = 0;

        for(int i = 0; i < 5; i++) {
            xAlien += widthAlien;
            aliens.add(
                    new Alien(
                            xAlien,
                            yAlien,
                            new Sprite(547, 200, widthAlien, heightAlien),
                            widthAlien,
                            heightAlien,
                            2
                    )
            );
        }

        for(Alien alien: aliens) {
            this.draw(alien);
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

                    moveRight(alien);
                }
            }
        }.start();
    }

    public void keyboardEvents(Scene scene) {
        //Keyboard events
        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();

            //Spaceship
            if(code.contains("LEFT")) { this.moveLeft(spaceship); }
            else if(code.contains("RIGHT")) { this.moveRight(spaceship); }

            //Bullet
            else if(code.contains("SPACE")) {
                //Create Bullet
                int bulletWidth = 20;
                int bulletHeight = 20;

                Bullet bullet = new Bullet(
                        spaceship.getX() + spaceship.getWidth()/2 - bulletWidth/2,
                        spaceship.getY() - spaceship.getHeight()/2,
                        new Sprite(20, 20, 20, 20),
                        bulletWidth,
                        bulletHeight,
                        10,
                        0
                );

                //AnimationTimer for Bullet
                new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        moveUp(bullet);
                    }
                }.start();
            }
        });
    }


    public void draw(AnimatedComponent animatedComponent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
      gc.drawImage(animatedComponent.getSprite().getImage(), animatedComponent.getX(), animatedComponent.getY());
//        System.out.println("Draw " + "x: "+x + " y: " + y);
    }

    public void clear(AnimatedComponent animatedComponent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //System.out.println("Clear " + "x: "+x + " y: " + y + " width: " + width + " height: " + height);
        gc.clearRect(animatedComponent.getX(), animatedComponent.getY(), animatedComponent.getWidth(), animatedComponent.getHeight());
    }

    public void moveUp(AnimatedComponent animatedComponent) {
        clear(animatedComponent);
        animatedComponent.setY(animatedComponent.getY() - animatedComponent.getYSpeed());
        draw(animatedComponent);
    }

    public void moveRight(AnimatedComponent animatedComponent) {
        clear(animatedComponent);
        animatedComponent.setX(animatedComponent.getX() + animatedComponent.getXSpeed());
        draw(animatedComponent);
    }

    public void moveDown(AnimatedComponent animatedComponent) {
        clear(animatedComponent);
        animatedComponent.setY(animatedComponent.getY() + animatedComponent.getYSpeed());
        draw(animatedComponent);
    }


    public void moveLeft(AnimatedComponent animatedComponent) {
        clear(animatedComponent);
        animatedComponent.setX(animatedComponent.getX() + animatedComponent.getXSpeed());
        draw(animatedComponent);
    }



}
