package game;

import javax.swing.*;
import java.awt.*;

public class mainMenu extends JPanel {
    public JPanel TitlePanel;
    public JPanel ButtonPanel;
    public mainMenu(mainFrame frame){
        super();
        this.setLayout(new GridLayout(2,1));
        this.setBackground(Color.black);
        this.setForeground(Color.white);

        TitlePanel = new titlePanel(frame);
        ButtonPanel = new mainMenuButtons(frame);


        this.add(TitlePanel);
        this.add(ButtonPanel);


        this.setVisible(true);
    }
}
