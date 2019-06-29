package app;

import app.controller.InformationController;
import app.controller.MenuController;
import app.controller.OptionsController;
import app.model.*;
import app.views.GameOverView;
import app.views.GameView;
import app.views.MenuView;
import app.views.OptionsView;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class GameLoop extends Application {

    private MenuView menuView = MenuView.getInstance();
    private GameView gameView = GameView.getInstance();
    private OptionsView optionsView = OptionsView.getInstance();
    private GameOverView gameOverView = GameOverView.getInstance();
    private Stage stage;

    private SpaceCanvas spaceCanvas = SpaceCanvas.getInstance();
    private Canvas canvas = spaceCanvas.getCanvas();
    private InformationController informationController = new InformationController(canvas.getGraphicsContext2D(), canvas);


    private Spaceship spaceship;
    private List<Alien> mAliens;
    private List<Bullet> mBullets;
    private List<Explosion> mExplosions;

    private AnimationTimer animationTimer;

    private app.model.Game game = new app.model.Game(0);
    private Player player = new Player("Player 1", 5, 5);

    private int aliensPerRow;
    private int aliensPerColumn;
    private int alienXSpeed;

    private boolean isShownMenuScene = false;
    private boolean aliensHaveNotJustBeenCreated = false;
    private boolean parametersUpdated = false;

    @Override
    public void start(Stage theStage) {
        this.stage = theStage;
        //Add icon to application
        stage.getIcons().add(new Image("images/icon.png"));

        // initialisation du menu
        new MenuController(this, this.stage);
        // initialisation des options
        new OptionsController(this, this.stage);


        //Add events for scenes
        keyboardEvents( menuView.getMenuScene() );
        keyboardEvents( gameView.getGameScene() );
        keyboardEvents( optionsView.getOptionsScene() );

        //Create Scene
        Scene scene = menuView.getMenuScene();
        theStage.setTitle("SpaceInvaders");
        theStage.setResizable(true);
        theStage.setScene(scene);
        theStage.show();
    }

    public void loadGame() {
        //Clear canvas
        if ( mAliens != null ) {
            for (Alien alien : mAliens) {
                spaceCanvas.clear(alien);
            }
        }
        if ( mBullets != null ) {
            for (Bullet bullet : mBullets) {
                spaceCanvas.clear(bullet);
            }
        }
        if ( spaceship != null ) {
            spaceCanvas.clear(spaceship);
        }

        if ( !parametersUpdated ) {
            this.aliensPerRow = 8;
            this.aliensPerColumn = 3;
            this.alienXSpeed = 10;
        }

        this.player.setLives(5);
        this.player.setScore(0);

        //Reset all components
        this.spaceship = null;
        this.mAliens = new ArrayList<>();
        this.mBullets = new ArrayList<>();
        this.mExplosions = new ArrayList<>();

        //Start music
        //startMusic();

        //Write information
        this.informationController.writeInformation(player, mAliens);

        //Create Spaceship and Aliens
        createSpaceship();
        createAliens(this.aliensPerRow, this.aliensPerColumn, this.alienXSpeed);

        //Create a animation timer
        this.animationTimer = new AnimationTimer() {
            private long lastUpdateAliens = 0;
            private long lastUpdateExplosion = 0;
            private boolean areAllowedMovingRight = true;


            @Override
            public void handle(long now) {
                //Write information
                informationController.writeInformation(player, mAliens);

                /*
                 * Les aliens se déplacent de gauche à droite et descendent lorsqu'il touchent le bord du canvas
                 *
                 /* Les bullets se déplacent de bas en haut et détruisent les aliens à leur contact */

                for (int i = 0; i < 5; i++) moveBullets();


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
                if (now - lastUpdateAliens >= 28_000_000 * 10) {
                    //Change direction of aliens if one alien exceed min/max of canvas
                    areAllowedMovingRight = moveAliens(this.areAllowedMovingRight);
                    lastUpdateAliens = now;
                }
                bulletAlienCollisionHandler();

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
                for (Alien lAlien : mAliens) {
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
            if ( lAlien.getRectangle().getMaxX() >= canvas.getWidth() ) {
                moveDownAliens();
                aliensHaveNotJustBeenCreated = true;
                areAllowedMovingRight = false;
            }
            else if ( lAlien.getRectangle().getMinX() <= 0 && aliensHaveNotJustBeenCreated ) {
                moveDownAliens();
                areAllowedMovingRight = true;
            }
            else if ( lAlien.getRectangle().getMinX() < 0 ) {
                areAllowedMovingRight = true;
            }

            //Next frame
            lAlien.getSprite().nextFrameOffsetX();

            //Move according to direction (left/right)
            if ( areAllowedMovingRight ) { lAlien.moveRight(); }
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

    private void bulletAlienCollisionHandler(){

        for( int indexBullet = 0; indexBullet < mBullets.size(); indexBullet++ ) {
            Bullet lBullet = mBullets.get(indexBullet);

            //Check collision with alien
            for( int indexAlien = mAliens.size()-1; indexAlien >= 0; indexAlien-- ) {
                Alien lAlien = mAliens.get(indexAlien);

                if ( lAlien.intersects(lBullet) ) {

                    //createExplosion(lAlien.getX() - lAlien.getWidth(), lAlien.getY() - lAlien.getHeight());
                    spaceCanvas.clear(lBullet);
                    spaceCanvas.clear(lAlien);

                    //Remove bullet and alien from List
                    mBullets.remove(indexBullet);
                    mAliens.remove(indexAlien);

                    //Increment score
                    player.setScore( player.getScore() + 10 );

                    break;
                }
            }

            //Check if the bullet exceed canvas
            if ( lBullet.getY() < 0 ) {
                player.setScore( player.getScore() - 20 );
            }
        }
    }

    private void moveBullets() {
        for( int indexBullet = 0; indexBullet < mBullets.size(); indexBullet++ ) {
            Bullet bullet = mBullets.get(indexBullet);
            if( bullet.getRectangle().getMaxY() > 0 ) {
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
            if ( lAlien.intersects(spaceship) ) {
                //Remove 1 live of player
                player.setLives( player.getLives() - 1 );

                if (player.getLives() == 0) {
                    stage.setScene( gameOverView.getGameOverScene() );
                    animationTimer.stop();
                } else {

                    for (Alien lAlien2: mAliens) {
                        //Clear and reset aliens
                        spaceCanvas.clear(lAlien2);
                        mAliens = new ArrayList<>();
                        //Draw spaceship
                        spaceCanvas.draw(spaceship);
                    }
                    createAliens(this.aliensPerRow, this.aliensPerColumn, this.alienXSpeed);
                }

                break;
            }
        }
    }

    private void alienWaveIsStillAlive() {

        if (mAliens.isEmpty()) {
            this.aliensPerColumn += 1;
            this.alienXSpeed += 2;
            createAliens(this.aliensPerRow, this.aliensPerColumn, this.alienXSpeed);
        }

    }

    public void updateParametersGame() {

        parametersUpdated =true;
        /* Get all options and apply to game */
        //Get wallpaper
        ImageView imageViewWallpaper = optionsView.getImageViewWallpaper();
        imageViewWallpaper.setFitWidth( gameView.getCanvas().getWidth() );
        imageViewWallpaper.setFitHeight( gameView.getCanvas().getHeight() );

        //Get level
        String level = optionsView.getEasyButton().getText();
        if ( optionsView.getEasyButton().isSelected() ) { level = optionsView.getEasyButton().getText(); }
        else if ( optionsView.getMediumButton().isSelected() ) { level = optionsView.getMediumButton().getText(); }
        else if ( optionsView.getHardButton().isSelected() ) { level = optionsView.getHardButton().getText(); }

        switch (level) {
            case "Easy":
                this.aliensPerRow = 8;
                this.aliensPerColumn = 3;
                this.alienXSpeed = 10;
                parametersUpdated = true;
                break;
            case "Medium":
                this.aliensPerRow = 16;
                this.aliensPerColumn = 5;
                this.alienXSpeed = 15;
                parametersUpdated = true;

                break;
            case "Hard":
                this.aliensPerRow = 20;
                this.aliensPerColumn = 8;
                this.alienXSpeed = 20;
                parametersUpdated = true;
                break;
        }

        //Add background image to game scene
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(
                        imageViewWallpaper.getImage().getUrl(),
                        gameView.getCanvas().getWidth(),
                        gameView.getCanvas().getHeight(),
                        false,
                        true
                ),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        //Set background image to pane
        gameView.getGameLayer().setBackground( new Background(backgroundImage) );
    }

    private void keyboardEvents(Scene theStage){

        theStage.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case LEFT:
                    if( spaceship != null && spaceship.getRectangle().getMinX() >= 0  ) {
                        spaceship.getSprite().nextFrameOffsetX();
                        spaceship.moveLeft();
                    }
                    break;
                case RIGHT:
                    if( spaceship != null && spaceship.getRectangle().getMaxX() <= canvas.getWidth()) {
                        spaceship.getSprite().nextFrameOffsetX();
                        spaceship.moveRight();
                    }
                    break;
                case SPACE:
                    if ( spaceship != null ) {
                        createBullet();
                    }
                    break;
                case ESCAPE:
                    pause();
                    break;
            }
        });
    }

    private void createSpaceship() {
        spaceship = Spaceship.spaceship1((int) canvas.getWidth() / 2, (int) canvas.getHeight() - 20, 5);
        //Get 1st frame of spaceship
        spaceship.getSprite().setWidth( spaceship.getSprite().getWidth() / 2 );
        //Modify x, y positions on canvas of spaceship with its width and height
        spaceship.setWidth( spaceship.getWidth() * 2 );
        spaceship.setHeight( spaceship.getHeight() * 2 );
        spaceship.setX( spaceship.getX() - spaceship.getWidth() / 2 );
        spaceship.setY( spaceship.getY() - spaceship.getHeight());

        //Draw spaceship
        spaceCanvas.draw(spaceship);
    }

    private void createAliens(int aliensPerRow, int aliensPerColumn, int alienXSpeed) {
        //Init x, y positions on canvas
        int originX = 0;
        int x = originX;
        int y = 25;

        this.aliensHaveNotJustBeenCreated = false;

        int alienHeight = 0;

        for( int iColumn = 0; iColumn <= aliensPerColumn; iColumn++ ) {
            for( int iRow = 0; iRow <= aliensPerRow; iRow++ ) {
                //Create new Alien
                Alien alien = Alien.alien1(x, y, alienXSpeed);

                //Define frame of size of alien
                alien.getSprite().setWidth( alien.getSprite().getWidth() / alien.getSprite().getNbFrames() );
                alien.setWidth( alien.getWidth() );
                alien.setHeight( alien.getHeight() );


                //Increment x position with alien width
                x += alien.getWidth() + 10;
                if( alienHeight < alien.getHeight() ) {
                    alienHeight = alien.getHeight();
                }
                //Add alien to List
                mAliens.add(alien);
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
                5
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

    private void createExplosion(int x, int y) {
        Explosion explosion = Explosion.explosion1(x, y);
        explosion.setWidth(explosion.getWidth() / 6);
        explosion.setHeight(explosion.getHeight() / 6);

        mExplosions.add(explosion);

        //TODO: draw and clear explosion sprite
        spaceCanvas.draw(explosion);

        for(int i =0; i<3000; i++){
            for(Explosion expl : mExplosions) {
                spaceCanvas.clear(expl);
            }
        }
        mExplosions.remove(explosion);

    }



    private void pause() {
        //Check game is launching
        if ( animationTimer != null ) {

            //If menu scene is not shown
            if ( !isShownMenuScene) {
                //Show menu scene
                stage.setScene( menuView.getMenuScene() );
                //Stop animationTimer
                animationTimer.stop();
                isShownMenuScene = true;
            } else {
                //Start animationTimer
                animationTimer.start();
                isShownMenuScene = false;

                //Show game scene
                stage.setScene( gameView.getGameScene() );
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
