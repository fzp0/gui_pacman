package game;

import blocks.Ghost;
import blocks.Passage;
import blocks.Player;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel {
    public JPanel ScorePanel;
    public JPanel MapGrid;
    public JPanel SouthPanel;

    public gamePanel(mainFrame frame){



        super();
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), "upArrowAction");
        this.getActionMap().put("upArrowAction", frame.g.UpArrowAction);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "downArrowAction");
        this.getActionMap().put("downArrowAction", frame.g.DownArrowAction);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "rightArrowAction");
        this.getActionMap().put("rightArrowAction", frame.g.RightArrowAction);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "leftArrowAction");
        this.getActionMap().put("leftArrowAction", frame.g.LeftArrowAction);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "escapeAction");
        this.getActionMap().put("escapeAction", frame.g.EscapeAction);


        this.setLayout(new BorderLayout());
        this.setBackground(Color.black);

        ScorePanel = new scorePanel(frame);
        MapGrid = new mapGrid(frame);
        SouthPanel = new southGamePanel(frame);

        this.add(ScorePanel,BorderLayout.NORTH);
        this.add(MapGrid,BorderLayout.CENTER);
        this.add(SouthPanel,BorderLayout.SOUTH);

        this.setVisible(true);
    }

}
