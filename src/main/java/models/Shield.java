package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Shield extends AnimateComponent {
    public Shield(int x, int y, Sprite sprite, int width, int height, int xSpeed) {
        super(x, y, sprite, width, height, xSpeed);
    }
}
