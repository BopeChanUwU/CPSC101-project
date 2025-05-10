package score4.main;

import static score4.view.GameFrame.go;
/**
 * This file is part of a Score4 game
 *
 * <p> Implements a GameBoard class that serves as the entry point for the game.
 * <p> This uses chess notation for moves.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class GameBoardOne {
    
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(() -> go());
    }
}