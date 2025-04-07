package score4.viewtwo.controllertwo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import score4.controller.GameboyController;
import score4.model.board.Board;
import score4.model.board.Peg;
import score4.viewtwo.gameboy.GameboyPanel;
import score4.viewtwo.gameboy.gamepanel.GamePanel;
import score4.viewtwo.gameboy.gamepanel.gamepanelcomponents.BlackBeadComponent;
import score4.viewtwo.gameboy.gamepanel.gamepanelcomponents.WhiteBeadComponent;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Controller class that handles the game logic
 * and communication between the model and the view. It listens for
 * user input from the GameboyPanel and updates the game state
 *
 * @author Tristen Sandhu

 * @version 1
 */
//Controller exposes data and commands needed by the view (acts as bridge from V -> M)///////////////////////////////////////////
public class ControllerTwo implements ActionListener, GameboyController {

    private final Board gameBoard;

    private final GameboyPanel gameBoyPanel;

    private boolean player1;

    /**
     * a one parameter constructor that makes a controller for 
     * a given GamePanel
     * @param gbp GamePanel 
     */
    public ControllerTwo(GameboyPanel gbp) {

        gameBoyPanel = gbp;
        gameBoard = new Board();
        player1 = true;
    }

    /**
     * gets the game board
     * @return Board the game board
     */
    @Override
    public Board getGameBoard() {

        return gameBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
    }
}
