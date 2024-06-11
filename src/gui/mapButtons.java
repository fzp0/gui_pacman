package gui;

import javax.swing.*;
import java.awt.*;

public class mapButtons extends JPanel {

    JButton a,b,c,d,e;

    public mapButtons(mainFrame frame){
        super();
        this.setLayout(new GridLayout(1,5));

        a = new JButton("map1");
        b = new JButton("map2");
        c = new JButton("map3");
        b = new JButton("map4");
        e = new JButton("map5");


        this.add(a); this.add(b); this.add(c); this.add(d); this.add(e);

        this.setVisible(true);
    }
}
