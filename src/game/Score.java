package game;

import java.io.Serializable;

public class Score implements Serializable {

    public Score(String name, int score, int level){
        this.name = name;
        this.score = score;
        this.level = level;
    }
        String name;
        int score;
        int level;
}
