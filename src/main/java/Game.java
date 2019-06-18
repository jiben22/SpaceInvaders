import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.SpaceCanvas;

public class Game extends Application{
    private Pane root = new Pane();
    private SpaceCanvas spaceCanvas = SpaceCanvas.getInstance();

    @Override
    public void start(Stage primaryStage) {
        //Add SpaceCanvas to Parent
        root.getChildren().add( spaceCanvas.getCanvas() );
        root.setStyle("-fx-background-color: black");

        //Create Scene
        Scene scene = new Scene(root);

        primaryStage.setTitle("SpaceInvaders");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
