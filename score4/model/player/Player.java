package score4.model.player;

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
     * gets the current player
     * @return boolean isCurrentPlayer
     */
    boolean isCurrentPlayer();

    /**
     * sets the number of wins
     */
    void setnumWin();

    /**
     * gets the number of wins
     * @return int numWins
     */
    int getnumWin();

    /**
     * gets the colour of the player
     * @return Colour black or white
     */
    Colour getColour();

}
