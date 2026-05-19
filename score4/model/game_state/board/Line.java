package score4.model.game_state.board;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Line class capable of holding a 3D line
 * in a 4x4x4 grid.
 * This class is used to represent the line of a bead in the game.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class Line {

    private int deltaX;
    private int deltaY;
    private int deltaZ;

    private final Position3D[] line = new Position3D[4];

    private static final ArrayList<Line> theLines = new ArrayList<>();
    
    /**
     * This method returns a boolean by checking that your start does not equal to your end as 
     * you cant have a line with only one point and checking as well to see if your row, column,
     * and height all either start where they end or that they end 4 spaces away.
     * @param start Position3D the position the line starts at
     * @param end Position3D the position the line ends at
     * @return a boolean whether or not this is a legal line
     */
    public static boolean isLegalStartEnd(Position3D start, Position3D end) {
        
        return ! start.equals(end) 
        && (end.getRow() - start.getRow()) % 3 == 0
        && (end.getColumn() - start.getColumn()) % 3 == 0
        && (end.getHeight() - start.getHeight()) % 3 == 0;
    }

    /**
     * A 2 parameter constructor that takes in 2 Position3D points and creates a line if 
     * the start and end are legal 
     * @param start A Position3D object representing the start of a line 
     * @param end A Position3D object representing the end of a line 
     * @throws IllegalArgumentException if start and end are not legal lines
     */
    public Line(Position3D start, Position3D end) {
        
        if(isLegalStartEnd(start, end)) {

            deltaX = (end.getRow() - start.getRow()) / 3;
            deltaY = (end.getColumn() - start.getColumn()) / 3;
            deltaZ = (end.getHeight() - start.getHeight()) / 3;
            line[0] = start;
            line[3] = end;
            for (int i = 1; i < 3; i++) {

                line[i] = new Position3D(start.getRow() + (deltaX * i), 
                    start.getColumn() + (deltaY * i), 
                    start.getHeight() + (deltaZ * i));
            }
        } else {

            throw new IllegalArgumentException("Illegal Line: " + start + " and " 
                + end + "must be 4 points away from eachother");
        }
    }

    /**
     * This method returns an array list of all 76 possible lines.
     * @return ArrayList<Line> all possible lines
     */
    public static ArrayList<Line> allLines() {
        
        if(theLines.isEmpty()) {
            ArrayList<Position3D> positions = Position3D.allPositions();
            for (int i = 0; i < positions.size(); i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    if (isLegalStartEnd(positions.get(i), positions.get(j))) {
                        theLines.add(new Line(positions.get(i), positions.get(j)));
                    }
                }
            }
        }

        return theLines;
    }



    /**
     * This method returns an array list of all of the lines that contain p.
     * @param p Position3D position to seach for lines through
     * @return A ArrayList of all possible lines through p
     */
    public static ArrayList<Line> allLinesThrough(Position3D p) {
        
        ArrayList<Line> linesThrough = new ArrayList<>();
        for (Line line : allLines()) {

            if(line.hasPosition3D(p)) {

                linesThrough.add(line);
            }
        }
        return linesThrough;
    }

    /**
     * Gets the position contained in the line (0-3)
     * @param k int position in line
     * @return Point3D object representing the specified position in the line
     */
    public final Position3D getPosition3D(int k) {

        return new Position3D(
            line[k].getRow(),
            line[k].getColumn(),
            line[k].getHeight());
    }

    /**
     * This method returns true if this line contains p
     * @param p A Position3D object
     * @return A Boolean 
     */
    public boolean hasPosition3D(Position3D p) {
        
        return p.equals(line[0])
            ||p.equals(line[1])
            ||p.equals(line[2])
            ||p.equals(line[3]);
    }

    @Override
    public int hashCode() {

        return Objects.hash(line[0],line[1],line[2],line[3]);
    }

    /** 
     * Should only return true if "o" is a Line object containing the same Position3Ds. 
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Line other)) return false;
        return line[0].equals(other.line[0])
            && line[1].equals(other.line[1])
            && line[2].equals(other.line[2])
            && line[3].equals(other.line[3]);
    }

    /**
     *  toString() should return, something like “[B1(1),B2(2),B3(3),B4(4)]” 
     */
    @Override
    public String toString() {
        
        return "[" + line[0].toString() +
            "," + line[1].toString() +
            "," + line[2].toString() + 
            "," + line[3].toString() + "]";
    }
}
