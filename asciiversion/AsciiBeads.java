package asciiversion;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Position3D class
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
 * @version 1
 */
public class AsciiBeads {

   private static final String[][][] beads = new String[4][4][4];

   private static final int[][] z = new int[4][4];

    /**
     * AsciiBeads constructor
     * initializes z to a 4x4 array and fills it with 0's
     * initializes beads to a 4x4x4 array and fills it with |'s (pipes)
     */
    AsciiBeads() {
    
       for (String[][] bead2 : beads) {
           for (String[] bead1 : bead2) {
                for (int i = 0; i < 4; i++) {

                   bead1[i] = "|";
                }
           }
       }
   }

   /**
    * takes in a users input as two sperate integers (x,y) then uses those as indexs to find "hight"
    * on the z axis the hight stored in z[i][j] is within range it then takes those three values and uses 
    * them as indexs for beads[i][j][k] where it sets the value = "W" we then increment the value of z[i][j]
    * @param i integer representing x position on game board
    * @param j integer representing y position on game board
    */
    public void setWhite(int i,int j) {

        int k = z[i][j];
        if(k<=3) {

            beads[i][j][k] = "W";
            z[i][j]++;
        }
    }

   /**
    * takes in a users input as two sperate integers (x,y) then uses those as indexs to find "hight"
    * on the z axis the hight stored in z[i][j] is within range it then takes those three values and uses 
    * them as indexs for beads[i][j][k] where it sets the value = "B" we then increment the value of z[i][j]
    * @param i integer representing x position on game board
    * @param j integer representing y position on game board
    */
    public void setBlack(int i,int j) {

        int l = z[i][j];
        if(l<=3) {

            beads[i][j][l] = "B";
            z[i][j]++;
        }
   }

   /**
    * gets the string stored at the specified index [x][y][z]
    * @param x  integer index for x axis
    * @param y integer index for y axis
    * @param z integer index for z axis
    * @return String element in beads[][][]
    */
    public String getPegged(int x,int y,int z){

        return beads[x][y][z];
    }

   /**
    * returns the location of the next valid placement on the z axis
    * @param x integer location on x axis
    * @param y integer location on y axis
    * @return integer the next valid hight to play 
    */
    public int getZ(int x, int y) {

        return z[x][y];
    }

   /**
    * takes in the index of the last move played as well as who played it then uses these to see
    * if it was a winning move or not
    * @param x integer x position of last played move
    * @param y integer y position of last played move
    * @param z integer z positionof last played move
    * @param token String determines whether its black or whites move
    * @return boolean true if win condition found false if no win found
    */
    public boolean checkWinner(int x,int y,int z,String token) {

        if (x==0&&beads[x][y][z].equals(beads[x+1][y][z])&&beads[x][y][z].equals(beads[x+2][y][z])&&beads[x][y][z].equals(beads[x+3][y][z])&&beads[x][y][z].equals(token)) {
            return true;
        } else if (x==3&&beads[x][y][z].equals(beads[x-1][y][z])&&beads[x][y][z].equals(beads[x-2][y][z])&&beads[x][y][z].equals(beads[x-3][y][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==1&&beads[x][y][z].equals(beads[x+1][y][z])&&beads[x][y][z].equals(beads[x+2][y][z])&&beads[x][y][z].equals(beads[x-1][y][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==2&&beads[x][y][z].equals(beads[x+1][y][z])&&beads[x][y][z].equals(beads[x-2][y][z])&&beads[x][y][z].equals(beads[x-1][y][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (y==0&&beads[x][y][z].equals(beads[x][y+1][z])&&beads[x][y][z].equals(beads[x][y+2][z])&&beads[x][y][z].equals(beads[x][y+3][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (y==1&&beads[x][y][z].equals(beads[x][y+1][z])&&beads[x][y][z].equals(beads[x][y+2][z])&&beads[x][y][z].equals(beads[x][y-1][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (y==2&&beads[x][y][z].equals(beads[x][y+1][z])&&beads[x][y][z].equals(beads[x][y-1][z])&&beads[x][y][z].equals(beads[x][y-2][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (y==3&&beads[x][y][z].equals(beads[x][y-1][z])&&beads[x][y][z].equals(beads[x][y-2][z])&&beads[x][y][z].equals(beads[x][y-3][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (z==3&&beads[x][y][z].equals(beads[x][y][z-1])&&beads[x][y][z].equals(beads[x][y][z-2])&&beads[x][y][z].equals(beads[x][y][z-3])&&beads[x][y][z].equals(token)){
            return true;
        } else if (z==1&&beads[x][y][z].equals(beads[x][y][z+1])&&beads[x][y][z].equals(beads[x][y][z+2])&&beads[x][y][z].equals(beads[x][y][z-1])&&beads[x][y][z].equals(token)){
            return true;
        } else if (z==2&&beads[x][y][z].equals(beads[x][y][z+1])&&beads[x][y][z].equals(beads[x][y][z-1])&&beads[x][y][z].equals(beads[x][y][z-2])&&beads[x][y][z].equals(token)){
            return true;
        } else if (z==0&&beads[x][y][z].equals(beads[x][y][z+1])&&beads[x][y][z].equals(beads[x][y][z+2])&&beads[x][y][z].equals(beads[x][y][z+3])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==0&&y==0&&beads[x][y][z].equals(beads[x+1][y+1][z])&&beads[x][y][z].equals(beads[x+2][y+2][z])&&beads[x][y][z].equals(beads[x+3][y+3][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==2&&y==2&&beads[x][y][z].equals(beads[x+1][y+1][z])&&beads[x][y][z].equals(beads[x-2][y-2][z])&&beads[x][y][z].equals(beads[x-1][y-1][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==1&&y==1&&beads[x][y][z].equals(beads[x+1][y+1][z])&&beads[x][y][z].equals(beads[x+2][y+2][z])&&beads[x][y][z].equals(beads[x-1][y-1][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==3&&y==3&&beads[x][y][z].equals(beads[x-1][y-1][z])&&beads[x][y][z].equals(beads[x-2][y-2][z])&&beads[x][y][z].equals(beads[x-3][y-3][z])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==0&&z==0&&beads[x][y][z].equals(beads[x+1][y][z+1])&&beads[x][y][z].equals(beads[x+2][y][z+2])&&beads[x][y][z].equals(beads[x+3][y][z+3])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==1&&z==1&&beads[x][y][z].equals(beads[x+1][y][z+1])&&beads[x][y][z].equals(beads[x-1][y][z-1])&&beads[x][y][z].equals(beads[x+2][y][z+2])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==2&&z==2&&beads[x][y][z].equals(beads[x+1][y][z+1])&&beads[x][y][z].equals(beads[x-1][y][z-1])&&beads[x][y][z].equals(beads[x-2][y][z-2])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==3&&z==3&&beads[x][y][z].equals(beads[x-1][y][z-1])&&beads[x][y][z].equals(beads[x-2][y][z-2])&&beads[x][y][z].equals(beads[x-3][y][z-3])&&beads[x][y][z].equals(token)){
            return true;
        } else if (y==0&&z==0&&beads[x][y][z].equals(beads[x][y+1][z+1])&&beads[x][y][z].equals(beads[x][y+2][z+2])&&beads[x][y][z].equals(beads[x][y+3][z+3])&&beads[x][y][z].equals(token)){
            return true;
        } else if (y==1&&z==1&&beads[x][y][z].equals(beads[x][y+1][z+1])&&beads[x][y][z].equals(beads[x][y+2][z+2])&&beads[x][y][z].equals(beads[x][y-1][z-1])&&beads[x][y][z].equals(token)){
            return true;
        } else if (y==2&&z==2&&beads[x][y][z].equals(beads[x][y-1][z-1])&&beads[x][y][z].equals(beads[x][y-2][z-2])&&beads[x][y][z].equals(beads[x][y+1][z+1])&&beads[x][y][z].equals(token)){
            return true;
        } else if (y==3&&z==3&&beads[x][y][z].equals(beads[x][y-1][z-1])&&beads[x][y][z].equals(beads[x][y-2][z-2])&&beads[x][y][z].equals(beads[x][y-3][z-3])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==2&&y==2&&z==2&&beads[x][y][z].equals(beads[x+1][y+1][z+1])&&beads[x][y][z].equals(beads[x-2][y-2][z-2])&&beads[x][y][z].equals(beads[x-1][y-1][z-1])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==0&&y==0&&z==0&&beads[x][y][z].equals(beads[x+1][y+1][z+1])&&beads[x][y][z].equals(beads[x+2][y+2][z+2])&&beads[x][y][z].equals(beads[x+3][y+3][z+3])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==1&&y==1&&z==1&&beads[x][y][z].equals(beads[x+1][y+1][z+1])&&beads[x][y][z].equals(beads[x+2][y+2][z])&&beads[x][y][z].equals(beads[x-1][y-1][z-1])&&beads[x][y][z].equals(token)){
            return true;
        } else if (x==3&&y==3&&z==3&&beads[x][y][z].equals(beads[x-1][y-1][z-1])&&beads[x][y][z].equals(beads[x-2][y-2][z-2])&&beads[x][y][z].equals(beads[x-3][y-3][z-3])&&beads[x][y][z].equals(token)){
            return true;
        } else{
            return false;
        }
    } 

    /**
     * Prints out current game board
     */
    public void showBoard() {

        System.out.println("      _____ ____ _____ _____ ____    ___    __ ");
        System.out.println("     |   __|    |     |  _  |  __|  | | |  |  |");
        System.out.println("     |__   |  --|  |  |    -|  __|  |_  |  |__|");
        System.out.println("     |_____|____|_____|__|__|____|    |_|  |__|");
        System.out.println("                 "+this.getPegged(0, 3, 3)+"         "+this.getPegged(1, 3, 3)+"         "+this.getPegged(2, 3, 3)+"         "+this.getPegged(3, 3, 3));
        System.out.println("                 "+this.getPegged(0, 3, 2)+"         "+this.getPegged(1, 3, 2)+"         "+this.getPegged(2, 3, 2)+"         "+this.getPegged(3, 3, 2));
        System.out.println("                 "+this.getPegged(0, 3, 1)+"         "+this.getPegged(1, 3, 1)+"         "+this.getPegged(2, 3, 1)+"         "+this.getPegged(3, 3, 1));
        System.out.println("                 "+this.getPegged(0, 3, 0)+"---------"+this.getPegged(1, 3, 0)+"---------"+this.getPegged(2, 3, 0)+"---------"+this.getPegged(3, 3, 0)+"(D4)");
        System.out.println("             "+this.getPegged(0, 2, 3)+"  /      "+this.getPegged(1, 2, 3)+"         "+this.getPegged(2, 2, 3)+"         "+this.getPegged(3, 2, 3)+"  /");
        System.out.println("             "+this.getPegged(0, 2, 2)+" /       "+this.getPegged(1, 2, 2)+"         "+this.getPegged(2, 2, 2)+"         "+this.getPegged(3, 2, 2)+" /");
        System.out.println("             "+this.getPegged(0, 2, 1)+"/        "+this.getPegged(1, 2, 1)+"         "+this.getPegged(2, 2, 1)+"         "+this.getPegged(3, 2, 1)+"/");
        System.out.println("             "+this.getPegged(0, 2, 0)+"---------"+this.getPegged(1, 2, 0)+"---------"+this.getPegged(2, 2, 0)+"---------"+this.getPegged(3, 2, 0));
        System.out.println("         "+this.getPegged(0, 1, 3)+"  /      "+this.getPegged(1, 1, 3)+"         "+this.getPegged(2, 1, 3)+"         "+this.getPegged(3, 1, 3)+"  /");
        System.out.println("         "+this.getPegged(0, 1, 2)+" /       "+this.getPegged(1, 1, 2)+"         "+this.getPegged(2, 1, 2)+"         "+this.getPegged(3, 1, 2)+" /");
        System.out.println("         "+this.getPegged(0, 1, 1)+"/        "+this.getPegged(1, 1, 1)+"         "+this.getPegged(2, 1, 1)+"         "+this.getPegged(3, 1, 1)+"/");
        System.out.println("         "+this.getPegged(0, 1, 0)+"---------"+this.getPegged(1, 1, 0)+"---------"+this.getPegged(2, 1, 0)+"---------"+this.getPegged(3, 1, 0));
        System.out.println("     "+this.getPegged(0, 0, 3)+"  /      "+this.getPegged(1, 0, 3)+"         "+this.getPegged(2, 0, 3)+"         "+this.getPegged(3, 0, 3)+"  /");
        System.out.println("     "+this.getPegged(0, 0, 2)+" /       "+this.getPegged(1, 0, 2)+"         "+this.getPegged(2, 0, 2)+"         "+this.getPegged(3, 0, 2)+" /");
        System.out.println("     "+this.getPegged(0, 0, 1)+"/        "+this.getPegged(1, 0, 1)+"         "+this.getPegged(2, 0, 1)+"         "+this.getPegged(3, 0, 1)+"/");
        System.out.println(" (A1)"+this.getPegged(0, 0, 0)+"---------"+this.getPegged(1, 0, 0)+"---------"+this.getPegged(2, 0, 0)+"---------"+this.getPegged(3, 0, 0));
    }
}
