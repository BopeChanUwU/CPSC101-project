package score4.model;

import java.util.ArrayList;

public class Line {

    Board gameBoard;

    //stores all possible lines to search through
    ArrayList<int[]> allPossibleLines = new ArrayList<>();

    //have these just store indexes for 3d array (board)
    //easy lines
    int[] lineX = {0, 1, 2, 3}; // S -> N (change in x)
    int[] lineY = {0, 1, 2, 3}; // W -> E (change in y)
    int[] lineZ = {0, 1, 2, 3}; // ^ -> v (change in z) should be 16 lines

    //medium lines (not sure how to do this because i want two index changes) maybe jump by 2 for these
    int[] lineXY1 = {3,0, 2,1, 1,2, 0,3}; // NE -> SW (change in x and y)
    int[] lineXY2 = {0,3, 1,2, 2,1, 3,1}; // SE -> NW (change in x and y)
    int[] lineYZ1 = {3,3, 2,2, 1,1, 0,0}; // ^E -> vW (change in y and z)
    int[] lineYZ2 = {0,3, 1,2, 2,1, 3,0}; // ^W -> vE (change in y and z)
    int[] lineXZ1 = {3,3, 2,2, 1,1, 0,0}; // ^N -> vS (change in x and z)
    int[] lineXZ2 = {0,3, 1,2, 2,1, 3,0}; // ^S -> vN (change in x and z) should be 4 lines

    //hard lines (not sure how to do this because i want three index changes ) maybe jump by 3 for these
    int[] lineXYZ1 = {0,0,3, 1,1,2, 2,2,1, 3,3,0}; // ^SW -> vNE (change in all)
    int[] lineXYZ2 = {3,3,3, 2,2,2, 1,1,1, 0,0,0}; // ^NE -> vSW (change in all)
    int[] lineXYZ3 = {0,3,3, 1,2,2, 2,1,1, 3,0,0}; // ^SE -> vNW (change in all)
    int[] lineXYZ4 = {3,0,3, 2,1,2, 1,2,1, 0,3,0}; // ^NW -> vSE (change in all) should be 1 line

    //maybe make it a single param constructor later if we find a way to make it expand that seems tricky tho 
    //currently all i think this needs is to take in a game board so it has the same board as the rest of the game
    public Line(Board gameBoard) {

        this.gameBoard = gameBoard;
    }

    //this will check all possible lines to see if any return 4
    public Boolean checkForWin(Colour colour) {

        
        return false;
    }

    public int[][] checkLineX(Colour colour){

        int count;
        int[][] lineLength = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; /* each row represents lines in a given z 
        col represent lines in a given y */
        for (int i = 0; i < 10; i++) { //gets my z
            for (int j = 0; j < 4; j++) { // gets my y
                count = 0;
                for (int k = 0; k < 4; k++) { // used to check LineX

                    if(gameBoard.getPeg(lineX[k], j).getBead(i).getColour() == colour) {

                        count++;
                    } else {

                        lineLength[i][j] = 0;
                        break;
                    }
                    lineLength[i][j] = count;
                }
            }
        }
        return lineLength;
    }

    public int[][] checkLineY(Colour colour){

        int count;
        int[][] lineLength = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; /* each row represents lines in a given z 
        col represent lines in a given y */
        for (int i = 0; i < 10; i++) { //gets my z
            for (int j = 0; j < 4; j++) { // gets my x
                count = 0;
                for (int k = 0; k < 4; k++) { // used to check LineY

                    if(gameBoard.getPeg(j, lineY[k]).getBead(i).getColour() == colour) {

                        count++;
                    } else {

                        lineLength[i][j] = 0;
                        break;
                    }
                    lineLength[i][j] = count;
                }
            }
        }
        return lineLength;
    }

    public int[][] checkLineZ(Colour colour){

        int count;
        int[][] lineLength = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; /* each row represents lines in a given z 
        col represent lines in a given y */
        for (int i = 0; i < 10; i++) { //gets my x
            for (int j = 0; j < 4; j++) { // gets my y
                count = 0;
                for (int k = 0; k < 4; k++) { // used to check LineZ

                    if(gameBoard.getPeg(i, j).getBead(lineZ[k]).getColour() == colour) {

                        count++;
                    } else {

                        lineLength[i][j] = 0;
                        break;
                    }
                    lineLength[i][j] = count;
                }
            }
        }
        return lineLength;
    }
}
