package score4.model.player;

import java.util.ArrayList;
import score4.model.game_state.board.Board;
import score4.model.game_state.board.Line;
import score4.model.game_state.board.Position3D;

public class AIPlayer implements Player {
    
    private Colour beadColour;
    private int wins = 0;
    private final String name;
    private boolean turn;
    private static final ArrayList<Position3D> highValuePositions = new ArrayList<>();

    /**
     * AIPlayer constructor
     * @param n int representing the player number
     * @throws IllegalArgumentException if n is not 1 or 2
     */
    public AIPlayer(int n) {

        if(n<1 || n>2){

            throw new IllegalArgumentException("this is a 2 player dumby not a " + n + " player game");
        } else if( n == 1) {

            beadColour = Colour.White;
            turn = true;
            name = "QWERTY";
        } else {

            beadColour = Colour.Black;
            turn = false;
            name = "HURTY";
        }
        
    }

    public static ArrayList<Position3D> getHighValuePositions(){

        if(highValuePositions.isEmpty()){
            highValuePositions.add(new Position3D(0, 0, 0));
            highValuePositions.add(new Position3D(3, 0, 0));
            highValuePositions.add(new Position3D(0, 3, 0));
            highValuePositions.add(new Position3D(3, 3, 0));
            highValuePositions.add(new Position3D(1, 1, 1));
            highValuePositions.add(new Position3D(1, 2, 1));
            highValuePositions.add(new Position3D(2, 1, 1));
            highValuePositions.add(new Position3D(2, 2, 1));
            highValuePositions.add(new Position3D(1, 1, 2));
            highValuePositions.add(new Position3D(1, 2, 2));
            highValuePositions.add(new Position3D(2, 1, 2));
            highValuePositions.add(new Position3D(2, 2, 2));
            highValuePositions.add(new Position3D(0, 0, 3));
            highValuePositions.add(new Position3D(3, 0, 3));
            highValuePositions.add(new Position3D(0, 3, 3));
            highValuePositions.add(new Position3D(3, 3, 3));
        }

        return highValuePositions;
    }

    /**
     *  Counts the score of potential lines of 4 beads of the same colour.
     *  A potential line is one that does not contain an opposite colour bead.
     *  Immediate threats (3-in-a-row where the 4th spot is the next available
     *  height on that peg) are weighted much higher to force blocking.
     * @param colour Colour to evaluate for
     * @param board Board the current game board (used for gravity checks)
     * @return An int score representing line potential for the given colour
     */
    public static int countPotentialLines(Colour colour, Board board) {

        int totalLines = 0;

        for (Line line : Line.allLines()) {
            int inArow = 0;
            boolean blocked = false;
            Position3D emptyPos = null;

            for (int k = 0; k < 4; k++) {
                Position3D pos = line.getPosition3D(k);
                Colour c = board.getBeadAt(pos.getRow(), pos.getColumn(), pos.getHeight()).getColour();

                if (c.equals(colour.opposite())) {
                    blocked = true;
                    break;
                } else if (c.equals(colour)) {
                    inArow++;
                    if (getHighValuePositions().contains(pos)) {
                        totalLines += 5;
                    }
                } else {
                    emptyPos = pos;
                }
            }

            if (!blocked) {
                boolean immediate = inArow == 3 && emptyPos != null &&
                    emptyPos.getHeight() == board.getPeg(emptyPos.getRow(), emptyPos.getColumn()).getNextHeight();

                switch (inArow) {
                    case 1 -> totalLines += 1;
                    case 2 -> totalLines += 25;
                    case 3 -> totalLines += (immediate ? 5000 : 150);
                    case 4 -> totalLines += 10000;
                }
            }
        }
        return totalLines;
    }

    /**
     * increases the number of wins by 1
     */
    @Override
    public void increaseWins(){

        wins += 1;
    }

    /**
     * gets the players current number of wins
     * @return int the total number of wins the player currently has
     */
    @Override
    public int getWins(){

        return wins;
    }

    /**
     * gets current player
     * @return boolean isCurrentPlayer
     * true if player is current player
     * false if player is not current player
     */
    @Override
    public boolean getTurn() {

        return turn;
    }

    /**
     * gets the bead colour
     * @return Colour beadColour
     */
    @Override
    public Colour getColour(){

        return beadColour;
    }

    /**
     * gets the players name
     * @return String players name
     */
    @Override
    public String getName() {
        
        return name;
    }

    /**
     * updates turn flag
     */
    @Override
    public void updateTurn() {
       
        turn = !turn;
    }

    /**
     * sets the players bead colour to the specified colour
     */
    @Override
    public void setColour(Colour colour) {
        
        beadColour = colour;
    }

    /**
     * decreases the players wins
     */
    @Override
    public void decreaseWins() {
        
        wins--;
    }
}