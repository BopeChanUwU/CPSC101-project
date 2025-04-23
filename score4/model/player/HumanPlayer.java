package score4.model.player;

import score4.model.board.Colour;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a HumanPlayer class that represents a human player in the game.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class HumanPlayer implements Player{

    private Colour beadColour;
    
    /**
     * HumanPlayer constructor
     * @param n int representing the player number
     * @throws IllegalArgumentException if n is not 1 or 2
     */
    public HumanPlayer(int n) {

        if(n<0 || n>2){

            throw new IllegalArgumentException("this is a 2 player game dumb dumb not a " + n + " player game");
        } else if( n == 1) {

            beadColour = Colour.White;
        } else {

            beadColour = Colour.Black;
        }
        
    }

    /**
     * gets the bead colour
     * @return Colour beadColour
     */
    public Colour getColour(){

        return beadColour;
    }

    /**
     * updates the model with the players selected move
     */
    @Override
    public void move(int x) {

        //this should update model stuff
    }

    /**
     * gets a Move recommended by the AI
     */
    @Override
    public int getMove() {
        
        return 1;
    }
}
