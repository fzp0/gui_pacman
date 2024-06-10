package gui;

import javax.swing.*;
import java.awt.*;

public class buttonPanel extends JPanel {
    public buttonPanel(){
        this.setLayout(new GridLayout(1,3));

        JButton a = new JButton("New Game");
        JButton b = new JButton("High Scores");
        JButton c = new JButton("Exit");

        this.add(a); this.add(b); this.add(c);
    }
}
