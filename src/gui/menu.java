package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;
import java.awt.*;

public class menu extends JFrame {

    public menu(){
        this.setLayout(new GridLayout(2,1));


        JPanel TitlePanel = new titlePanel();
        JPanel ButtonPanel = new buttonPanel();

        this.add(TitlePanel);
        this.add(ButtonPanel);

        this.setSize(800,800);
        this.setTitle("Real Pacman");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
