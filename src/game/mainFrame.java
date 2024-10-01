package game;

import blocks.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class mainFrame extends JFrame {

    public JPanel MapSelection;
    public gameLogic g;
    public mainFrame(){
        g = new gameLogic();
        Player.anims = new ImageIcon[4];
        Player.anims[0] = new ImageIcon("pacmanopenup.png");
        Player.anims[1] = new ImageIcon("pacmanopendown.png");
        Player.anims[2] = new ImageIcon("pacmanopenleft.png");
        Player.anims[3] = new ImageIcon("pacmanopenright.png");
        Player.closedanim = new ImageIcon("pacmanclosed.png");
        Passage.foodIcon = new ImageIcon("pacmanfood.png");
        Ghost.ghostIcons = new ImageIcon[4];
        for(int i = 0; i < Ghost.ghostIcons.length; i++){
            Ghost.ghostIcons[i] = new ImageIcon("ghost" + Integer.toString(i+1)+".png");
        }
        Ghost.usedGhosts = new boolean[Ghost.ghostIcons.length];

        Powerup.doubleSpeedIcon = new ImageIcon("speedup.png");
        Powerup.halfSpeedIcon = new ImageIcon("slowdown.png");
        Powerup.doublePointsIcon = new ImageIcon("doublepoints.png");
        Powerup.halfPointsIcon = new ImageIcon("halfpoints.png");
        Powerup.overdriveIcon = new ImageIcon("overdrive.png");




        JPanel MenuPanel = new mainMenu(this);
        this.add(MenuPanel);
        this.setSize(800,800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
