package blocks;

import game.gameLogic;

import javax.swing.*;

public class Player extends Entity{
    public static ImageIcon[] anims; // up down left right
    public static ImageIcon closedanim;

    public gameLogic.Directions direction;
    public boolean open;
    public Player(){
        direction = gameLogic.Directions.RIGHT;
        open = true;
    }
    @Override
    public void display(){
        System.out.printf("P");
    }
}
