package models;

import javafx.scene.image.*;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Sprite {
    private ImageView imageView;
    private Image image;
    private static final String pathToSpriteImg = "images/invader.png";
    private int offsetX;
    private int offsetY;
    private int imgWidth;
    private int imgHeight;

     /**
     * Sélection du sprite dans l'image invaders.png dès l'instanciation
     * @param offsetX
      * décalage horizontal dans l'image
     * @param offsetY
      * décalage vertical dans l'image
     * @param imgWidth
      * largeur du sprite
     * @param imgHeight
      * hauteur du sprite
      *
      * Le tout en pixels
     */
    public Sprite(int offsetX, int offsetY, int imgWidth, int imgHeight) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
    }

    public Image getImage() {
        return new Image(pathToSpriteImg);
    }
}
