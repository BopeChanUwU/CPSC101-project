package score4.model;

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
    public void move();

    /**
     * gets a Move recommended by the AI
     */
    public void getMove(); //gets a move recommended by the AI
}
