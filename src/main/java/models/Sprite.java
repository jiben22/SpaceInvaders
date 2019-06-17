package models;

import javafx.scene.image.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Sprite {
    private final Image image = new Image("images/invader.png");
    private final ImageView imageView = new ImageView(image);
    private int dx;
    private int dy;
    private int width;
    private int height;

    public Sprite(int dx, int dy, int width, int height) {
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
    }
}
