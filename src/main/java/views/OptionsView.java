package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
public class OptionsView {
    private static OptionsView ourInstance = new OptionsView();
    public static OptionsView getInstance() {
        return ourInstance;
    }

    private Scene optionsScene;
    private Pane optionsLayer = new Pane();

    private List<ImageView> imageViewsWallpapers = new ArrayList<>();
    private  ImageView imageViewWallpaper;
    private int indexWallpaper = 0;

    private List<ImageView> imageViewsAliens = new ArrayList<>();
    private  ImageView imageViewAlien;
    private int indexAlien = 0;

    private Label wallpaperLabel = new Label("Wallpaper");
    private Button leftWallpaperButton = new Button("<");
    private Button rightWallpaperButton = new Button(">");

    private Label levelLabel = new Label("Level");
    private ToggleButton easyButton = new ToggleButton("Easy");
    private ToggleButton mediumButton = new ToggleButton("Medium");
    private ToggleButton hardButton = new ToggleButton("Hard");

    private Label alienLabel = new Label("Alien");
    private Button leftAlienButton = new Button("<");
    private Button rightAlienButton = new Button(">");

    private Button cancelButton = new Button("Cancel");
    private Button validateButton = new Button("Validate");

    private OptionsView() {
        initOptionsLayer();
        this.optionsScene = new Scene( this.optionsLayer );
        optionsScene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
    }

    private void initOptionsLayer() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        vBox.setPrefSize(600, 600);

        BorderPane wallpaperBorderPane = createWallpaperBorderPane();
        HBox levelHBox = createLevelHBox();
        //BorderPane alienBorderPane = createAliensBorderPane();
        HBox cancelValidationHBox = createCancelValidateHBox();

        //Add all components to vBox
        vBox.getChildren().addAll(
                wallpaperBorderPane,
                levelHBox,
                cancelValidationHBox
        );

        //Add vBox to options layer
        optionsLayer.getChildren().add(vBox);
        //Set background color to menu layer
        optionsLayer.setStyle("-fx-background-color: black");
    }

    private BorderPane  createWallpaperBorderPane() {
        BorderPane borderPane = new BorderPane();

        //Path of wallpapers
        String imagesPath[] = {
                "images/wallpapers/backlit.jpg",
                "images/wallpapers/astronomyDolomitesEvening.jpg",
                "images/wallpapers/astronomyEvening.jpg",
                "images/wallpapers/coldDaylight.jpg",
                "images/wallpapers/environment.jpg",
                "images/wallpapers/forest.jpg"
        };
        //Add wallpapers choice
        for ( int indexImagesPath = 0; indexImagesPath < imagesPath.length; indexImagesPath++ ) {
            ImageView imageView = createImageView( imagesPath[ indexImagesPath ], 200, 200 );
            imageViewsWallpapers.add(imageView);
        }

        imageViewWallpaper = imageViewsWallpapers.get( indexWallpaper );
        //Add components to VBox
        borderPane.setTop( wallpaperLabel );
        borderPane.setRight( rightWallpaperButton );
        borderPane.setLeft( leftWallpaperButton );
        borderPane.setCenter( imageViewWallpaper );

        //Alignment
        BorderPane.setAlignment( wallpaperLabel, Pos.CENTER );
        BorderPane.setAlignment( rightWallpaperButton, Pos.CENTER );
        BorderPane.setAlignment( leftWallpaperButton, Pos.CENTER );
        BorderPane.setAlignment( imageViewWallpaper, Pos.CENTER );
        //Margin/Padding
        BorderPane.setMargin( imageViewWallpaper, new Insets(50,20,0,20) );
        borderPane.setPadding( new Insets(10,50,0,50) );

        return borderPane;
    }

    private HBox createLevelHBox() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(40);
        hBox.setPrefSize(600, 150);

        //Select easy button by default
        easyButton.setSelected(true);

        //Add components to HBox
        hBox.getChildren().addAll(
                levelLabel,
                easyButton,
                mediumButton,
                hardButton
        );

        return hBox;
    }

    /*private BorderPane  createAliensBorderPane() {
        BorderPane borderPane = new BorderPane();

        //Create all aliens
        Alien alien1 = Alien.alien1(0, 0, 0);
        Alien alien2 = Alien.alien2(0, 0, 0);

        ImageView imageView1 = alien1.getSprite().getImageView();
        imageView1.setFitHeight(200);
        imageView1.setFitWidth(200);
        ImageView imageView2 = alien2.getSprite().getImageView();
        imageView2.setFitHeight(200);
        imageView2.setFitWidth(200);

        //Add aliens choice
        imageViewsAliens.add(imageView1);
        imageViewsAliens.add(imageView2);

        imageViewAlien = imageViewsAliens.get( indexAlien );
        //Add components to VBox
        borderPane.setTop( alienLabel );
        borderPane.setRight( rightAlienButton );
        borderPane.setLeft( leftAlienButton );
        borderPane.setCenter( imageViewAlien );

        //Alignment
        BorderPane.setAlignment( alienLabel, Pos.CENTER );
        BorderPane.setAlignment( rightAlienButton, Pos.CENTER );
        BorderPane.setAlignment( leftAlienButton, Pos.CENTER );
        BorderPane.setAlignment( imageViewAlien, Pos.CENTER );
        //Margin/Padding
        BorderPane.setMargin( imageViewAlien, new Insets(50,20,0,20) );
        borderPane.setPadding( new Insets(10,50,0,50) );

        return borderPane;
    }*/

    private HBox createCancelValidateHBox() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(110);
        hBox.setPrefSize(600, 100);

        cancelButton.setId("cancelButton");
        validateButton.setId("validateButton");

        //Add components to VBox
        hBox.getChildren().addAll(
                cancelButton,
                validateButton
        );

        return hBox;
    }

    private ImageView createImageView(String imagePath, int width, int height) {
        ImageView imageView = new ImageView(new Image( imagePath ));
        imageView.setFitHeight( width );
        imageView.setFitWidth( height );

        return imageView;
    }
}
