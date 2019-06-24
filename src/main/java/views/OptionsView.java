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

    private Label backgroundLabel = new Label("Background");
    private Button leftButton = new Button("<");
    private Button rightButton = new Button(">");
    private Label levelLabel = new Label("Level");
    private ToggleButton easyButton = new ToggleButton("Easy");
    private ToggleButton mediumButton = new ToggleButton("Medium");
    private ToggleButton hardButton = new ToggleButton("Hard");

    private OptionsView() {
        initOptionsLayer();
        this.optionsScene = new Scene( this.optionsLayer );
    }

    private void initOptionsLayer() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(200);
        vBox.setPrefSize(600, 600);

        BorderPane backgroundBorderPane = createBackgroundBorderPane();
        HBox levelHBox = createLevelHBox();

        //Add all components and boxes to options layer
        optionsLayer.getChildren().addAll(
                backgroundBorderPane,
                levelHBox
        );
        //Set background color to menu layer
        optionsLayer.setStyle("-fx-background-color: black");
    }

    private BorderPane  createBackgroundBorderPane() {
        BorderPane borderPane = new BorderPane();

        //Create all wallpapers
        ImageView imageView1 = new ImageView(new Image("images/wallpapers/galaxy.jpg"));
        imageView1.setFitHeight(200);
        imageView1.setFitWidth(200);
        ImageView imageView2 = new ImageView(new Image("images/wallpapers/nightFarm.jpg"));
        imageView2.setFitHeight(200);
        imageView2.setFitWidth(200);
        ImageView imageView3 = new ImageView(new Image("images/wallpapers/snowMountain.jpeg"));
        imageView3.setFitHeight(200);
        imageView3.setFitWidth(200);

        //Add wallpapers choice
        imageViewsWallpapers.add(imageView1);
        imageViewsWallpapers.add(imageView2);
        imageViewsWallpapers.add(imageView3);

        imageViewWallpaper = imageViewsWallpapers.get( indexWallpaper );
        //Add components to VBox
        borderPane.setTop( backgroundLabel );
        borderPane.setRight( rightButton );
        borderPane.setLeft( leftButton );
        borderPane.setCenter( imageViewWallpaper );

        //Alignment
        BorderPane.setAlignment( backgroundLabel, Pos.CENTER );
        BorderPane.setAlignment( rightButton, Pos.CENTER );
        BorderPane.setAlignment( leftButton, Pos.CENTER );
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
        hBox.setPrefSize(600, 700);

        //Add components to VBox
        hBox.getChildren().addAll(
                levelLabel,
                easyButton,
                mediumButton,
                hardButton
        );

        return hBox;
    }
}
