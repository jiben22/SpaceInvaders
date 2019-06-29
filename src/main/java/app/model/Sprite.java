package app.model;

import javafx.scene.image.*;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Sprite {
    private Image image;
    private ImageView imageView;
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
        this.image = new Image("images/components/invader_transparency.png");
        this.imageView = new ImageView(image);
    }

    public Sprite(String imagePath, int width, int height) {
        this.width = width;
        this.height = height;
        this.image = new Image(imagePath);
        this.imageView = new ImageView(image);
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
