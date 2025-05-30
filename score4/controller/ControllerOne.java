package score4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import score4.model.game_state.GameState;
import score4.view.gameboy.GameboyPanel;

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
public class ControllerOne implements ActionListener, GameboyController {

    private final GameState gameState;

    private final GameboyPanel gameBoyPanel;

    /**
     * a one parameter constructor that makes a controller for 
     * a given GamePanel
     * @param gbp GamePanel 
     */
    public ControllerOne(GameboyPanel gbp, GameState gs) {

        gameBoyPanel = gbp;
        gameState = gs;
    }

    /**
     * gets the game board
     * @return Board the game board
     */
    @Override
    public GameState getGameState() {

        return gameState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO; rewrite this once all model changes are done
    }

    
}