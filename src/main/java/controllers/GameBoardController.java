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
            switch (e.getCode()) {
                case LEFT:
                    animatedComponentController.moveLeftSpaceship();
                    break;
                case RIGHT:
                    animatedComponentController.moveRightSpaceship();
                    break;
                case SPACE:
                    animatedComponentController.shotBullet();
                    break;
            }
        });
    }
}
