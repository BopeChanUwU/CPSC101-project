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
public class Peg {

    private final Bead[] pegHeight = new Bead[4];

    //private  int pegX;
    //private  int pegY;
    private int pegZ = 0;

    /**
     * Peg constructor
     * <p> This constructor initializes the Peg object with a given x and y position.
     * <p> The Peg object is used to represent a peg on the game board.
     * <p> The Peg class is used to keep track of the state of the game board
     * and the position of the Beads on the Peg.
     */
    public Peg() {

        //pegX = x;
        //pegY = y;
    }

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
}
