package gui;

import javax.swing.*;
import java.awt.*;
public class mapSelection extends JPanel {

    public JPanel SelectionTitle;
    public JPanel MapButtons;

    mapSelection(mainFrame frame){
        super();
        this.setLayout(new GridLayout(2,1));

        SelectionTitle = new selectionTitle(frame);

        MapButtons = new mapButtons(frame);

        this.add(SelectionTitle);
        this.setVisible(true);
    }
}
