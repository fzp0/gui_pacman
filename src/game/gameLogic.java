package game;

import blocks.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class gameLogic {

    public enum Directions{
        UP,RIGHT,DOWN,LEFT,NONE;
    }
    Directions direction = Directions.NONE;
    public Action UpArrowAction;
    public Action DownArrowAction;
    public Action RightArrowAction;
    public Action LeftArrowAction;

    public Action EscapeAction;

    public int[][] baseTable;
    public Entity[][] table;
    public int N;
    int score;
    int lives;
    float timeElapsed;
    Powerup.Types currentPowerUp; // change to enum soon
    float powerUpTimeLeft;
    public boolean isRunning = false;
    float scoreMultiplier = 1.0f;

    int currentLevel;

    boolean alreadyMovedThisFrame = false;

    float lastPowerupGeneratedTimestamp;

    public gameLogic(){
        UpArrowAction = new upArrowAction();
        DownArrowAction = new downArrowAction();
        RightArrowAction = new rightArrowAction();
        LeftArrowAction = new leftArrowAction();
        EscapeAction = new escapeAction();
    }

    private void createEntityTableFromIntTable(int[][] input){
        Arrays.fill(Ghost.usedGhosts,false);
        table = new Entity[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                switch(input[i][j]){
                    case 0: // nothing
                        table[i][j] = new Passage(true);
                        break;
                    case 1: // wall
                        table[i][j] = new Wall();
                        break;
                    case 2: // pacman
                        table[i][j] = new Player();
                        break;
                    case 3: // ghost
                        table[i][j] = new Ghost();
                        break;
                }
            }
        }
    }
    public void Setup(int mapSize){
        switch(mapSize){
            case 0: // 9x9 https://imgur.com/a/sYe0E5g
                N = 9;
                baseTable = new int[][]{
                        {1, 0, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 1},
                        {1, 1, 0, 3, 0, 3, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 0, 1, 1, 0, 1},
                        {1, 1, 1, 0, 0, 0, 1, 1, 1},
                        {1, 0, 1, 1, 0, 1, 1, 0, 1},
                        {0, 0, 0, 0, 2, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1}
                };

                createEntityTableFromIntTable(
                        baseTable
                );

                break;
            case 1: // 11x11
                N = 11;

                baseTable = new int[][]{
                        {1,1,1,1,1,1,1,1,1,1,1},
                        {1,0,0,0,0,1,0,0,0,2,1},
                        {1,0,1,1,0,1,0,1,1,0,1},
                        {1,3,1,0,0,0,0,0,1,0,1},
                        {1,0,1,0,1,1,1,3,1,0,1},
                        {1,0,0,0,0,0,0,0,0,0,1},
                        {1,0,1,0,1,1,1,0,1,0,1},
                        {1,0,1,0,0,0,0,0,1,3,1},
                        {1,0,1,1,3,1,0,1,1,0,1},
                        {1,0,0,0,0,1,0,0,0,0,1},
                        {1,1,1,1,1,1,1,1,1,1,1}
                };

                createEntityTableFromIntTable(
                        baseTable
                );

                break;
            case 2: // 15x15

                N=15;
                baseTable = new int[][]{
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 1, 0, 0, 2, 0, 0, 1, 0, 0, 0, 1},
                        {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                };

                createEntityTableFromIntTable(
                        baseTable
                );

                break;
            case 3: // 19x19
                N=19;

                baseTable = new int[][]{
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 1, 3, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 0, 1, 0, 0, 0, 2, 0, 0, 0, 1, 0, 1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                        {1, 0, 1, 3, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 3, 1, 0, 1},
                        {1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 0, 1},
                        {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                };


                createEntityTableFromIntTable(
                        baseTable
                );

                break;
            case 4: // 23x23
                N=23;
                baseTable = new int[][]{
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 1, 3, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 3, 1, 1, 1, 0, 1},
                        {1, 0, 1, 1, 0, 0, 0, 1, 0, 2, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 0, 1, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                };

                createEntityTableFromIntTable(
                        baseTable
                );

                break;
            default:
                throw new RuntimeException("No Proper Selection");
        }


        for(int i = 0; i< N; i++){
            for(int j = 0; j < N; j++){
                this.table[i][j].display();
                System.out.print(" ");
            }
            System.out.println();
        }


    }
    public void Game(mainFrame frame){
        isRunning = true;
        score = 0;
        lives = 3;
        timeElapsed = 0.f;
        currentPowerUp = Powerup.Types.NONE;
        powerUpTimeLeft = 0.f;
        currentLevel = 1;
        lastPowerupGeneratedTimestamp = 0.f;

        while(isRunning){ // lose conditions
            AdvanceFrame(frame);
        }

        frame.getContentPane().removeAll();
        gamePanel updatedGamePanel = new gamePanel(frame);
        frame.add(updatedGamePanel);
        frame.validate();
        frame.repaint();

        // input name messagebox after losing
        String name = JOptionPane.showInputDialog("Your name for the scoreboard: ");

        if(name == null){
            frame.getContentPane().removeAll();
            mainMenu mm = new mainMenu(frame);
            frame.add(mm);
            frame.validate();
            frame.repaint();
            return;
        }

        Score newScore = new Score(name,score,currentLevel);
        FileInputStream in = null;
        ObjectInputStream objIn = null;
        try{
            in = new FileInputStream("scores.txt");
        }catch(FileNotFoundException e){
            File f = new File("scores.txt");
            try{
                f.createNewFile();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }finally{
            try{
                in = new FileInputStream("scores.txt");
                objIn = new ObjectInputStream(in);
            }catch(EOFException x){

            }
            catch(Exception e) {
                e.printStackTrace();
            }

            ArrayList<Score> scores;
            try{
                scores = (ArrayList<Score>)objIn.readObject();
                objIn.close();
                in.close();
            }catch(Exception e){
                scores = new ArrayList<Score>();
            }
            scores.add(newScore);

            try{
                FileOutputStream out = new FileOutputStream("scores.txt");
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                objOut.writeObject(scores);
                objOut.close();
                out.close();
            }catch(Exception e){
              e.printStackTrace();
            }
            frame.getContentPane().removeAll();
            mainMenu mm = new mainMenu(frame);
            frame.add(mm);
            frame.validate();
            frame.repaint();
        }

    }
    public void AdvanceFrame(mainFrame frame){
        try{
            alreadyMovedThisFrame = false;

            checkForReset();
            moveGhosts();

            float gameInterval = 0.25f;

            if(powerUpTimeLeft > 0.f){
                switch(currentPowerUp){
                    case SPEEDUP:
                        gameInterval = 0.125f;
                        break;
                    case SLOWDOWN:
                        gameInterval = 0.5f;
                        break;
                    case HALFPOINTS:
                        scoreMultiplier = 0.5f;
                        break;
                    case DOUBLEPOINTS:
                        scoreMultiplier = 2.f;
                        break;
                }
                powerUpTimeLeft-=gameInterval;
            }else if(powerUpTimeLeft == 0.f && currentPowerUp != Powerup.Types.NONE){
                currentPowerUp = Powerup.Types.NONE;
                scoreMultiplier = 1.f;
            }

            //create updated gamePanel
            frame.getContentPane().removeAll();
            gamePanel updatedGamePanel = new gamePanel(frame);
            frame.add(updatedGamePanel);
            frame.validate();
            frame.repaint();



            timeElapsed+=gameInterval;
            Thread.sleep((int)(gameInterval * 1000));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void checkForReset(){

        if(!findGhost()){
            alreadyMovedThisFrame = true;
            createEntityTableFromIntTable(baseTable);
            return;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(table[i][j] instanceof Passage){
                    Passage p = (Passage)table[i][j];
                    if(p.hasFood)
                        return;
                }
            }
        }

        alreadyMovedThisFrame = true;
        createEntityTableFromIntTable(baseTable);
        currentLevel++;
    }
    private void death(){
        lives--;
        if(lives==0){
            isRunning = false;
        }else{
            createEntityTableFromIntTable(baseTable);
        }
    }
    private boolean findGhost(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(table[i][j] instanceof Ghost)
                    return true;
            }
        }
        return false;
    }
    private boolean findPowerup(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(table[i][j] instanceof Powerup)
                    return true;
            }
        }
        return false;
    }
    private void moveGhosts(){
        for(int i = 0; i < N; i++){
            for(int j =0; j < N; j++){
                if(table[i][j] instanceof Ghost){
                   if(Math.random() < 0.2){
                       Ghost g = (Ghost)table[i][j];
                       List<Directions> directionsList = new ArrayList<Directions>();
                       if(i!=0){
                           if(table[i-1][j] instanceof Passage){
                               directionsList.add(Directions.UP);
                           }
                       }
                       if(i!=N-1){
                           if(table[i+1][j] instanceof Passage){
                               directionsList.add(Directions.DOWN);
                           }
                       }
                       if(j!=0){
                           if(table[i][j-1] instanceof Passage){
                               directionsList.add(Directions.LEFT);
                           }
                       }
                       if(j!=N-1){
                           if(table[i][j+1] instanceof Passage){
                               directionsList.add(Directions.RIGHT);
                           }
                       }

                       if(directionsList.isEmpty())
                           continue;

                       Entity trail;

                       if(Math.random() <= 0.25 && timeElapsed - lastPowerupGeneratedTimestamp >= 5.f && !findPowerup() && powerUpTimeLeft == 0.f){
                           trail = new Powerup((int)(Math.random() * 5));
                           lastPowerupGeneratedTimestamp = timeElapsed;
                       }else{
                            trail = new Passage(g.lastPassageHadFood);
                       }

                       Passage toReplace;
                       switch(directionsList.get((int)(Math.random() * directionsList.size()))){
                           case Directions.UP:
                               toReplace = (Passage)table[i-1][j];
                               g.lastPassageHadFood = toReplace.hasFood;
                               table[i-1][j] = table[i][j];
                               table[i][j] = trail;
                               break;
                           case Directions.DOWN:
                               toReplace = (Passage)table[i+1][j];
                               g.lastPassageHadFood = toReplace.hasFood;
                               table[i+1][j] = table[i][j];
                               table[i][j] = trail;
                               break;
                           case Directions.LEFT:
                               toReplace = (Passage)table[i][j-1];
                               g.lastPassageHadFood = toReplace.hasFood;
                               table[i][j-1] = table[i][j];
                               table[i][j] = trail;
                               break;
                           case Directions.RIGHT:
                               toReplace = (Passage)table[i][j+1];
                               g.lastPassageHadFood = toReplace.hasFood;
                               table[i][j+1] = table[i][j];
                               table[i][j] = trail;
                               break;
                       }
                   }
                }
            }
        }
    }
    private void handleMovingIntoGhost(int coordX, int coordY, int targetX, int targetY){
        if(!(currentPowerUp == Powerup.Types.OVERDRIVE && powerUpTimeLeft > 0.f)) {
            death();
        }else{
            score += 100 * scoreMultiplier;
            Player player = (Player)table[coordY][coordX];
            player.open = !player.open;
            player.direction = Directions.UP;
            table[targetY][targetX] = table[coordY][coordX];
            table[coordY][coordX] = new Passage(false);
        }
    }
    private void movePacman(Directions d){
        if(alreadyMovedThisFrame)
            return;

        int coordX = -1, coordY = -1;

        // find player
        for(int i = 0; i < N; i++){
            boolean breakout = false;
            for(int j = 0; j < N; j++){
                if(table[i][j] instanceof Player){
                    coordY = i;
                    coordX = j;
                    breakout = true;
                    break;
                }
            }
            if(breakout) break;
        }

        if(coordX == -1)
            throw new RuntimeException("couldnt find pacman to move (somethings really wrong)");

        switch(d) {
            case UP:
                if(coordY - 1 >= 0) {
                    if (table[coordY - 1][coordX] instanceof Passage) {
                        Passage p = (Passage) table[coordY - 1][coordX];
                        if (p.hasFood)
                            score += 10 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.UP;
                        table[coordY - 1][coordX] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);


                    } else if (table[coordY - 1][coordX] instanceof Ghost) {
                        handleMovingIntoGhost(coordX,coordY,coordX,coordY-1);
                    } else if (table[coordY - 1][coordX] instanceof Powerup) {
                        Powerup p = (Powerup) table[coordY - 1][coordX];
                        switch (p.type){
                            case SPEEDUP:
                                currentPowerUp = Powerup.Types.SPEEDUP;
                                break;
                            case SLOWDOWN:
                                currentPowerUp = Powerup.Types.SLOWDOWN;
                                break;
                            case HALFPOINTS:
                                currentPowerUp = Powerup.Types.HALFPOINTS;
                                break;
                            case DOUBLEPOINTS:
                                currentPowerUp = Powerup.Types.DOUBLEPOINTS;
                                break;
                            case OVERDRIVE:
                                currentPowerUp = Powerup.Types.OVERDRIVE;
                                break;
                        }
                        powerUpTimeLeft=5.f;
                        score += 20 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.UP;
                        table[coordY - 1][coordX] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }
                }else { // going around the back of the map
                    if(table[N-1][coordX] instanceof Passage){
                        Passage p = (Passage) table[N-1][coordX];
                        if (p.hasFood)
                            score += 10 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.UP;
                        table[N-1][coordX] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }else if(table[N-1][coordX] instanceof Ghost){
                        handleMovingIntoGhost(coordX,coordY,coordX,N-1);
                    }
                    else if(table[N-1][coordX] instanceof Powerup){
                        Powerup p = (Powerup) table[N-1][coordX];
                        switch (p.type){
                            case SPEEDUP:
                                currentPowerUp = Powerup.Types.SPEEDUP;
                                break;
                            case SLOWDOWN:
                                currentPowerUp = Powerup.Types.SLOWDOWN;
                                break;
                            case HALFPOINTS:
                                currentPowerUp = Powerup.Types.HALFPOINTS;
                                break;
                            case DOUBLEPOINTS:
                                currentPowerUp = Powerup.Types.DOUBLEPOINTS;
                                break;
                            case OVERDRIVE:
                                currentPowerUp = Powerup.Types.OVERDRIVE;
                                break;
                        }
                        powerUpTimeLeft=5.f;
                        score += 20 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.UP;
                        table[N-1][coordX] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }
                }
                break;
            case DOWN:
                if(coordY + 1 < N) {
                    if (table[coordY + 1][coordX] instanceof Passage) {
                        Passage p = (Passage) table[coordY + 1][coordX];
                        if (p.hasFood)
                            score += 10 * scoreMultiplier;


                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.DOWN;
                        table[coordY + 1][coordX] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);


                    } else if (table[coordY + 1][coordX] instanceof Ghost) {
                        handleMovingIntoGhost(coordX,coordY,coordX,coordY+1);
                    } else if (table[coordY + 1][coordX] instanceof Powerup){
                        Powerup p = (Powerup) table[coordY + 1][coordX];
                        switch (p.type){
                            case SPEEDUP:
                                currentPowerUp = Powerup.Types.SPEEDUP;
                                break;
                            case SLOWDOWN:
                                currentPowerUp = Powerup.Types.SLOWDOWN;
                                break;
                            case HALFPOINTS:
                                currentPowerUp = Powerup.Types.HALFPOINTS;
                                break;
                            case DOUBLEPOINTS:
                                currentPowerUp = Powerup.Types.DOUBLEPOINTS;
                                break;
                            case OVERDRIVE:
                                currentPowerUp = Powerup.Types.OVERDRIVE;
                                break;
                        }
                        powerUpTimeLeft=5.f;
                        score += 20 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.UP;
                        table[coordY + 1][coordX] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }
                }else{
                    if(table[0][coordX] instanceof Passage){
                        Passage p = (Passage) table[0][coordX];
                        if (p.hasFood)
                            score += 10 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.DOWN;
                        table[0][coordX] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }else if(table[0][coordX] instanceof Ghost){
                        handleMovingIntoGhost(coordX,coordY,coordX,0);
                    }else if(table[0][coordX] instanceof Powerup){
                        Powerup p = (Powerup) table[0][coordX];
                        switch (p.type){
                            case SPEEDUP:
                                currentPowerUp = Powerup.Types.SPEEDUP;
                                break;
                            case SLOWDOWN:
                                currentPowerUp = Powerup.Types.SLOWDOWN;
                                break;
                            case HALFPOINTS:
                                currentPowerUp = Powerup.Types.HALFPOINTS;
                                break;
                            case DOUBLEPOINTS:
                                currentPowerUp = Powerup.Types.DOUBLEPOINTS;
                                break;
                            case OVERDRIVE:
                                currentPowerUp = Powerup.Types.OVERDRIVE;
                                break;
                        }
                        powerUpTimeLeft=5.f;
                        score += 20 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.UP;
                        table[0][coordX] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }
                }

                break;
            case LEFT:
                if (coordX - 1 >= 0) {
                    if (table[coordY][coordX - 1] instanceof Passage) {
                        Passage p = (Passage) table[coordY][coordX - 1];
                        if (p.hasFood)
                            score += 10 * scoreMultiplier;


                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.LEFT;
                        table[coordY][coordX - 1] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);


                    } else if (table[coordY][coordX - 1] instanceof Ghost) {
                        handleMovingIntoGhost(coordX,coordY,coordX-1,coordY);
                    } else if (table[coordY][coordX - 1] instanceof Powerup){
                        Powerup p = (Powerup) table[coordY][coordX - 1];
                        switch (p.type){
                            case SPEEDUP:
                                currentPowerUp = Powerup.Types.SPEEDUP;
                                break;
                            case SLOWDOWN:
                                currentPowerUp = Powerup.Types.SLOWDOWN;
                                break;
                            case HALFPOINTS:
                                currentPowerUp = Powerup.Types.HALFPOINTS;
                                break;
                            case DOUBLEPOINTS:
                                currentPowerUp = Powerup.Types.DOUBLEPOINTS;
                                break;
                            case OVERDRIVE:
                                currentPowerUp = Powerup.Types.OVERDRIVE;
                                break;
                        }
                        powerUpTimeLeft=5.f;
                        score += 20 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.UP;
                        table[coordY][coordX-1] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }
                }else{
                    if(table[coordY][N-1] instanceof Passage){
                        Passage p = (Passage) table[coordY][N-1];
                        if (p.hasFood)
                            score += 10 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.LEFT;
                        table[coordY][N-1] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }else if(table[coordY][N-1] instanceof Ghost){
                        handleMovingIntoGhost(coordX,coordY,N-1,coordY);
                    }else if(table[coordY][N-1] instanceof Powerup){
                        Powerup p = (Powerup) table[coordY][N-1];
                        switch (p.type){
                            case SPEEDUP:
                                currentPowerUp = Powerup.Types.SPEEDUP;
                                break;
                            case SLOWDOWN:
                                currentPowerUp = Powerup.Types.SLOWDOWN;
                                break;
                            case HALFPOINTS:
                                currentPowerUp = Powerup.Types.HALFPOINTS;
                                break;
                            case DOUBLEPOINTS:
                                currentPowerUp = Powerup.Types.DOUBLEPOINTS;
                                break;
                            case OVERDRIVE:
                                currentPowerUp = Powerup.Types.OVERDRIVE;
                                break;
                        }
                        powerUpTimeLeft=5.f;
                        score += 20 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.UP;
                        table[coordY][N-1] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }

                }

                break;
            case RIGHT:
                if(coordX + 1 < N){
                    if(table[coordY][coordX+1] instanceof Passage){
                        Passage p = (Passage)table[coordY][coordX+1];
                        if(p.hasFood)
                            score += 10*scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.RIGHT;
                        table[coordY][coordX+1] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);


                    } else if (table[coordY][coordX+1] instanceof Ghost) {
                        handleMovingIntoGhost(coordX,coordY,coordX+1,coordY);;
                    } else if (table[coordY][coordX+1] instanceof Powerup){
                        Powerup p = (Powerup) table[coordY][coordX+1];
                        switch (p.type){
                            case SPEEDUP:
                                currentPowerUp = Powerup.Types.SPEEDUP;
                                break;
                            case SLOWDOWN:
                                currentPowerUp = Powerup.Types.SLOWDOWN;
                                break;
                            case HALFPOINTS:
                                currentPowerUp = Powerup.Types.HALFPOINTS;
                                break;
                            case DOUBLEPOINTS:
                                currentPowerUp = Powerup.Types.DOUBLEPOINTS;
                                break;
                            case OVERDRIVE:
                                currentPowerUp = Powerup.Types.OVERDRIVE;
                                break;
                        }
                        powerUpTimeLeft=5.f;
                        score += 20 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.UP;
                        table[coordY][coordX+1] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }
                }else{
                    if(table[coordY][0] instanceof Passage){
                        Passage p = (Passage) table[coordY][0];
                        if (p.hasFood)
                            score += 10 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.RIGHT;
                        table[coordY][0] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }else if(table[coordY][0] instanceof Ghost){
                        handleMovingIntoGhost(coordX,coordY,0,coordY-1);
                    }else if(table[coordY][0] instanceof Powerup){
                        Powerup p = (Powerup) table[coordY][0];
                        switch (p.type){
                            case SPEEDUP:
                                currentPowerUp = Powerup.Types.SPEEDUP;
                                break;
                            case SLOWDOWN:
                                currentPowerUp = Powerup.Types.SLOWDOWN;
                                break;
                            case HALFPOINTS:
                                currentPowerUp = Powerup.Types.HALFPOINTS;
                                break;
                            case DOUBLEPOINTS:
                                currentPowerUp = Powerup.Types.DOUBLEPOINTS;
                                break;
                            case OVERDRIVE:
                                currentPowerUp = Powerup.Types.OVERDRIVE;
                                break;
                        }
                        powerUpTimeLeft=5.f;
                        score += 20 * scoreMultiplier;

                        Player player = (Player)table[coordY][coordX];
                        player.open = !player.open;
                        player.direction = Directions.UP;
                        table[coordY][0] = table[coordY][coordX];
                        table[coordY][coordX] = new Passage(false);
                    }
                }
                break;
        }


        alreadyMovedThisFrame = true;
    }
    public class upArrowAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("up");
            movePacman(Directions.UP);
        }
    }
    public class downArrowAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("down");
            movePacman(Directions.DOWN);
        }
    }
    public class rightArrowAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("right");
            movePacman(Directions.RIGHT);
        }
    }
    public class leftArrowAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("left");
            movePacman(Directions.LEFT);
        }
    }

    public class escapeAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            isRunning = false;
        }
    }
}
