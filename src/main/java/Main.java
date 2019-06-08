import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/main/java/views/informationBoard.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        primaryStage.setTitle( "Timeline Example" );
//
//        Group root = new Group();
//        Scene theScene = new Scene( root );
//        primaryStage.setScene( theScene );
//
//        Canvas canvas = new Canvas( 512, 512 );
//        root.getChildren().add( canvas );
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        Image earth = new Image( "images/alien.gif" );
//        Image sun   = new Image( "images/ship.gif" );
//        Image space = new Image( "images/shot.gif" );
//
//        final long startNanoTime = System.nanoTime();
//
//        new AnimationTimer()
//        {
//            public void handle(long currentNanoTime)
//            {
//                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
//
//                double x = 232 + 128 * Math.cos(t);
//                double y = 232 + 128 * Math.sin(t);
//
//                // background image clears canvas
//                gc.drawImage( space, 0, 0 );
//                gc.drawImage( earth, x, y );
//                gc.drawImage( sun, 196, 196 );
//            }
//        }.start();
//
//        primaryStage.show();
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
