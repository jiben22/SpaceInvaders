package app.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Player {
    private String name;
    private int score;
    private int lives;
    private int credits;

    public Player(String name, int lives, int credits) {
        this.name = name;
        this.score = 0;
        this.lives = lives;
        this.credits = credits;
    }
}
