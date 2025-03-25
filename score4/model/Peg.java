package score4.model;

/* import java.awt.geom.Point2D; */

public class Peg {

    //stuff for pegs
    //private int[][] pegLocation;
    private Bead[] pegHieght = new Bead[4];    //this acts like "z"
    private Bead[] blackBeads = new Bead[4];
    private Bead[] whiteBeads = new Bead[4];
    private int currentBead = 0;
    //private int x;
    //private int y;
    private int currentHieght = 0;

    public Peg() {

        for (int i = 0; i < blackBeads.length; i++) {

            blackBeads[i] = new Bead(Colour.Black);
            whiteBeads[i] = new Bead(Colour.White);
        }
    }
    
    //stuff for beads
    /* private Point2D[][][] beadLocation;    //this is for win checker 0 means empty 1 means white 2 means black
    private int z; */

    //stuff to send to paint
    private int xLocation;
    private final int x2DConvert = 10;
    private int yLocation;
    private final int y2DConvert = 10;
    private final int z2DConvert = 10;

    /////////////////3D MODE///////////////////////////////////////////////

    //this is used as x position 
    /* public int getX() {

        return x;
    } */

    //this is used as y position 
    /* public int getY() {

        return y;
    } */

    //this is used as z position 
    /* public int getZ() {

        return z;
    } */

    //this sets my z position
    /* public void setZ() {

        z = pegHieght[pegLocation[this.x][this.y]];
        pegHieght[pegLocation[this.x][this.y]]++;
    } */

    public Bead getBead(int hieght) {

        return pegHieght[hieght];
    }

    //translates from text to ints
/*     public void realMove(String input) {

        switch(input){
            case "A1":
                x = 0;
                y = 0;
                break;
            case "A2":
                x = 0;
                y = 1;
                break;
            case "A3":
                x = 0;
                y = 2;
                break;
            case "A4":
                x = 0;
                y = 3;
                break;
            case "B1":
                x = 1;
                y = 0;
                break;
            case "B2":
                x = 1;
                y = 1;
                break;
            case "B3":
                x = 1;
                y = 2;
                break;
            case "B4":
                x = 1;
                y = 3;
                break;
            case "C1":
                x = 2;
                y = 0;
                break;
            case "C2":
                x = 2;
                y = 1;
                break;
            case "C3":
                x = 2;
                y = 2;
                break;
            case "C4":
                x = 2;
                y = 3;
                break;
            case "D1":
                x = 3;
                y = 0;
                break;
            case "D2":
                x = 3;
                y = 1;
                break;
            case "D3":
                x = 3;
                y = 2;
                break;
            case "D4":
                x = 3;
                y = 3;
                break;
            default:
                x = -1;
                y = -1;
                break;
        }
    }  
 */
    
    public void setBead(boolean player1) {

        if(currentHieght>=4) {
            if (player1 == true) {
            
                pegHieght[currentHieght] = whiteBeads[currentBead];
                currentHieght++;
                currentBead++;
            } else {

                pegHieght[currentHieght] = blackBeads[currentBead];
                currentHieght++;
                currentBead++;
            }
        }
    }

    /////////////////2D MODE///////////////////////////////////////////////////////////
    
    //this gets xLocation
    /* public int getXLocation() {

        return xLocation;
    } */

    //this gets yLocation
    /* public int getYLocation() {

        return yLocation;
    } */

    /* public void setXLocation(int x) {

        xLocation = x;
    } */

    /* public void setYLocation(int y) {
        
        yLocation = y;
    } */

    /*something to convert from xyz to just xy for painting purposes
    still need to figure out proper shifting for both x and y */
    public void convertTo2D(int x, int y, int z) {

        switch (y) {
            case 0:

                xLocation = x*x2DConvert + 8;
                break;
            case 1:

                xLocation = x*x2DConvert;
                break;
            case 2:

                xLocation = x*x2DConvert;
                break;
            case 3:

                xLocation = x*x2DConvert;
            break;
        }

        yLocation = y*y2DConvert + z*z2DConvert;
    }
}
