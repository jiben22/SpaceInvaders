package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class OptionsView {
    private static OptionsView ourInstance = new OptionsView();
    public static OptionsView getInstance() {
        return ourInstance;
    }

    private Pane optionsLayer = new Pane();
    private Button leftButton = new Button("<");
    private Button rightButton = new Button(">");

    private OptionsView() {
        initOptionsLayer();
    }

    private void initOptionsLayer() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(40);
        hBox.setPrefSize(600, 600);

        //Add wallpapers choice
        ImageView imageView1 = new ImageView(new Image("./images/wallpapers/galaxy1.jpg"));
        imageView1.setFitHeight(200);
        imageView1.setFitWidth(200);

        //Add components to VBox
        hBox.getChildren().addAll(leftButton, imageView1, rightButton);

        //Add vbox to menu layer
        optionsLayer.getChildren().add(hBox);
        //Set background color to menu layer
        optionsLayer.setStyle("-fx-background-color: black");
    }
}
