package score4.controller;

import score4.model.game_state.GameState;

public interface GameboyController {

    /**
     * gets the game board
     * @return Board the game board
     */
    public GameState getGameState() ;


}
