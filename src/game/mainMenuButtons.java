package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class mainMenuButtons extends JPanel {
    public JButton a,b,c;

    public mainMenuButtons(mainFrame frame){
        super();
        this.setLayout(new GridLayout(1,3));
        this.setBackground(Color.black);
        this.setForeground(Color.white);

        a = new JButton("New Game"); // switch to mapSelection panel
        a.setBackground(Color.black);
        a.setForeground(Color.white);
        a.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.MapSelection = new mapSelection(frame);
            frame.add(frame.MapSelection);
            frame.validate();
            frame.repaint();
        });

        b = new JButton("High Scores");
        b.setBackground(Color.black);
        b.setForeground(Color.white);
        b.addActionListener(e -> {
            frame.getContentPane().removeAll();
            JPanel highScorePanel = new highScorePanel(frame);
            frame.add(highScorePanel);
            frame.validate();
            frame.repaint();
        });

        c = new JButton("Exit");
        c.setBackground(Color.black);
        c.setForeground(Color.white);
        c.addActionListener( e -> {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });


        this.add(a); this.add(b); this.add(c);
    }
}
