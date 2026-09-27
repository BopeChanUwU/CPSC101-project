package score4.viewtwo;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import score4.viewtwo.gameboy.MenuPanel;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a GameFrame class this is the main frame for the game
 * it contains the game screen and the title bar and the bottom bar
 * and the left and right side panels
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class GameFrame extends JFrame{

    private final MenuPanel menu = new MenuPanel();

    /**
     * constructs GameFrame
     */
    public GameFrame(){

        super();
        this.initialize();
    }

    /**
     * static method to call the constructor
     */
    public static void go(){

        new GameFrame();
    }

    /**
     * sets the contents of the GameFrame
     */
    public final void initialize(){

        setSize(500,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(menu, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
}
