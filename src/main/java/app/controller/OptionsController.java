package app.controller;

import app.GameLoop;
import app.views.MenuView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import app.model.SpaceCanvas;
import app.views.GameView;
import app.views.OptionsView;

import java.util.List;

public class OptionsController extends Controller{



    public OptionsController(GameLoop gameLoop, Stage stage) {

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

        //Cancel
        optionsView.getCancelButton().setOnAction(actionEvent -> {
            //Show menu scene
            stage.setScene(menuView.getMenuScene());
        });

        //Validate
        optionsView.getValidateButton().setOnAction(actionEvent -> {

            gameLoop.updateParametersGame();
            //Load game and show game scene
            gameLoop.loadGame();
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
