package game;
import javax.swing.*;
import java.awt.*;
import blocks.*;

public class mapGrid extends JPanel {
    public JLabel[][] blocks;
    public mapGrid(mainFrame frame){
        super();
        this.setLayout(new GridLayout(frame.g.N, frame.g.N));
        this.setBackground(Color.black);

        blocks = new JLabel[frame.g.N][frame.g.N];

        for(int i = 0; i < frame.g.N; i++){
            for(int j = 0; j < frame.g.N; j++){

                blocks[i][j] = new JLabel();

                if(frame.g.table[i][j] instanceof Wall){

                    blocks[i][j].setBackground(Color.BLUE);
                    blocks[i][j].setOpaque(true);

                }else if(frame.g.table[i][j] instanceof Passage){

                    Passage p = (Passage)frame.g.table[i][j];
                    blocks[i][j].setBackground(Color.black);
                    blocks[i][j].setOpaque(true);
                    if(p.hasFood)
                        blocks[i][j].setIcon(Passage.foodIcon);

                }else if(frame.g.table[i][j] instanceof Ghost){

                    blocks[i][j].setBackground(Color.black);
                    blocks[i][j].setOpaque(true);
                    Ghost g = (Ghost)frame.g.table[i][j];
                    blocks[i][j].setIcon(Ghost.ghostIcons[g.iconSelection]);

                }else if(frame.g.table[i][j] instanceof Player){
                    Player p = (Player)frame.g.table[i][j];
                    if(p.open){
                        switch(p.direction){
                            case UP:
                                blocks[i][j].setIcon(Player.anims[0]);
                                break;
                            case DOWN:
                                blocks[i][j].setIcon(Player.anims[1]);
                                break;
                            case LEFT:
                                blocks[i][j].setIcon(Player.anims[2]);
                                break;
                            case RIGHT:
                                blocks[i][j].setIcon(Player.anims[3]);
                                break;
                        }
                    }else {
                        blocks[i][j].setIcon(Player.closedanim);
                    }
                }else if(frame.g.table[i][j] instanceof Powerup){
                    Powerup p = (Powerup)frame.g.table[i][j];
                    switch (p.type){
                        case SPEEDUP:
                            blocks[i][j].setIcon(Powerup.doubleSpeedIcon);
                            break;
                        case SLOWDOWN:
                            blocks[i][j].setIcon(Powerup.halfSpeedIcon);
                            break;
                        case HALFPOINTS:
                            blocks[i][j].setIcon(Powerup.halfPointsIcon);
                            break;
                        case DOUBLEPOINTS:
                            blocks[i][j].setIcon(Powerup.doublePointsIcon);
                            break;
                        case OVERDRIVE:
                            blocks[i][j].setIcon(Powerup.overdriveIcon);
                            break;
                    }
                }

                blocks[i][j].setHorizontalAlignment(JLabel.CENTER);
                blocks[i][j].setVisible(true);
                this.add(blocks[i][j]);
            }
        }


        this.setVisible(true);
    }

}
