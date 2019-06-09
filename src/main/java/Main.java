import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Bullet;
import models.Game;
import models.Spaceship;
import models.Sprite;

public class Main extends Application {

    private Pane root = new Pane();
    private Canvas canvas = new Canvas( 650, 700 );
    private Scene scene;
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Spaceship spaceship;

    private void addAnimatedComponents() {
        //Todo: getInstance() of Spaceship (Singleton)

        //Create new Spaceship
        spaceship = new Spaceship(
                (int) Math.round(canvas.getWidth() / 2),
                (int) Math.round(canvas.getHeight() - 40),
                new Sprite(Spaceship.imgPath, 50, 40),
                50,
                40,
                15
        );
        spaceship.draw(canvas);
    }

    @Override
    public void start(Stage primaryStage) {
        //Add Canvas to Parent and AnimatedComponents
        root.getChildren().add(canvas);
        addAnimatedComponents();

        //Add Parent to Scene
        Scene scene = new Scene(root);
        //Add Event Actions for keyboard Events
        keyboardEvents(scene);

        primaryStage.setTitle("SpaceInvaders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void keyboardEvents(Scene scene) {
        //Keyboard events
        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();

            //Spaceship
            if(code.contains("LEFT")) { spaceship.moveLeft(canvas); }
            else if(code.contains("RIGHT")) { spaceship.moveRight(canvas); }

            //Bullet
            else if(code.contains("SPACE")) {
                //Create Bullet
                int bulletWidth = 20;
                int bulletHeight = 20;

                Bullet bullet = new Bullet(
                        spaceship.getX() + spaceship.getWidth()/2 - bulletWidth/2,
                        spaceship.getY() - spaceship.getHeight()/2,
                        new Sprite(Bullet.imgPath, 20, 20),
                        bulletWidth,
                        bulletHeight,
                        1
                );

                //AnimationTimer for Bullet
                new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        bullet.moveUp(canvas);
                    }
                }.start();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
