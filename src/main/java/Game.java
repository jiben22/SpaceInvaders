import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Alien;
import models.SpaceCanvas;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application{
    private Pane root = new Pane();
    private SpaceCanvas spaceCanvas = SpaceCanvas.getInstance();
    private List<Alien> mAliens = new ArrayList<>();

    @Override
    public void start(Stage theStage) {
        //Add SpaceCanvas to Parent
        root.getChildren().add( spaceCanvas.getCanvas() );
        root.setStyle("-fx-background-color: black");

        //Create Scene
        Scene scene = new Scene(root);

        theStage.setTitle("SpaceInvaders");
        theStage.setResizable(true);
        theStage.setScene(scene);

        //Add Aliens
        int aliensPerRow = 5;
        int aliensPerColumn = 5;
        int alienXSpeed = 3;
        createAliens(aliensPerRow, aliensPerColumn, alienXSpeed);

        new AnimationTimer(){
            @Override
            public void handle(long l) {

                /*
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

                aliensHaveWon();
                collisionHandler();
                alienWaveIsStillAlive();




            }
        }.start();



        theStage.show();

    }

    private void collisionHandler(){
        //if(collision) {
            //on retire l'alien en question
            //ajouter explosion
            //retirer explosion
            //retirer bullet
            //mettre à jour le score
        //}
    }

    private void aliensHaveWon() {
        // si les coordonnées aliens
    }

    private void alienWaveIsStillAlive(){
        // si tous les aliens sont morts, déploiement d'une nouvelle vague plus forte (vitesse++, musique++)
        //Le caractère infini du jeu va se faire ici


    }

    public void keyboardEvents(Scene theStage){

        theStage.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case LEFT: break;
                case RIGHT: break;
                case SPACE: break;
            }
        });

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
                Alien alien = Alien.alien1(x, y, alienXSpeed);
                alien.getSprite().setWidth( alien.getSprite().getWidth() / 2 );
                alien.setWidth( (int) (alien.getWidth() * 1.2) );
                alien.setHeight( (int) (alien.getHeight() * 1.2) );
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
}
