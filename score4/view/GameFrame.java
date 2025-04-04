package score4.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import score4.view.gameboy.GameboyPanel;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Position3D class
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
 * @version 1
 */
public class GameFrame extends JFrame{

    private final GameboyPanel gP = new GameboyPanel();

    /**
     * constructs GameFrame 
     */
    public GameFrame(){

        super();
        this.initialize(gP);
    }

    /**
     * static method to call the constructor
     */
    public static void go(){

        new GameFrame(); 
    }

    /**
     * sets the contents of the GameFrame
     * @param gP GameboyPanel holds the game screen
     */
    public final void initialize(GameboyPanel gP){

        setSize(500,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gP ,BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
}
