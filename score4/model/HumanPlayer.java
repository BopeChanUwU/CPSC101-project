package score4.model;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Position3D class
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
 * @version 1
 */
public class HumanPlayer implements Player{

    private Colour beadColour;
    
    /**
     * 
     * @param n
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
     * 
     */
    @Override
    public void getMove() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getMove'");
    }
}
