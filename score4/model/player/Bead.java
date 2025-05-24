package score4.model.player;

import java.util.ArrayList;
import score4.model.game_state.board.Position3D;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Bead class that represents a bead in the game.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class Bead implements Cloneable {

    private final Colour beadColour;
    private final Position3D position3D;
    private static final ArrayList<Bead> theBeads = new ArrayList<>();

    /**
     * Constructor for the Bead class
     * @param colour Colour of the bead
     * @param position3D Position3D of the bead
     * @throws IllegalArgumentException if colour is null or position3D is null
     */
    public Bead(Colour colour, Position3D position3D) {

        if(colour == null) {

            throw new IllegalArgumentException("colour cannot be null");
        }
        if(position3D == null) {

            throw new IllegalArgumentException("position3D cannot be null");
        }
        beadColour = colour;
        this.position3D = position3D;
    }

    /**
     * creates a new bead with specified colour and position3d objects
     * it then adds this bead to the 
     * @param colour an enum either black or white
     * @param position3D the locatiton in 3d space you wish to create the 
     * bead at from 0-63
     * @return Bead a bead object with the specified colour and location 
     */
    public static Bead createBead(Colour colour, Position3D position3D) {
            
        Bead bead = new Bead(colour, position3D);
        theBeads.add(bead);
        return bead;
    }

    /**
     * Gets the position of the bead in 3D space
     * @return Position3D position3D
     */
    public Position3D getPosition3D() {

        return position3D;
    }

    /**
     * removes the last bead from theBeads arraylist
     */
    public static void removeBead(){

        if(theBeads != null)
            theBeads.removeLast();
    }

    /**
     * gets the positions arraylist
     * @return ArrayList<Position3D> thePositions
     */
    public static ArrayList<Bead> getTheBeads() {

        return theBeads;
    }

    /**
     * gets the bead colour
     * @return Colour beadColour
     */
    public Colour getColour() {

        return beadColour;
    }

    /**
     * checks to see if the coulours of the beads match
     * @param colour1 Colour the colour to compare to
     * @param colour2 Colour the colour you want to compare
     * @return Boolean true if the colours match false if they dont
     */
    public static boolean coloursMatch(Colour colour1, Colour colour2) {

        return colour1 == colour2;
    }

    /**
     * clones the Bead object
     * @return Bead clone
     * @throws CloneNotSupportedException if the object cannot be cloned
     */
    @Override
    public Bead clone() throws CloneNotSupportedException {

        return (Bead) super.clone();
    }
}
