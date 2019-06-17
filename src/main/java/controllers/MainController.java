package controllers;

public class MainController {
    private static MainController mainController = new MainController();
    public static MainController getInstance() {
        return mainController;
    }

    public AnimatedComponentController animatedComponentController;
    public CanvasController canvasController;
    public GameBoardController gameBoardController;
    public InformationBoardController informationBoardController;

    private MainController() {
        animatedComponentController = AnimatedComponentController.getInstance();
        canvasController = CanvasController.getInstance();
        gameBoardController = GameBoardController.getInstance();
        informationBoardController = InformationBoardController.getInstance();
    }

    public void createMenu() {

    }
}
