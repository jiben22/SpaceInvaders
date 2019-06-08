package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class GameComponent {
    //Movement
    private Integer mX;
    private Integer mY;
    //Sprite image
    private Sprite mSprite;
}
