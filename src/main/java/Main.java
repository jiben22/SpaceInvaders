import controllers.AnimatedComponentController;
import controllers.CanvasController;
import controllers.GameBoardController;
import controllers.InformationBoardController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application{
    private Pane root = new Pane();

    private AnimatedComponentController animatedComponentController = AnimatedComponentController.getInstance();
    private CanvasController canvasController = CanvasController.getInstance();
    private GameBoardController gameBoardController = GameBoardController.getInstance();
    private InformationBoardController informationBoardController = InformationBoardController.getInstance();

    @Override
    public void start(Stage primaryStage) {
        //Add Canvas to Parent
        root.getChildren().add(canvasController.getCanvas());
        root.setStyle("-fx-background-color: black");

        //Add information
        informationBoardController.writeHeader();
        informationBoardController.writeFooter();

        //Add Animated Components
        animatedComponentController.addAnimatedComponents();

        //Add Parent to Scene
        Scene scene = new Scene(root);
        //Add Event Actions for keyboard Events
        gameBoardController.keyboardEvents(scene);

        primaryStage.setTitle("SpaceInvaders");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
