package app.controller;

import app.views.GameOverView;
import app.views.GameView;
import app.views.MenuView;
import app.views.OptionsView;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public abstract class Controller {

    protected Stage stage;
    protected GameView gameView = GameView.getInstance();
    protected OptionsView optionsView = OptionsView.getInstance();
    protected MenuView menuView = MenuView.getInstance();
    protected GameOverView gameOverView = GameOverView.getInstance();

    protected boolean isShownMenuScene = false;

    protected AnimationTimer animationTimer;
}
