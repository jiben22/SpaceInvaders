package models;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Player {
    private String name;
    private int score;
    private int lifes;
    private int credits;

    public Player(String name, int score, int lifes, int credits) {
        this.name = name;
        this.score = score;
        this.lifes = lifes;
        this.credits = credits;
    }
}
