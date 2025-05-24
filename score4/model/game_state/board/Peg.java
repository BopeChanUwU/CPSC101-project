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
    private final int maxHeight;
    private int beadCount = 0;
    private boolean full = false;

    /**
     * this constructor takes in a desired height and creates a peg
     * with the specified height and bead count set to 0
     * @param height int the specified height of the peg as an integer
     */
    public Peg(int height) {

        maxHeight = height;
        pegHeight = new Bead[maxHeight];
    }

    /**
     * default no argument constructor where the pegHeight is set to 4
     * and beadcount is set to 0
     */
    public Peg(){

        maxHeight = 4;
        pegHeight = new Bead[maxHeight];
    }

    /**
     * gets the bead at a given height
     * @param height int height
     * @return Bead bead
     * @throws IllegalArgumentException if height is out of bounds
     */
    public Bead getBead(int height) throws IllegalArgumentException {

        if (checkInBounds(height))
            return pegHeight[height];
        throw new IllegalArgumentException ("height(" + height + ") is out of bounds (between 0 - " + maxHeight + ")");
    }

    /**
     * gets the max height of the peg (the height it was create)
     * @return int peg height
     */
    public int getMaxHeight() { 

        return maxHeight;
    }

    /**
     * gets the current number of beads on the peg
     * @return int the currnet number of beads on the peg
     */
    public int getBeadCount() {

        return beadCount;
    }

    /**
     * this method sets a bead at the lowest legal point on the peg
     * @param row int the row the peg is located at on the board
     * @param col int the column the peg is located at on the board
     * @param colour Colour of the bead you would like to set
     */
    public void setBead(int row,int col, Colour colour) {

        if (beadCount < maxHeight) {
            pegHeight[beadCount] = Bead.createBead(colour, new Position3D(row, col, beadCount));
            increaseBeadCount();
        }
    }

    /**
     * this method removes the highest bead attached to the peg
     * @throws IllegalArgumentException if peg is empty
     */
    public void removeBead() {

        if (beadCount > 0) {
            pegHeight[beadCount-1] = null;
            Bead.removeBead();
            decreaseBeadCount();
        } else {
            throw new IllegalArgumentException("Peg is empty!");
        }
    }

    /**
     * increases the bead count of the peg by 1 if current count
     * is less than height else it sets the bead to full
     */
    private void increaseBeadCount() {

        if(beadCount<maxHeight) {
            beadCount++;
        } else if (!full) {
            full = true;
        }
    }

    /**
     * decreases the bead count of the peg by 1 and sets full to false
     * if count is greater than 0 if beadcount is 0 or less it does nothing
     */
    private void decreaseBeadCount() {

        if (beadCount > 0) {
            beadCount--;
        }
        full = false;
    }

    /**
     * Checks to see if a specified height is inbounds.
     * @param possibleHeight int the input height to check
     * @return boolean true if inbounds false if out of bounds
     */
    public boolean checkInBounds(int possibleHeight) {

        return (possibleHeight>=0) && (possibleHeight<maxHeight);
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
