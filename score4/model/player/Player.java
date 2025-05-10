package score4.model.player;

import score4.model.game_state.GameState;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Player interface for the model
 *
 * @author Tristen Sandhu
 * @version 1
 */
public interface Player {

    /**
     * 
     */
    public void move(int x, int y, GameState currentState); //updates the model with the players selected move

    /**
     * gets a Move recommended by the AI
     */
    public int getMove(); //gets a move recommended by the AI
}
