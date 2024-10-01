package blocks;

import javax.swing.*;
public class Ghost extends Entity {

    public static ImageIcon[] ghostIcons;
    public static boolean[] usedGhosts;

    public int iconSelection;
    public boolean lastPassageHadFood;

    public Ghost(){
        lastPassageHadFood = true;

        for(int i = 0; i < usedGhosts.length; i++){
            if(!usedGhosts[i]){
                iconSelection = i;
                usedGhosts[i] = true;
                return;
            }
        }

        //throw new RuntimeException("too many ghosts on the map (max 4)");
    }

    @Override
    public void display() {
        System.out.printf("G");
    }
}
