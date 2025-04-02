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
public class Peg {


    private final Bead[] pegHeight = new Bead[4];

    private int currentHeight = 0;


    public Peg() {

        for (int i = 0; i < pegHeight.length; i++) {

            pegHeight[i] = new Bead(Colour.Empty);
        }
    }

    /**
     * 
     * @param hieght
     * @return
     */
    public Bead getBead(int height) {

        return pegHeight[height];
    }

    public int getPegHeight() {

        return currentHeight;
    }
    
    /**
     * 
     * @param player1
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
