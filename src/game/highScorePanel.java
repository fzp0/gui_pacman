package game;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class highScorePanel extends JPanel{
    public highScorePanel(mainFrame frame){
        this.setBackground(Color.black);
        this.setForeground(Color.white);

        FileInputStream in = null;
        try{
            in = new FileInputStream("scores.txt");
        }catch(FileNotFoundException e) {
            this.setLayout(new BorderLayout());
            JLabel label = new JLabel("No scores.txt file found, go play some games!");
            this.add(label, BorderLayout.CENTER);
            this.setVisible(true);
            return;
        }

        ObjectInputStream objIn = null;
        try{
            objIn = new ObjectInputStream(in);
            ArrayList<Score> scores = (ArrayList<Score>)objIn.readObject();

            scores.sort( (l,r) -> {
                if(l.score < r.score)
                    return 1;
                else if(l.score > r.score)
                    return -1;
                else
                    return 0;
            });

            String[] formattedScores = new String[scores.size()];
            for(int i = 0; i< scores.size(); i++){
                formattedScores[i] = ((int)i+(int)1) + ". " + scores.get(i).name + " - score: " + scores.get(i).score + ", level: " + scores.get(i).level;
            }
            JList<String> list = new JList<>(formattedScores);
            list.setBackground(Color.black);
            list.setForeground(Color.white);


            objIn.close();
            in.close();
            this.setLayout(new BorderLayout());
            this.add(new JScrollPane(list), BorderLayout.CENTER);
            this.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
