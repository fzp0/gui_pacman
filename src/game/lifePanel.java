package game;

import blocks.Player;

import javax.swing.*;
import java.awt.*;

public class lifePanel extends JPanel {
    public JLabel[] lives;
    public JLabel currentPowerUp;
    public lifePanel(mainFrame frame){
        super();

        lives = new JLabel[3];
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.black);

        for(int i = 0; i < 3; i++){
            lives[i] = new JLabel();
            lives[i].setIcon(Player.anims[3]);
            lives[i].setBackground(Color.black);

            if(i < frame.g.lives)
                lives[i].setVisible(true);
            else
                lives[i].setVisible(false);

            this.add(lives[i]);
        }

        this.setVisible(true);
    }
}
