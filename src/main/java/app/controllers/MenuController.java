package app.controllers;

import app.Main;
import app.views.MenuView;
import javafx.stage.Stage;

public class MenuController extends Controller {

    public MenuController(Main main, Stage stage) {

            MenuView menuView = MenuView.getInstance();

            //New app.Main
            menuView.getNewGameButton().setOnAction( actionEvent -> {

                menuView.getVBox().getChildren().remove(menuView.getOptionsButton());
                menuView.getNewGameButton().setText("Play");

                //Load game
                if(!isShownMenuScene){
                    main.loadGame();
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


                main.loadGame();
                stage.setScene( gameView.getGameScene() );

            });
        }
    }

