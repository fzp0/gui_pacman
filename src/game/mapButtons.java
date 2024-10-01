package game;

import blocks.Ghost;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class mapButtons extends JPanel {

    public JButton a,b,c,d,e;

    private void launchGame(mainFrame frame, int selection){
            frame.getContentPane().removeAll();
            frame.g.Setup(selection);
            gamePanel GamePanel = new gamePanel(frame);
            frame.add(GamePanel);
            frame.validate();
            frame.repaint();

            Thread t = new Thread(() -> frame.g.Game(frame));
            t.start();
    }

    public mapButtons(mainFrame frame){
        super();
        this.setLayout(new GridLayout(1,5));
        this.setBackground(Color.black);
        this.setForeground(Color.white);

        a = new JButton("9x9");
        a.setBackground(Color.black);
        a.setForeground(Color.white);
        a.addActionListener( e -> {
            launchGame(frame,0);
        });
        b = new JButton("11x11");
        b.setBackground(Color.black);
        b.setForeground(Color.white);
        b.addActionListener( e -> {
            launchGame(frame,1);
        });
        c = new JButton("15x15");
        c.setBackground(Color.black);
        c.setForeground(Color.white);
        c.addActionListener( e -> {
            launchGame(frame,2);
        });
        d = new JButton("19x19");
        d.setBackground(Color.black);
        d.setForeground(Color.white);
        d.addActionListener( e -> {
            launchGame(frame,3);
        });
        e = new JButton("23x23");
        e.setBackground(Color.black);
        e.setForeground(Color.white);
        e.addActionListener( e -> {
            launchGame(frame,4);
        });

        this.add(a); this.add(b); this.add(c); this.add(d); this.add(e);

        this.setVisible(true);
    }
}
