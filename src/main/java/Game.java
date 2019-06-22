import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Alien;
import models.Bullet;
import models.SpaceCanvas;
import models.Spaceship;
import views.MainView;
import views.MenuView;
import views.OptionsView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application{
    private StackPane root = MainView.getInstance().getRoot();

    private SpaceCanvas spaceCanvas = SpaceCanvas.getInstance();
    private Canvas canvas = spaceCanvas.getCanvas();

    private Spaceship spaceship;
    private List<Alien> mAliens;
    private List<Bullet> mBullets;

    private AnimationTimer animationTimer;

    //TODO: set to keyboardEvents()
    private boolean isShownMenuLayer = false;

    @Override
    public void start(Stage theStage) {
        initMenu();
        initOptions();

        //Create Scene
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("./css/app.css").toExternalForm());
        theStage.setTitle("SpaceInvaders");
        theStage.setResizable(true);
        theStage.setScene(scene);
        theStage.show();

        keyboardEvents(scene);
    }

    private void loadGame() {
        //Reset all components
        spaceship = null;
        mAliens = new ArrayList<>();
        mBullets = new ArrayList<>();

        //Add Spaceship
        createSpaceship();

        //Add Aliens
        int aliensPerRow = 5;
        int aliensPerColumn = 5;
        int alienXSpeed = 50;

        createAliens(aliensPerRow, aliensPerColumn, alienXSpeed);

        animationTimer = new AnimationTimer(){
            private long lastUpdate = 0;
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
                if (now - lastUpdate >= 28_000_000 * 10) {
                    //Change direction of aliens if one alien exceed min/max of canvas
                    areAllowedMovingRight = moveAliens(this.areAllowedMovingRight);
                    lastUpdate = now ;
                }
                aliensHaveWon();
                collisionHandler();
                /* Le spaceship se délplace de gauche à droite
                 *
                 * Elements qui demandent une action de l'utilisateur :
                 *  - Spaceship
                 *  - Bullet
                 *
                 *
                 * Elements qui ne demandent pas d'action de l'utilisateur :
                 *  - Alien
                 * */
                for (Alien lAlien: mAliens) {
                    spaceCanvas.clear(lAlien);
                    spaceCanvas.draw(lAlien);
                }

                aliensHaveWon();
                alienWaveIsStillAlive();
            }
        };
        animationTimer.start();
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

    private void collisionHandler(){

        for( int indexBullet = 0; indexBullet < mBullets.size(); indexBullet++ ) {
            Bullet lBullet = mBullets.get(indexBullet);
            for( int indexAlien = mAliens.size()-1; indexAlien >= 0; indexAlien-- ) {
                Alien lAlien = mAliens.get(indexAlien);

                if(lBullet.getX() >= lAlien.getX() - lAlien.getWidth()/2 && lBullet.getX() <= lAlien.getX() + lAlien.getWidth()){
                    if(lBullet.getY() <= lAlien.getY()) {

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

        for(Alien lAlien: mAliens){
            if(lAlien.getY() + lAlien.getHeight() >= spaceship.getY()){
                spaceCanvas.clear(lAlien);
                //TODO: WHAT ?
                //mAliens.removeAll(lAlien);
                spaceCanvas.clear(spaceship);
                System.out.println("Game Over !");
                break;
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
        // si les coordonnées aliens
    }

    private void alienWaveIsStillAlive(){
        // si tous les aliens sont morts, déploiement d'une nouvelle vague plus forte (vitesse++, musique++)
        //Le caractère infini du jeu va se faire ici


    }

    private void keyboardEvents(Scene theStage){

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
                case ESCAPE:
                    pause();
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

    private void initMenu() {
        MenuView menuView = MenuView.getInstance();
        ObservableList<Node> childs = root.getChildren();

        //New Game
        menuView.getNewGameButton().setOnAction( actionEvent -> {
            //Load game
            loadGame();

            //Show game layer
            childs.get(0).toFront();
        });

        //Options
        menuView.getOptionsButton().setOnAction( actionEvent -> {
            //Show options layer
            childs.get(1).toFront();
        });

        //Exit
        menuView.getExitGame().setOnAction( actionEvent -> System.exit(0) );
    }

    private void initOptions() {
        OptionsView optionsView = OptionsView.getInstance();
    }

    private void pause() {
        ObservableList<Node> childs = root.getChildren();

        //Check game is launching
        if ( animationTimer != null ) {
            //If menu layer is not shown
            if ( !isShownMenuLayer ) {
                //Show menu layer
                childs.get(2).toFront();
                //Stop animationTimer
                animationTimer.stop();
                isShownMenuLayer = true;
            } else {
                //Show game layer
                childs.get(0).toFront();
                //Start animationTimer
                animationTimer.start();
                isShownMenuLayer = false;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
