import controllers.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Player;


public class Game extends Application{
    private Pane root = new Pane();
    private MainController mainController = MainController.getInstance();

    @Override
    public void start(Stage primaryStage) {
        //Add SpaceCanvas to Parent
        root.getChildren().add( mainController.canvasController.getCanvas() );
        root.setStyle("-fx-background-color: black");

        //Create player
        Player player = new Player("Player1", 3, 5);
        //Add scores and player information
        mainController.informationBoardController.addScores(player);
        mainController.informationBoardController.addPlayerInformation(player);

        //Create Scene
        Scene scene = new Scene(root);

        //Create menu
        //mainController.gameBoardController.createMenu();

        //Add Animated Components
        mainController.animatedComponentController.addAnimatedComponents();
        //Add Event Actions for keyboard Events
        mainController.gameBoardController.keyboardEvents(scene);

        primaryStage.setTitle("SpaceInvaders");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
