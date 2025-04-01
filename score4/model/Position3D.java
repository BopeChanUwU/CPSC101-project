package score4.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position3D {

    private final int row; 
    private final int col;
    private final int height;

    private static final Position3D[] thePositions = new Position3D[64];

    /**
     * Creates a Position3D with the given row, column, and height. 
     * Note that r, c, h are all between 0 and 3 inclusuve.
     * @param r int row
     * @param c int column
     * @param h int height
     * @exception IllegalArgumentException 
     */
    public Position3D (int r,int c,int h) {

        if(r<0 || r>3) {

            throw new IllegalArgumentException("Illegal row! " + r + " must be between 0 -> 3"); 
        } else if (c<0 || c>3) {
            
            throw new IllegalArgumentException("Illegal column! " + c + " must be between 0 -> 3");
        } else if (h<0 || h>3) {
            
            throw new IllegalArgumentException("Illegal height! " + h + " must be between 0 -> 3");
        } 
        row = r;
        col = c;
        height = h;
    }

    /**
     * of() should understand strings like "C4(1) where the first letter is A–D and 
     * designates the row, the first number is between 1 and 4 and designates the 
     * column, and the second number is also between 1 and 4 and designates the height. 
     * of() should be the inverse of toString().
     * @return A Position3D object representing the string
     * @exception IllegalArgumentException
     */
    public static Position3D of(String s) {
        
        Pattern pattern = Pattern.compile("[ABCD][1234][(][1234][)]");
        Matcher matcher = pattern.matcher(s);
        int matches = 0;
        while (matcher.find()) {

            matches++;
        }

        if(matches != 1) {

            throw new IllegalArgumentException("booo you done fucked up bucko " + s + " aint the correct formatting");
        }
        String row = s.substring(0, 1);

        int realRow;
        int col = Integer.parseInt(s.substring(1, 2));
        int height = Integer.parseInt(s.substring(3, 4));

        if(col<1 || col>4) {

            throw new IllegalArgumentException("Bad column " + col + " column must be between 1-4");  
        } 
        col--;

        if(height<1 || height>4) {

            throw new IllegalArgumentException("Bad Height " + height + " height must be between 1-4");
        } 
        height--;

        switch (row) {

            case "A" -> { realRow = 0;
            }

            case "B" -> { realRow = 1;
            }

            case "C" -> { realRow = 2;
            }

            case "D" -> { realRow = 3;
            }
            
            default -> { throw new IllegalArgumentException("Illegal row " 
            + row + " row must be between A -> D"); //this should be impossible
            }
        }
        return new Position3D(realRow, col, height);
    }

    /**
     * This method should return an array of all 64 possible positions.
     * @return Position3D[] of all possible positions
     */
    public static Position3D[] allPositions() {
        
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    thePositions[count] = new Position3D(i, j, k); 
                    count++;
                }
            }
        }
        return thePositions;
    }

    /**
     * Gets the row
     * @return int row (between 0-3)
     */
    public int getRow() {

        return row;
    }

    /**
     * Gets the column
     * @return int column (between 0-3)
     */
    public int getColumn() {

        return col;
    }

    /**
     * Gets the height 
     * @return int height (between 0-3)
     */
    public int getHeight() {
        
        return height;
    }
    
    @Override
    public int hashCode() { 
        
        return Objects.hash(row,col,height);
    }

    /** 
     * Should only return true if o is a Position3D object with the same row, column, and height. 
     */
    @Override
    public boolean equals(Object o) {

        if(this.getClass() !=  o.getClass()) {
            
            return false;
        }
        return this.equals((Position3D) o);
    }

    /** 
     * toString() should be the inverse of of(). 
     * This means it should output in the format A2(2) 
     */
    @Override
    public String toString() {

        String alphaRow;
        int strColumn = col + 1;
        int strHeight = height + 1;
        
        switch (row) {
            case 0 -> { alphaRow = "A";
            }
        
            case 1 -> { alphaRow = "B";
            }

            case 2 -> { alphaRow = "C";
            }

            case 3 -> { alphaRow = "D";
            }

            default -> { throw new RuntimeException(); //if input is not A-D i dont think this should be possible
            }
        }
        return alphaRow + strColumn + "(" + strHeight + ")";
    }

    /**
     * Compares 2 Position3D objects to see if they are the same 
     * (should only return true if p has the same row, column, and height)
     * @param p A Position3D object you wish to compare
     * @return A boolean representing if they equal or not
     */
    public boolean equals(Position3D p) {
        
        return row == p.getRow() 
        && col == p.getColumn()
        && height == p.getHeight();
    }
}
