package controllers;

import javafx.scene.Scene;

public class GameBoardController {
    private static GameBoardController gameBoardController = new GameBoardController();
    public static GameBoardController getInstance() {
        return gameBoardController;
    }
    private GameBoardController() {}

    private AnimatedComponentController animatedComponentController = AnimatedComponentController.getInstance();

    public void keyboardEvents(Scene scene) {
        //Keyboard events
        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();

            //Spaceship
            if(code.contains("LEFT")) { animatedComponentController.moveLeftSpaceship(); }
            else if(code.contains("RIGHT")) { animatedComponentController.moveRightSpaceship(); }

            //Bullet
            else if(code.contains("SPACE")) { animatedComponentController.shotBullet(); }
        });
    }
}
