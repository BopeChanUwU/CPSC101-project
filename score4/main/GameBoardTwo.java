package score4.main;

import static score4.viewtwo.GameFrame.go;
/**
 * This file is part of a Score4 game
 *
 * <p> Implements a GameBoard class that serves as the entry point for the game.
 * <p> This uses buttons for moves.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class GameBoardTwo {
    
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(() -> go());
    }
}