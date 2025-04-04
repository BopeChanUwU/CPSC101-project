package score4.main;

import static score4.view.GameFrame.go;
/**
 * This file is part of a Score4 game
 *
 * <p> Implements a GameBoard class that serves as the entry point for the game.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class GameBoard{
    
    public static void main(String[] args) throws Exception {
        
        javax.swing.SwingUtilities.invokeLater(() -> go());
    }
}