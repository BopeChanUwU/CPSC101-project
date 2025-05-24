package score4.model.game_state.board;

import score4.model.player.Bead;
import score4.model.player.Colour;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Peg class that represents a peg on the game board
 * and contains an array of Beads. The Peg class provides methods to
 * get and set the Beads, as well as to manage the height of the Peg.
 * The Peg class is used to keep track of the state of the game board
 * and the position of the Beads on the Peg.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class Peg implements Cloneable {

    private final Bead[] pegHeight;
    private final int size;
    private int beadCount = 0;
    private boolean full = false;

    /**
     * this constructor takes in a desired height and creates a peg
     * with the specified height and bead count set to 0
     * @param height int the specified height of the peg as an integer
     */
    public Peg(int height){

        size = height;
        pegHeight = new Bead[size];
    }

    /**
     * default no argument constructor where the pegHeight is set to 4
     * and beadcount is set to 0
     */
    public Peg(){

        size = 4;
        pegHeight = new Bead[size];
    }

    /**
     * gets the bead at a given height
     * @param height int height
     * @return Bead bead
     * @throws IllegalArgumentException if height is out of bounds
     */
    public Bead getBead(int height) throws IllegalArgumentException{

        if (checkInBounds(height))
            return pegHeight[height];
        throw new IllegalArgumentException ("height(" + height + ") is out of bounds (between 0 - " + size + ")");
    }

    /**
     * gets the max height of the peg (the height it was create)
     * @return int peg height
     */
    public int getMaxHeight() { 

        return size;
    }

    /**
     * gets the current number of beads on the peg
     * @return int the currnet number of beads on the peg
     */
    public int getBeadCount() {

        return beadCount;
    }

    public void setBead(int row,int col, Colour colour) {

        if (beadCount <= size) {
            pegHeight[beadCount] = Bead.createBead(colour, new Position3D(row, col, beadCount));
            increaseBeadCount();
        }
    }

    public void removeBead(int row, int col) {

        if (beadCount > 0) {
            pegHeight[beadCount] = null;
            decreaseBeadCount();
        }
    }

    /**
     * increases the bead count of the peg by 1 if current count
     * is less than height else it sets the bead to full
     */
    public void increaseBeadCount() {

        if(beadCount<size) {
            beadCount++;
        } else if (!full) {
            full = true;
        }
    }

    public void decreaseBeadCount() {

        if (beadCount > 0) {
            beadCount--;
        }
    }

    /**
     * Checks to see if a specified height is inbounds.
     * @param possibleHeight int the input height to check
     * @return boolean true if inbounds false if out of bounds
     */
    public boolean checkInBounds(int possibleHeight) {

        return (possibleHeight>=0) && (possibleHeight<=size);
    }

    /**
     * clones the Peg object
     * @return Peg clone
     * @throws CloneNotSupportedException if the object cannot be cloned
     */
    @Override
    public Peg clone() throws CloneNotSupportedException {

        return (Peg) super.clone();
    }
}
