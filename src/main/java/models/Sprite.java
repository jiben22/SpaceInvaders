package models;

import javafx.scene.image.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Sprite {
    private final Image image = new Image("images/components/invader_transparency.png");
    private final ImageView imageView = new ImageView(image);
    private int dx;
    private int dy;
    private int width;
    private int height;
    private int currentFrame;
    private int nbFrames;

    public Sprite(int dx, int dy, int width, int height, int currentFrame, int nbFrames) {
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
        this.currentFrame = currentFrame;
        this.nbFrames = nbFrames;
    }

    public void nextFrameOffsetX() {
        if( currentFrame < nbFrames ) {
            //Next frame
            this.dx += this.width;
            this.currentFrame += 1;
        }
        else {
            //Reset to frame 1
            this.dx -= this.width * (nbFrames - 1);
            this.currentFrame = 1;
        }
    }

    public void nextFrameOffsetY() {
        if( currentFrame < nbFrames ) {
            //Next frame
            this.dy += this.height;
            this.currentFrame += 1;
        }
        else {
            //Reset to frame 1
            this.dy -= this.height * (nbFrames - 1);
            this.currentFrame = 1;
        }
    }
}
