package score4.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import score4.view.gameboy.GameboyPanel;

public class GameFrame extends JFrame{

    private GameboyPanel gP;

    public GameFrame(){

        this.initialize(gP);
    }

    public static void go(){

        new GameFrame(); 
    }

    public void initialize(GameboyPanel gP){

        setSize(500,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gP = new GameboyPanel(),BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
}
