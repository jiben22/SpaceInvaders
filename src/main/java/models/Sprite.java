package models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Sprite {
    private ImageView imageView;
    private Image image;
    private String spritePath;
    private int widthImg;
    private int heightImg;

    public Sprite(String spritePath, int widthImg, int heightImg) {
        this.spritePath = spritePath;
        this.widthImg = widthImg;
        this.heightImg = heightImg;

        //Create image
        image = new Image(this.spritePath, this.widthImg, this.heightImg, false, false);

        //Create ImageView
        imageView = new ImageView( this.spritePath );
        imageView.setFitWidth( this.widthImg );
        imageView.setFitHeight( this.heightImg );
        //imageView.setPreserveRatio(true);
    }
}
