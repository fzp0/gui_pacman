package gui;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class titlePanel extends JPanel {
    public titlePanel(){
        this.setLayout(new GridBagLayout());

        JTextPane pacmanTitle = new JTextPane();
        pacmanTitle.setDisabledTextColor(Color.black);
        pacmanTitle.setEditable(false);
        pacmanTitle.setEnabled(false);
        pacmanTitle.setText("PACMAN");
        pacmanTitle.setVisible(true);
        pacmanTitle.setFont(pacmanTitle.getFont().deriveFont(Font.PLAIN,42));

        JPanel panel = new JPanel();
        panel.add(pacmanTitle);

        this.add(panel, new GridBagConstraints());
    }
}
