package score4.model.player;

import score4.model.game_state.GameState;
import score4.model.game_state.board.Position3D;

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
    public Position3D move(int x, int y, GameState currentState); //updates the model with the players selected move
}
