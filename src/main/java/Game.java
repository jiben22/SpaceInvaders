import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.SpaceCanvas;


public class Game extends Application{
    private Pane root = new Pane();
    private SpaceCanvas spaceCanvas = SpaceCanvas.getInstance();

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
        if(collision) {
            //on retire l'alien en question
            //ajouter explosion
            //retirer explosion
            //retirer bullet
            //mettre à jour le score
        }
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
    public static void main(String[] args) { launch(args); }
}
