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


    private Bead[] pegHieght = new Bead[4];

    private int currentBead = 0;
    private int currentHieght = 0;
    private int xLocation;
    private int yLocation;

    public Peg() {

        for (int i = 0; i < pegHieght.length; i++) {

            pegHieght[i] = new Bead(Colour.Empty);
        }
    }

    //stuff to send to paint
    private final int x2DConvert = 10;
    private final int y2DConvert = 10;
    private final int z2DConvert = 10;

    /////////////////3D MODE///////////////////////////////////////////////

    public Bead getBead(int hieght) {

        return pegHieght[hieght];
    }
    
    public void setBead(boolean player1) {

        if(currentHieght>=4) {

            if (player1 == true) {
            
                pegHieght[currentHieght].setColour(Colour.White);
                currentHieght++;
                currentBead++;
            } else {

                pegHieght[currentHieght].setColour(Colour.Black);
                currentHieght++;
                currentBead++;
            }
        }
    }

    /////////////////2D MODE///////////////////////////////////////////////////////////

    /*something to convert from xyz to just xy for painting purposes
    still need to figure out proper shifting for both x and y */
    public void convertTo2D(int x, int y, int z) {

        switch (y) {

            case 0 -> xLocation = x*x2DConvert + 8;

            case 1 -> xLocation = x*x2DConvert;

            case 2 -> xLocation = x*x2DConvert;

            case 3 -> xLocation = x*x2DConvert;
        }
        yLocation = y*y2DConvert + z*z2DConvert;
    }
}
