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
                (int) Math.round(canvasController.getCanvas().getHeight() - 40),
                new Sprite(50, 40, 40, 40),
                40,
                100,
                30
        );
        canvasController.draw(mSpaceship);

        //Create new Alien
        int widthAlien = 33;
        int heightAlien = 22;

        int xAlien = 0;
        int yAlien = 0;

        for(int i = 0; i < 5; i++) {
            xAlien += widthAlien;
            mAliens.add(
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

        for(Alien lAlien: mAliens) {
            canvasController.draw(lAlien);
        }
        //TODO: Create function for AnimationTimer of AnimatedComponent
        //AnimationTimer for Aliens
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for(Alien lAlien: mAliens) {
                    if( lAlien.getX() >= canvasController.getCanvas().getWidth() ) {
                        lAlien.setX(0);
                        lAlien.setY( lAlien.getY() + lAlien.getHeight() );
                    }

                    //Move Alien
                    moveRight(lAlien);
                }
            }
        }.start();
    }

    /* Displacement of the spaceship */
    public void moveLeftSpaceship() { moveLeft(mSpaceship); }
    public void moveRightSpaceship() { moveRight(mSpaceship); }

    /* Shot bullets */
    public void shotBullet() {
        createBullet();
    }

    private void moveUp(AnimatedComponent animatedComponent) {
        canvasController.clear(animatedComponent);
        animatedComponent.setY( animatedComponent.getY() - animatedComponent.getYSpeed() );
        canvasController.draw(animatedComponent);
    }

    private void moveRight(AnimatedComponent animatedComponent) {
        canvasController.clear(animatedComponent);
        animatedComponent.setX( animatedComponent.getX() + animatedComponent.getXSpeed() );
        canvasController.draw(animatedComponent);
    }

    private void moveDown(AnimatedComponent animatedComponent) {
        canvasController.clear(animatedComponent);
        animatedComponent.setY( animatedComponent.getY() + animatedComponent.getYSpeed() );
        canvasController.draw(animatedComponent);
    }


    private void moveLeft(AnimatedComponent animatedComponent) {
        canvasController.clear(animatedComponent);
        animatedComponent.setX( animatedComponent.getX() + animatedComponent.getXSpeed()) ;
        canvasController.draw(animatedComponent);
    }

    public void createBullet()  {
        //Create Bullet
        int bulletWidth = 20;
        int bulletHeight = 20;

        Bullet lBullet = new Bullet(
                mSpaceship.getX() + mSpaceship.getWidth()/2 - bulletWidth/2,
                mSpaceship.getY() - mSpaceship.getHeight()/2,
                new Sprite(20, 20, 20, 20),
                bulletWidth,
                bulletHeight,
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
