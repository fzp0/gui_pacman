package game;

import javax.swing.*;
import java.awt.*;
public class mapSelection extends JPanel {

    public JPanel SelectionTitle;
    public JPanel MapButtons;

    mapSelection(mainFrame frame){
        super();
        this.setLayout(new GridLayout(2,1));
        this.setBackground(Color.black);
        this.setForeground(Color.white);

        SelectionTitle = new selectionTitle(frame);

        MapButtons = new mapButtons(frame);

        this.add(SelectionTitle);
        this.add(MapButtons);
        this.setVisible(true);
    }
}
