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
     * gets the players name
     * @return String the players name
     */
    String getName();

    /**
     * flips the players turn flag
     */
    void updateTurn();

    /**
     * gets the players turn status
     * @return boolean true if there turn
     * false if not there turn
     */
    boolean getTurn();

    /**
     * sets the number of wins
     */
    void increaseWins();

    /**
     * gets the number of wins
     * @return int numWins
     */
    int getWins();

    /**
     * gets the colour of the player
     * @return Colour black or white
     */
    Colour getColour();

    /**
     * sets the colour of the player
     * @param colour the colour you wish to set the player to
     */
    void setColour(Colour colour);
}
