package gui;

import javax.swing.*;

public class mainFrame extends JFrame {

    public JPanel MapSelection;

    public mainFrame(){
        JPanel MenuPanel = new mainMenu(this);
        this.add(MenuPanel);
        this.setSize(800,800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
