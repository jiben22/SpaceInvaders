package models;

import javafx.scene.media.Media;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Paths;

@Getter @Setter
public class Game {
    private int highScore;
    private static String pathToMusic = "musics/spaceInvaders.mp3";
    //private Media mp3MusicFile = new Media(Paths.get(pathToMusic).toUri().toString());

    public Game(int highScore) {
        this.highScore = highScore;
    }
}
