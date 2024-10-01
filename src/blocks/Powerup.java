package blocks;

import javax.swing.*;

public class Powerup extends Entity {
    public enum Types{
        SLOWDOWN, SPEEDUP, DOUBLEPOINTS, HALFPOINTS, OVERDRIVE, NONE;
    }

    public static ImageIcon doubleSpeedIcon;
    public static ImageIcon halfSpeedIcon;
    public static ImageIcon doublePointsIcon;
    public static ImageIcon halfPointsIcon;
    public static ImageIcon overdriveIcon;


    public Powerup.Types type;
    public Powerup(int t){
        type = switch(t){
            case 0 -> Types.SPEEDUP;
            case 1 -> Types.SLOWDOWN;
            case 2 -> Types.DOUBLEPOINTS;
            case 3 -> Types.HALFPOINTS;
            case 4 -> Types.OVERDRIVE;
            default -> Types.NONE;
        };
    }
    @Override
    public void display(){
        System.out.printf("H");
    }
}
