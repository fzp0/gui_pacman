package gui;

import javax.swing.*;
import java.awt.*;

public class mainMenu extends JPanel {
    public JPanel TitlePanel;
    public JPanel ButtonPanel;
    public mainMenu(mainFrame frame){
        super();
        this.setLayout(new GridLayout(2,1));

        TitlePanel = new titlePanel(frame);
        ButtonPanel = new buttonPanel(frame);


        this.add(TitlePanel);
        this.add(ButtonPanel);


        this.setVisible(true);
    }
}
