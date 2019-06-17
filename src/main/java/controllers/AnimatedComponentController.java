package controllers;

import javafx.animation.AnimationTimer;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class AnimatedComponentController {
    private static AnimatedComponentController animatedComponentController = new AnimatedComponentController();
    public static AnimatedComponentController getInstance() {
        return animatedComponentController;
    }
    private AnimatedComponentController() {}

    private CanvasController canvasController = CanvasController.getInstance();

    private Spaceship mSpaceship;
    private List<Alien> mAliens = new ArrayList<>();
    private List<Bullet> mBullets = new ArrayList<>();
    private List<Shield> mShields = new ArrayList<>();

    public void addAnimatedComponents() {
        //Todo: getInstance() of Spaceship (Singleton)

        //Create new Spaceship
        mSpaceship = new Spaceship(
                (int) Math.round(canvasController.getCanvas().getWidth() / 2),
                (int) Math.round(canvasController.getCanvas().getHeight() - 100),
                new Sprite(Spaceship.offsetX, Spaceship.offsetY, Spaceship.imgWidth/2, Spaceship.imgHeight),
                Spaceship.imgWidth/2,
                Spaceship.imgHeight,
                30
        );
        canvasController.draw(mSpaceship);

        //Define number of aliens per line/column
        int numberAliensPerLine = 1;
        int numberAliensPerColumn = 1;

        //Create Aliens
        int yAlien = 50;
        for( int iLine = 0; iLine < numberAliensPerLine; iLine++ ) {
            //Start at x = 0
            int xAlien = 0;
            for( int iColumn = 0; iColumn < numberAliensPerColumn; iColumn++ ) {
                //Increment x
                xAlien += Alien.imgWidth + 5;

                mAliens.add(
                        new Alien(
                                xAlien,
                                yAlien,
                                new Sprite(Alien.offsetX, Alien.offsetY, Alien.imgWidth/2, Alien.imgHeight),
                                Alien.imgWidth/2,
                                Alien.imgHeight,
                                2
                        )
                );
            }

            //Increment y
            yAlien += Alien.imgHeight + 5;
        }

        for(Alien lAlien: mAliens) {
            canvasController.draw(lAlien);
        }
        //TODO: Create function for AnimationTimer of AnimatedComponent
        //AnimationTimer for Aliens
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for(Alien lAlien: mAliens) {
                    moveAlien(lAlien);
                }
            }
        }.start();
    }

    /* Displacement of the spaceship */
    public void moveLeftSpaceship() {
        if( mSpaceship.getX() - Spaceship.imgWidth > 0 ) { moveLeft(mSpaceship); }
    }
    public void moveRightSpaceship() {
        if( mSpaceship.getX() + Spaceship.imgWidth < canvasController.getCanvas().getWidth() ) {
            moveRight(mSpaceship);
        }
    }

    /* Displacement of Aliens */
    public void moveAlien(Alien alien) {

        if( alien.getX() < alien.getWidth() ) {
            for (Alien lAlien: mAliens) {
                lAlien.setY(lAlien.getY() + lAlien.getHeight());
            }
        }
        else if ( alien.getX() > canvasController.getCanvas().getWidth() + alien.getWidth() ) {
            for (Alien lAlien: mAliens) {
                lAlien.setY(lAlien.getY() + lAlien.getHeight());
            }
        }
    }

    /* Shot bullets */
    public void shotBullet() {
        createBullet();
    }

    public void createBullet()  {
        Bullet lBullet = new Bullet(
                mSpaceship.getX() + mSpaceship.getWidth()/2 - Bullet.imgWidth/2,
                mSpaceship.getY() - mSpaceship.getHeight()/2,
                new Sprite(Bullet.offsetX, Bullet.offsetY, Bullet.imgWidth, Bullet.imgHeight/3),
                Bullet.imgWidth,
                Bullet.imgHeight/3,
                10
        );

        //Add bullet to bullets
        mBullets.add(lBullet);

        //AnimationTimer for Bullet
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveUp(lBullet);
            }
        }.start();
    }
}
