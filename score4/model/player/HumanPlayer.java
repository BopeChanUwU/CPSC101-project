package score4.model.player;

import score4.model.game_state.board.Position3D;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a HumanPlayer class that represents a human player in the game.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class HumanPlayer implements Player {

    private Colour beadColour;
    private int numWins = 0;
    private boolean isCurrentPlayer;
    
    /**
     * HumanPlayer constructor
     * @param n int representing the player number
     * @throws IllegalArgumentException if n is not 1 or 2
     */
    public HumanPlayer(int n) {

        if(n<=0 || n>2){

            throw new IllegalArgumentException("this is a 2 player game dumb dumb not a " + n + " player game");
        } else if( n == 1) {

            beadColour = Colour.White;
            isCurrentPlayer = true;
        } else {

            beadColour = Colour.Black;
            isCurrentPlayer = false;
        }
    }

    /**
     * sets the number of wins
     */
    @Override
    public void setnumWin(){

        numWins += 1;
    }

    /**
     * gets the number of wins
     * @return int numWins
     */
    @Override
    public int getnumWin(){

        return numWins;
    }

    /**
     * gets the current player
     * @return boolean isCurrentPlayer
     * true if player is current player
     * false if player is not current player
     */
    @Override
    public boolean isCurrentPlayer() {

        return isCurrentPlayer;
    }

    /**
     * gets the bead colour
     * @return Colour beadColour
     */
    @Override
    public Colour getColour(){

        return beadColour;
    }

    /**
     * gets a Move recommended by the AI
     */
    public Position3D getMove() {
       
        //TODO: implement getMove
        return null;
    }
}
