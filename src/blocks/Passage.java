package blocks;

import javax.swing.*;

public class Passage extends Entity {
    public boolean hasFood;
    public static ImageIcon foodIcon;


    public Passage(boolean hasFood){
        super();
        this.hasFood = hasFood;
    }

    public void display(){
        String c = hasFood ? "0" : "_";
        System.out.printf(c);
    }

}
