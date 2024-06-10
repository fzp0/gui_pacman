import gui.menu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {



        System.out.println("Hello world!");

        gameLoop g = new gameLoop();

        //Thread GameLoop = new Thread(() -> g.Setup(0));

        SwingUtilities.invokeLater(
                () -> new menu()
        );


    }
}