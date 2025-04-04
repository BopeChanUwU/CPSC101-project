package score4.model.board;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Bead class that represents a bead in the game.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class Bead {

    Colour beadColour;

    /**
     * Constructor for the Bead class
     * @param colour Colour of the bead
     * @throws IllegalArgumentException if colour is null
     */
    public Bead(Colour colour) {

        beadColour = colour;
    }

    /**
     * gets the bead colour
     * @return Colour beadColour
     * @throws IllegalArgumentException if beadColour is null
     */
    public Colour getColour() {

        return beadColour;
    }

    /**
     * sets the bead colour
     * @param colour Colour of the bead
     * @throws IllegalArgumentException if colour is null
     */
    public void setColour(Colour colour){

        beadColour = colour;
    }
}
