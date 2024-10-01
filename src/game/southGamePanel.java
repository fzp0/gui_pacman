package game;

import blocks.Powerup;

import javax.swing.*;
import java.awt.*;

public class southGamePanel extends JPanel {
    public JPanel LifePanel;
    public JLabel LevelPanel;

    public JLabel PowerupLabel;
    public southGamePanel(mainFrame frame){
        super();
        this.setLayout(new GridLayout(1,3));
        this.setBackground(Color.black);
        LifePanel = new lifePanel(frame);
        LevelPanel = new JLabel("Level: " + frame.g.currentLevel);
        LevelPanel.setForeground(Color.white);
        PowerupLabel = new JLabel();
        if(frame.g.powerUpTimeLeft > 0.f){
            switch(frame.g.currentPowerUp){
                case SPEEDUP:
                    PowerupLabel.setText("2X Speed - " + frame.g.powerUpTimeLeft + "s");
                    break;
                case SLOWDOWN:
                    PowerupLabel.setText("0.5X Speed - " + frame.g.powerUpTimeLeft + "s");
                    break;
                case DOUBLEPOINTS:
                    PowerupLabel.setText("Double Points - " + frame.g.powerUpTimeLeft + "s");
                    break;
                case HALFPOINTS:
                    PowerupLabel.setText("Half Points - " + frame.g.powerUpTimeLeft + "s");
                    break;
                case OVERDRIVE:
                    PowerupLabel.setText("Overdrive - " + frame.g.powerUpTimeLeft + "s");
                    break;
            }
        }

        PowerupLabel.setForeground(Color.white);


        this.add(LifePanel);
        this.add(PowerupLabel);
        this.add(LevelPanel);


        this.setVisible(true);
    }

}
