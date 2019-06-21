import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class Game extends Application{
    private Pane root = new Pane();
    private SpaceCanvas spaceCanvas = SpaceCanvas.getInstance();
    private Canvas canvas = spaceCanvas.getCanvas();
    private Spaceship spaceship;
    private List<Alien> mAliens = new ArrayList<>();
    private List<Bullet> mBullets = new ArrayList<>();

    @Override
    public void start(Stage theStage) {


        //Add SpaceCanvas to Parent
        root.getChildren().add(canvas);
        //root.setStyle("-fx-background-color: black");

        //Add background image
        BackgroundImage backgroundImage= new BackgroundImage(
                new Image("./images/wallpapers/galaxy1.jpg",
                canvas.getWidth(),canvas.getHeight(),
                        false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //Set background image to pane
        root.setBackground(new Background(backgroundImage));

        //Create Scene
        Scene scene = new Scene(root);

        theStage.setTitle("SpaceInvaders");
        theStage.setResizable(true);
        theStage.setScene(scene);

        //Add Spaceship
        createSpaceship();

        //Add Aliens
        int aliensPerRow = 5;
        int aliensPerColumn = 5;
        int alienXSpeed = 50;

        createAliens(aliensPerRow, aliensPerColumn, alienXSpeed);

        new AnimationTimer(){
            private long lastUpdateAliens = 0;
            private long lastUpdateExplosion = 0;
            private boolean areAllowedMovingRight = true;

            @Override
            public void handle(long now) {

                /*
                 * Les aliens se déplacent de gauche à droite et descendent lorsqu'il touchent le bord du canvas
                 *
                 /* Les bullets se déplacent de bas en haut et détruisent les aliens à leur contact */
                moveBullets();

                /* Check collision between Bullet and Alien
                * Les aliens se déplacent de gauche à droite et descendent lorsqu'il touchent le bord du canvas
                *
                * Les bullets se déplacent de bas en haut et détruisent les aliens à leur contact
                *
                * Le spaceship se délplace de gauche à droite
                *
                * Elements qui demandent une action de l'utilisateur :
                *  - Spaceship
                *  - Bullet
                *
                * Elements qui ne demandent pas d'action de l'utilisateur :
                *  - Alien
                *
                * */
                //
                if (now - lastUpdateAliens >= 28_000_000 * 10) {
                    //Change direction of aliens if one alien exceed min/max of canvas
                    areAllowedMovingRight = moveAliens(this.areAllowedMovingRight);
                    lastUpdateAliens = now ;
                }
                bulletAlienCollisionHandler(now, lastUpdateExplosion);

                 /* Le spaceship se délplace de gauche à droite
                 *
                 * Elements qui demandent une action de l'utilisateur :
                 *  - Spaceship
                 *  - Bullet
                 *
                 *
                 * Elements qui ne demandent pas d'action de l'utilisateur :
                 *  - Alien
                 */
                for (Alien lAlien: mAliens) {
                    spaceCanvas.clear(lAlien);
                    spaceCanvas.draw(lAlien);
                }

                aliensHaveWon();
                alienWaveIsStillAlive();

            }
        }.start();

        keyboardEvents(scene);
        theStage.show();

    }

    private boolean moveAliens(boolean areAllowedMovingRight) {
        for ( Alien lAlien: mAliens ) {
            //Check if aliens do not exceed min/max width of canvas
            if ( lAlien.getX() >= canvas.getWidth() - lAlien.getWidth() ) {
                moveDownAliens();
                areAllowedMovingRight = false;
            }
            else if ( lAlien.getX() <= lAlien.getWidth() ) {
                moveDownAliens();
                areAllowedMovingRight = true;
            }

            //Next frame
            lAlien.getSprite().nextFrameOffsetX();

            //Move according to direction (left/right)
            if( areAllowedMovingRight ) { lAlien.moveRight(); }
            else { lAlien.moveLeft(); }
        }

        return areAllowedMovingRight;
    }

    private void moveDownAliens() {
        for ( Alien lAlien: mAliens ) {
            //Move down all aliens
            lAlien.moveDown();
        }
    }

    private void bulletAlienCollisionHandler(long now, long lastUpdateExplosion){

        for( int indexBullet = 0; indexBullet < mBullets.size(); indexBullet++ ) {
            Bullet lBullet = mBullets.get(indexBullet);
            for( int indexAlien = mAliens.size()-1; indexAlien >= 0; indexAlien-- ) {
                Alien lAlien = mAliens.get(indexAlien);

                if(lBullet.getX() >= lAlien.getX() - lAlien.getWidth()/2 && lBullet.getX() <= lAlien.getX() + lAlien.getWidth()){
                    if(lBullet.getY() <= lAlien.getY()) {

                        createExplosion(lAlien.getX() - lAlien.getWidth(), lAlien.getY() - lAlien.getHeight(), now, lastUpdateExplosion);
                        spaceCanvas.clear(lBullet);
                        spaceCanvas.clear(lAlien);

                        //Remove bullet and alien from List
                        mBullets.remove(indexBullet);
                        mAliens.remove(indexAlien);
                        break;
                    }
                }




            }
        }


        //Foreach bullet, check if there is a collision with an alien

    }

    private void moveBullets() {
        for( int indexBullet = 0; indexBullet < mBullets.size(); indexBullet++ ) {
            Bullet bullet = mBullets.get(indexBullet);
            if( bullet.getY() > 0 ) {
                bullet.getSprite().nextFrameOffsetY();
                bullet.moveUp();
            } else {
                //Clear bullet
                spaceCanvas.clear(bullet);
                //Remove bullet of List
                mBullets.remove(indexBullet);
                break;
            }
        }
    }

    private void aliensHaveWon() {
        for(Alien lAlien: mAliens){
            if(lAlien.getY() + lAlien.getHeight() >= spaceship.getY()){
                spaceCanvas.clear(lAlien);
                spaceCanvas.clear(spaceship);
                spaceCanvas.getCanvas().getGraphicsContext2D().fillText("Game Over !", 0, 0);
                break;
            }
        }
    }

    private void alienWaveIsStillAlive() {
        // si tous les aliens sont morts, déploiement d'une nouvelle vague plus forte (vitesse++, musique++)
        //Le caractère infini du jeu va se faire ici


    }

    public void keyboardEvents(Scene theStage){

        theStage.setOnKeyPressed(e -> {
            switch (e.getCode()){
                //TODO: verify spaceship is NOT NULL !
                case LEFT:
                    if( spaceship.getX() >= 0  ) {
                        spaceship.getSprite().nextFrameOffsetX();
                        spaceship.moveLeft();
                    }
                    break;
                case RIGHT:

                    if( spaceship.getX() <= canvas.getWidth() - spaceship.getWidth()  ) {
                        spaceship.getSprite().nextFrameOffsetX();

                        spaceship.moveRight();
                    }
                    break;
                case SPACE:
                    createBullet();
                    break;
            }
        });

    }

    private void createSpaceship() {
        spaceship = Spaceship.spaceship1((int) canvas.getWidth() / 2, (int) canvas.getHeight(), 10);
        //Get 1st frame of spaceship
        spaceship.getSprite().setWidth( spaceship.getSprite().getWidth() / 2 );
        //Modify x, y positions on canvas of spaceship with his width and his height
        spaceship.setWidth( spaceship.getWidth() * 2 );
        spaceship.setHeight( spaceship.getHeight() * 2 );
        spaceship.setX( spaceship.getX() - spaceship.getWidth() / 2 );
        spaceship.setY( spaceship.getY() - spaceship.getHeight());

        //Draw spaceship
        spaceCanvas.draw(spaceship);
    }

    private void createAliens(int aliensPerRow, int aliensPerColumn, int alienXSpeed) {
        //Init x, y positions on canvas
        int originX = 40;
        int x = originX;
        int y = 40;

        int alienHeight = 0;

        for( int iColumn = 0; iColumn < aliensPerColumn; iColumn++ ) {
            for( int iRow = 0; iRow < aliensPerRow; iRow++ ) {
                //Create new Alien
                Alien alien = Alien.alien2(x, y, alienXSpeed);

                //Define frame of size of alien
                alien.getSprite().setWidth( alien.getSprite().getWidth() / alien.getSprite().getNbFrames() );
                alien.setWidth( (int) (alien.getWidth() * 1.2) );
                alien.setHeight( (int) (alien.getHeight() * 1.2) );

                //Add alien to List
                mAliens.add(alien);
                //Increment x position with alien width
                x += alien.getWidth() + 10;
                if( alienHeight < alien.getHeight() ) { alienHeight = alien.getHeight(); }
            }
            //Increment y position with alien height and reset x position
            x = originX;
            y += alienHeight + 10;
        }

        //Draw aliens
        for (Alien lAlien: mAliens) {
            spaceCanvas.draw(lAlien);
        }
    }

    private void createBullet() {
        //Create Bullet
        Bullet bullet = Bullet.bullet1(
                spaceship.getX() + spaceship.getWidth() / 2,
                spaceship.getY() - spaceship.getHeight(),
                4
        );
        //Define frame of size of bullet
        bullet.getSprite().setHeight( bullet.getSprite().getHeight() / bullet.getSprite().getNbFrames() );
        bullet.setWidth( (int) (bullet.getWidth() * 0.7) );
        bullet.setHeight( (int) (bullet.getHeight() * 0.7) );
        //Set width of height of bullet
        bullet.setX( bullet.getX() - bullet.getWidth() / 2 );
        bullet.setY( bullet.getY() + bullet.getHeight() / 2 );

        //Add bullet to List
        mBullets.add(bullet);

        //Draw bullet
        spaceCanvas.draw(bullet);
    }

    private void createExplosion(int x, int y, long now, long lastUpdateExplosion) {
        Explosion explosion = Explosion.explosion1(x, y);
        explosion.setWidth(explosion.getWidth() / 6);
        explosion.setHeight(explosion.getHeight() / 6);


        //TODO: draw and clear explosion sprite
        for(int iTest=0; iTest <= 1500; iTest++) {
            spaceCanvas.draw(explosion);
        }

        for(int iTest=0; iTest <= 3000; iTest++) {
            spaceCanvas.clear(explosion);
        }


    }

    public static void main(String[] args) {
        launch(args);
    }

}
