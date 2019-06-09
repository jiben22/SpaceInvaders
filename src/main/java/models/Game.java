package models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Game {
    private int highScore;
    private static String soundPath;

    public Game() { }

    public Game(int highScore) {
        this.highScore = highScore;
    }
}
