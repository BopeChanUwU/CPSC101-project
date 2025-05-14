package score4.model.game_state.board;

import score4.model.player.Bead;

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

    private final Bead[] pegHeight = new Bead[4];
    private int pegZ = 0;

    /**
     * gets the bead at a given height
     * @param hieght int height
     * @return Bead bead
     * @throws IllegalArgumentException if height is out of bounds
     */
    public Bead getBead(int height) {

        if (height < 0 || height > 3) {

            throw new IllegalArgumentException("height (" + height + ") is out of bounds");
        }

        return pegHeight[height];
    }

    /**
     * gets the bead at a given height
     * @return Bead bead
     */
/*     public Bead getBead() {

        return bead;
    } */

    /**
     * gets the peg height
     * @return int peg height
     */
    public Bead[] getPegHeight() {

        return pegHeight;
    }

    public int getPegZ() {

        return pegZ;
    }

    public void increasePegZ() {

        pegZ++;
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
