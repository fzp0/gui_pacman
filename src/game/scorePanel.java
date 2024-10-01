package game;
import javax.swing.*;
import java.awt.*;

public class scorePanel extends JPanel {

    public scorePanel(mainFrame frame){
        super();

        this.setLayout(new GridLayout(1,2));
        this.setBackground(Color.black);


        JLabel timer = new JLabel("time: " + Float.toString(frame.g.timeElapsed)+"s", SwingConstants.CENTER);
        timer.setForeground(Color.white);


        String scoreStr = "score: " + Integer.toString(frame.g.score);
        JLabel currentScore = new JLabel(scoreStr, SwingConstants.CENTER);
        currentScore.setForeground(Color.white);

        this.add(timer);
        this.add(currentScore);


        this.setVisible(true);
    }

}
