package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MenuView {
    private static MenuView ourInstance = new MenuView();
    public static MenuView getInstance() {
        return ourInstance;
    }

    private Pane menuLayer = new Pane();
    private Button newGameButton = new Button("New game");
    private Button optionsButton = new Button("Options");
    private Button exitGame = new Button("Exit");

    private MenuView() {
        initMenuLayer();
    }

    private void initMenuLayer() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);
        vBox.setPrefSize(600, 600);

        //Add Space Invaders logo
        ImageView imageViewLogo = new ImageView(new Image("./images/logo_spaceInvaders.jpg"));

        //Add components to VBox
        vBox.getChildren().addAll(imageViewLogo, newGameButton, optionsButton, exitGame);

        //Add vbox to menu layer
        menuLayer.getChildren().add(vBox);
        //Set background color to menu layer
        menuLayer.setStyle("-fx-background-color: black");
    }
}
