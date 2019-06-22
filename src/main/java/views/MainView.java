package views;

import javafx.scene.layout.StackPane;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MainView {
    private static MainView ourInstance = new MainView();
    public static MainView getInstance() {
        return ourInstance;
    }

    private StackPane root = new StackPane();

    private MainView() {
        MenuView menuView = MenuView.getInstance();
        GameView gameView = GameView.getInstance();
        OptionsView optionsView = OptionsView.getInstance();

        //Add all layers
        root.getChildren().addAll(
                gameView.getGameLayer(),
                optionsView.getOptionsLayer(),
                menuView.getMenuLayer()
        );
    }
}
