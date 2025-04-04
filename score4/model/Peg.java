package score4.model;

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

    private int currentHeight = 0;

    /**
     * Peg constructor
     * <p> This constructor initializes the peg with an array of Bead objects.
     * <p> Each Bead object is initialized with a default constructor.
     * <p> The peg height is set to 0.
     */
    public Peg() {

        for (int i = 0; i < pegHeight.length; i++) {

            pegHeight[i] = new Bead(Colour.Empty);
        }
    }

    /**
     * gets the bead at a given height
     * @param hieght int height
     * @return Bead bead
     * @throws IllegalArgumentException if height is out of bounds
     */
    public Bead getBead(int height) {

        return pegHeight[height];
    }

    /**
     * gets the peg height
     * @return int peg height
     */
    public int getPegHeight() {

        return currentHeight;
    }
    
    /**
     * sets the bead at a given height
     * @param player1 boolean player1
     * @throws IllegalArgumentException if height is out of bounds
     */
    public void setBead(boolean player1) {

        if(currentHeight>=4) {

            if (player1 == true) {
            
                pegHeight[currentHeight].setColour(Colour.White);
                currentHeight++;
            } else {

                pegHeight[currentHeight].setColour(Colour.Black);
                currentHeight++;
            }
        }
    }
}
