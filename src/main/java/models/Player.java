package models;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Player {
    private String name;
    private int score;
    private int lives;
    private int credits;

    public Player() { }

    public Player(String name, int score, int lives, int credits) {
        this.name = name;
        this.score = score;
        this.lives = lives;
        this.credits = credits;
    }
}
