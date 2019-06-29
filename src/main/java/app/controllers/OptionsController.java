package app.controllers;

import app.Main;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import app.views.OptionsView;

import java.util.List;

public class OptionsController extends Controller{



    public OptionsController(Main main, Stage stage) {

        OptionsView optionsView = OptionsView.getInstance();

        //Left Wallpaper
        optionsView.getLeftWallpaperButton().setOnAction(actionEvent -> {
            int indexWallpaper = optionsView.getIndexWallpaper();
            List<ImageView> imageViews = optionsView.getImageViewsWallpapers();



            //Check if there are wallpapers left
            if (indexWallpaper > 0) {
                changeImageViewWallpaper(indexWallpaper - 1);
            }
        });

        //Right Wallpaper
        optionsView.getRightWallpaperButton().setOnAction(actionEvent -> {
            int indexWallpaper = optionsView.getIndexWallpaper();
            List<ImageView> imageViews = optionsView.getImageViewsWallpapers();

            //Check if there are wallpapers left
            if (indexWallpaper < imageViews.size() - 1) {
                changeImageViewWallpaper(indexWallpaper + 1);
            }

            if (indexWallpaper == imageViews.size() - 1) {
                optionsView.getRightAlienButton().setVisible(false);
            }
        });

        //Levels
        optionsView.getEasyButton().setOnAction(keyEvent -> {
            Main.aliensPerRow = 8;
            Main.aliensPerColumn = 3;
            Main.alienXSpeed = 10;
        } );
        optionsView.getMediumButton().setOnAction(keyEvent -> {
            Main.aliensPerRow = 10;
            Main.aliensPerColumn = 4;
            Main.alienXSpeed = 15;
        } );
        optionsView.getHardButton().setOnAction(keyEvent -> {
            Main.aliensPerRow = 10;
            Main.aliensPerColumn = 4;
            Main.alienXSpeed = 20;
        } );

        //Cancel
        optionsView.getCancelButton().setOnAction(actionEvent -> {
            //Show menu scene
            stage.setScene(menuView.getMenuScene());
        });

        //Validate
        optionsView.getValidateButton().setOnAction(actionEvent -> {
            menuView.getVBox().getChildren().remove(menuView.getOptionsButton());
            menuView.getNewGameButton().setText("Play");

            main.updateParametersGame();
            //Load game and show game scene
            main.loadGame();
            stage.setScene(gameView.getGameScene());

        });
    }

    private void changeImageViewWallpaper(int indexWallpaper) {
        //Increment index of wallpaper
        optionsView.setIndexWallpaper( indexWallpaper );
        //Change wallpaper
        ImageView imageView = optionsView.getImageViewsWallpapers().get( optionsView.getIndexWallpaper() );
        optionsView.getImageViewWallpaper().setImage( imageView.getImage() );
    }

}
