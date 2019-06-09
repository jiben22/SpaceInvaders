package views;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindowView {

    private Pane root = new Pane();
    private Scene scene;

    public void keyboardEvents(){

    }


}
