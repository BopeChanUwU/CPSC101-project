package score4.model.game_state;

import java.util.ArrayList;
import java.util.Arrays;

import score4.model.game_state.board.Board;
import score4.model.game_state.board.Position3D;
import score4.model.player.Bead;
import score4.model.player.Colour;
import score4.model.player.Player;

/**
 * This file is part of a Score4 game
 *
 * <p> 
 * This class represents the state of the game. It contains information about
 * the game board, the current turn, and whether the game is over or a draw.
 * It also keeps track of the history of moves made during the game.
 * <p>
 * The class provides methods to set and get the game state, including the
 * current turn, winner, and whether the game is over or a draw. It also
 * provides methods to set and get the game board and the last move made.
 * <p>
 * This class is used by the GameboyController to manage the game state
 * and by the GameboyView to display the game state to the user.
 * <p>
 *
 * @author Tristen Sandhu

 * @version 1
 */
public class GameState implements Cloneable{

    private boolean isOver = false;
    private boolean draw = false;
    private boolean win = false;
    private int turn;
    private final Player[] thePlayers = new Player[2];
    private Board gameBoard;
    private final int maxMoves = 64;
    // Possible moves start at 4x4x0
    private ArrayList<Position3D> possibleMoves = new ArrayList<>(Arrays.asList( new Position3D(0, 0, 0), new Position3D(0, 1, 0), new Position3D(0, 2, 0), new Position3D(0, 3, 0),
            new Position3D(1, 0, 0), new Position3D(1, 1, 0), new Position3D(1, 2, 0), new Position3D(1, 3, 0),
            new Position3D(2, 0, 0), new Position3D(2, 1, 0), new Position3D(2, 2, 0), new Position3D(2, 3, 0),
            new Position3D(3, 0, 0), new Position3D(3, 1, 0), new Position3D(3, 2, 0), new Position3D(3, 3, 0)));

    /**
     * 
     * @param player1
     * @param player2
     */
    public GameState(Player player1, Player player2) {

        gameBoard = new Board();
        thePlayers[0] = player1;
        thePlayers[1] = player2;
        turn = 0; // Player 1 starts
    }

    /**
     * 
     * @param player1
     * @param player2
     * @param size
     */
    public GameState(Player player1, Player player2, int size){

        gameBoard = new Board(size);
        thePlayers[0] = player1;
        thePlayers[1] = player2;
        turn = 0; // Player 1 starts
    }

    /**
     * gets the current game board
     * @return returns the current board
     */
    public Board getBoard() {

        return gameBoard;
    }

    /**
     * gets the game state
     * @return true if game is over
     * false if game is not over
     */
    public boolean getIsOver() {

        return isOver;
    }

    /**
     * Sets game status to draw
     */
    public void setDraw() {
        
        draw = true; 
        isOver = true;
    }

    /**
     * checks if game is a draw
     * @return true if game is a draw
     * false if game is not a draw
     */
    public boolean getDrawStatus() {

        return draw;
    }

    /** 
     * returns whether the game is won
     * @return true if game is won
     * false if game is not won
     */
    public boolean getWinStatus(){

        return win;
    }

    /**
     * sets the winner of the game
     * @param player HumanPlayer the winner of the game
     */
    public void Win(Player player) {

        win = true;
        isOver = true;
        player.increaseWins();
    }

    /**
     * gets the current turn of the game
     * @return the Player whose turn it is
     */
    public Player getTurn() {
    
        return thePlayers[turn % 2];
    }
    
    /**
     * resets the game state to defaults
     */
    public void resetGameState(){

        isOver = false;
        draw = false;
        win = false; 
        getTurn();
        gameBoard = new Board();
    }

    /**
     * applies a move to the game board and removes it from possibleMoves
     * @param move
     * @param colour
     */
    public void applyMove(Position3D move, Colour colour) {

        gameBoard.getPeg(move.getRow(),move.getColumn()).setBead(move.getRow(),move.getColumn(),colour);
        removePossibleMove(move);
        addPossibleMove(new Position3D(move.getRow(), move.getColumn(), move.getHeight() + 1));
    }

    /**
     * undoes a given move and adds it to possibleMoves
     * @param move Position3D location of the move to undo
     */
    public void undoMove(Bead move) {
    
        gameBoard.getPeg(move.getPosition3D().getRow(),move.getPosition3D().getColumn()).removeBead(); 
        addPossibleMove(move.getPosition3D());
        removePlayedMove(move);
    }

    /**
     * 
     * @param move
     */
    private void removePlayedMove(Bead move) {

        if(Bead.getTheBeads().contains(move))
            Bead.getTheBeads().remove(move);
    }

    /**
     * adds a given possible move if it doesnt already exist
     * @param move Position3D location to add to list
     */
    private void addPossibleMove(Position3D move) {

        if(!possibleMoves.contains(move))
            possibleMoves.add(move);
    }

    /**
     * 
     * @param move
     */
    private void removePossibleMove(Position3D move) {

        if(possibleMoves.contains(move))
            possibleMoves.remove(move);
    }

    /**
     * gets an array list of possible moves
     * @return ArrayList<Position3D> the possible moves
     */
    public ArrayList<Position3D> getPossibleMoves() {
    
        return possibleMoves;
    }

    /**
     * evaluates the game state for the AI
     * @param aiColour Colour of the AI player
     * @return int score of the game state for the AI
     */
    public int evaluate(Colour aiColour) {

        //checks the game board to see if ai is winning, losing, or neutral
        //TODO: implement evaluation function here
        return 0;
    }

    /** 
     * minimax algo  this takes in the game state and then checks 
     * @param GameState
     * @param int depth of search 
     * @param boolean the maximizing player
     * @param Colour computer players colour
     * @return int the value of the end state
    */
    public int minimax(GameState state, int depth, boolean maximizingPlayer, Colour aiColour) {
        
        //checks the value of state or ends if depth is 0
        if (state.getIsOver() || depth == 0) {
            if (state.getDrawStatus()) {
                return 0; // Draw
            } else if (state.getWinStatus()) {
                return 1; // AI wins
            } else {
                return -1; // Opponent wins
            }
        }

        // if maximizing set max eval equal to mall int clone state 
        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Position3D move : state.getPossibleMoves()) {
                GameState newState;
                try {
                    newState = state.clone();
                    newState.applyMove(move, aiColour);
                    int eval = minimax(newState, depth - 1, false, aiColour);
                    maxEval = Math.max(maxEval, eval);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            return maxEval;
        } else {
            //this is minimizing player
            int minEval = Integer.MAX_VALUE;
            for (Position3D move : state.getPossibleMoves()) {
                GameState newState;
                try {
                    newState = state.clone();
                    newState.applyMove(move, aiColour == Colour.White ? Colour.Black : Colour.White);
                    int eval = minimax(newState, depth - 1, true, aiColour);
                    minEval = Math.min(minEval, eval);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                
            }
            return minEval;
        }
    }

    /**
     * clones the game state
     * @return a clone of the game state
     * @throws CloneNotSupportedException if the game state cannot be cloned
     */
    @Override
    public GameState clone() throws CloneNotSupportedException {

        GameState clone = (GameState) super.clone();
        clone.gameBoard = gameBoard.clone();
        return clone;
    }
}
