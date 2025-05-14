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

    private Colour beadColour;
    private Position3D position3D;
    private static ArrayList<Bead> theBeads = new ArrayList<>();

    /**
     * Constructor for the Bead class
     * @param colour Colour of the bead
     * @param position3D Position3D of the bead
     * @throws IllegalArgumentException if colour is null or position3D is null
     */
    public Bead(Colour colour) {

        if(beadColour == null) {

            throw new IllegalArgumentException("colour cannot be null");
        }
        /* if(position3D == null) {

            throw new IllegalArgumentException("position3D cannot be null");
        } */
        beadColour = colour;
        //position3D = new Position3D(0, 0, 0);
    }

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
        //theBeads.add(this);
    }

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

        return this.position3D;
    }

    /**
     * Sets the position of the bead in 3D space
     * @param row int row
     * @param col int column
     * @param height int height
     * @throws IllegalArgumentException if row, col, or height are out of bounds
     */
    public void setPosition3D(int row, int col, int height) {

        if(row < 0 || row > 3) {

            throw new IllegalArgumentException("row is out of bounds");
        }
        if(col < 0 || col > 3) {

            throw new IllegalArgumentException("col is out of bounds");
        }
        if(height < 0 || height > 3) {

            throw new IllegalArgumentException("height is out of bounds");
        }
        position3D = new Position3D(row, col, height);
    }

    /**
     * this method adds newly created beads to the beads array list
     */
    public void registerBead() {

        theBeads.add(this);
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
     * @throws IllegalArgumentException if beadColour is null
     */
    public Colour getColour() {

        if(beadColour == null) {

            throw new IllegalArgumentException("beadColour cannot be null");
        }

        return beadColour;
    }

    /**
     * sets the bead colour
     * @param colour Colour of the bead
     * @throws IllegalArgumentException if colour is null
     */
    public void setColour(Colour colour){

        if(colour == null) {

            throw new IllegalArgumentException("colour cannot be null");
        }
        beadColour = colour;
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
