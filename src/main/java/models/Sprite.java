package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Sprite {
    private String spritePath;
    private int widthImg;
    private int heightImg;

    public Sprite(String spritePath, int widthImg, int heightImg) {
        this.spritePath = spritePath;
        this.widthImg = widthImg;
        this.heightImg = heightImg;
    }
}
