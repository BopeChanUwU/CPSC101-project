package score4.model;

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

            throw new IllegalArgumentException("this is a 2 player dumby not a " + n + " player game");
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
     * 
     */
    @Override
    public void move() {

        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    /**
     * gets a Move recommended by the AI
     */
    @Override
    public void getMove() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getMove'");
    }
}
