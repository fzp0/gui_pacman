import javax.swing.*;

public class Main {
    public static void main(String[] args) {



        System.out.println("Hello world!");

        gameLogic g = new gameLogic();

        //Thread GameLoop = new Thread(() -> g.Setup(0));

        SwingUtilities.invokeLater(
                () -> new gui.mainFrame()
        );


    }
}