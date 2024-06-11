package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class buttonPanel extends JPanel {
    public JButton a,b,c;

    public buttonPanel(mainFrame frame){
        super();
        this.setLayout(new GridLayout(1,3));

        a = new JButton("New Game"); // switch to mapSelection panel
        a.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.MapSelection = new mapSelection(frame);
            frame.add(frame.MapSelection);
            frame.validate();
            frame.repaint();
        });

        b = new JButton("High Scores");
        c = new JButton("Exit");
        c.addActionListener( e -> {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });


        this.add(a); this.add(b); this.add(c);
    }
}
