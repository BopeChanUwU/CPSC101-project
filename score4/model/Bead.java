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
public class Bead {

    Colour beadColour;

    /**
     * 
     * @param colour
     */
    public Bead(Colour colour) {

        beadColour = colour;
    }

    /**
     * 
     * @return
     */
    public Colour getColour() {

        return beadColour;
    }

    /**
     * 
     * @param colour
     */
    public void setColour(Colour colour){

        beadColour = colour;
    }
}
