package score4.model.board;

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
    private final Bead bead = new Bead();

    private final int pegX;
    private final int pegY;
    private int pegZ = 0;

    public Peg() {
        
        pegX = 0;
        pegY = 0;
    }
    /**
     * Peg constructor
     * <p> This constructor initializes the Peg object with a given x and y position.
     * <p> The Peg object is used to represent a peg on the game board.
     * <p> The Peg class is used to keep track of the state of the game board
     * and the position of the Beads on the Peg.
     */
    public Peg(int x, int y) {

        pegX = x;
        pegY = y;
    }

    /**
     * gets the bead at a given height
     * @param hieght int height
     * @return Bead bead
     * @throws IllegalArgumentException if height is out of bounds
     */
    public Bead getBead(int height) {

        if (height < 0 || height > 3) {

            throw new IllegalArgumentException("height is out of bounds");
        }

        return pegHeight[height];
    }

    /**
     * gets the bead at a given height
     * @return Bead bead
     */
    public Bead getBead() {

        return bead;
    }

    /**
     * gets the peg height
     * @return int peg height
     */
    public int getPegHeight() {

        return pegZ;
    }
    
    /**
     * sets the bead at a given height
     * @param player1 boolean player1
     * @throws IllegalArgumentException if height is out of bounds
     */
    public void setBead(boolean player1) {

        if(pegZ < 4) {

            if (player1 == true) {
            
                pegHeight[pegZ] = new Bead(Colour.White, new Position3D(pegX, pegY, pegZ));
            } else {

                pegHeight[pegZ] = new Bead(Colour.Black, new Position3D(pegX, pegY, pegZ));
            }
            pegZ++;
        } else {

            throw new IllegalArgumentException("height is out of bounds");
        }
    }
}
