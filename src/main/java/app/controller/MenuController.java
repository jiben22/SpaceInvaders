package app.controller;

import app.GameLoop;
import app.views.GameView;
import app.views.MenuView;
import app.views.OptionsView;
import javafx.stage.Stage;

public class MenuController extends Controller {

    public MenuController(GameLoop gameLoop, Stage stage) {

            MenuView menuView = MenuView.getInstance();

            //New app.GameLoop
            menuView.getNewGameButton().setOnAction( actionEvent -> {

                menuView.getVBox().getChildren().remove(menuView.getOptionsButton());
                menuView.getNewGameButton().setText("Play");

                //Load game
                if(!isShownMenuScene){
                    gameLoop.loadGame();
                    //Show game scene
                } else{
                    //Start animationTimer
                    animationTimer.start();
                    isShownMenuScene = false;
                }

                stage.setScene( gameView.getGameScene() );

            });

            //Options
            menuView.getOptionsButton().setOnAction( actionEvent -> {
                //Show options scene
                stage.setScene( optionsView.getOptionsScene() );
            });

            //Exit
            menuView.getExitGame().setOnAction( actionEvent -> System.exit(0) );

            gameOverView.getExitGame().setOnAction(actionEvent -> System.exit(0));
            gameOverView.getRestartButton().setOnAction(actionEvent -> {


                gameLoop.loadGame();
                stage.setScene( gameView.getGameScene() );

            });
        }
    }

