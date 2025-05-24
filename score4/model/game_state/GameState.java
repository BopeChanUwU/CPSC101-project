package score4.model.game_state;

import java.util.ArrayList;
import score4.model.game_state.board.Board;
import score4.model.game_state.board.Position3D;
import score4.model.player.AIPlayer;
import score4.model.player.Colour;
import score4.model.player.HumanPlayer;

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
    private int turnNum = 0;
    private Board gameBoard;
    private Position3D[] lastMove; // 0 = white, 1 = black
    private final int maxMoves = 64;
    private ArrayList<Position3D> possibleMoves = new ArrayList<>(); //should i store all 64 at start or just bottom layer and tweak as i go?

    /**
     * Constructor for the GameState class
     * initializes the game state to default values
     * sets the game to not over, not a draw, and no winner
     * sets the turn to 0 (white)
     * initializes the board history to an empty board
     * initializes the board to an empty board
     * initializes the last move to an empty move
     * initializes the move count to 0
     * <p>
     */
    public GameState(HumanPlayer player1, HumanPlayer player2) {

        gameBoard = new Board();
        lastMove = new Position3D[2]; // ? whats my plan with this ? 
    }

    public GameState(HumanPlayer player1, HumanPlayer player2, int size){

        gameBoard = new Board(size);
        lastMove = new Position3D[2]; // ? whats my plan with this ?  

    }

    public GameState(HumanPlayer player1, AIPlayer player2) {

        gameBoard = new Board();
        lastMove = new Position3D[2]; // ? whats my plan with this ? 
    }

    public GameState(HumanPlayer player1, AIPlayer player2, int size) {

        gameBoard = new Board(size);
        lastMove = new Position3D[2]; // ? whats my plan with this ? 
    }

    /**
     * gets the current game board
     * @return returns the current board
     */
    public Board getBoard() {

        return gameBoard;
    }

    /**
     * sets the game state to over
     * @param isOver true if game is over
     * false if game is not over
     */
    public void setGameOver(boolean isOver) {

        if(win || draw) {

            isOver = true;
        }
    }

    /**
     * gets the game state
     * @return true if game is over
     * false if game is not over
     */
    public boolean isGameOver() {

        return isOver;
    }

    /**
     * Sets game status to draw
     */
    public void Draw() {
        
        draw = true; 
    }

    /**
     * checks if game is a draw
     * @return true if game is a draw
     * false if game is not a draw
     */
    public boolean isDraw() {

        return draw;
    }

    /**
     * sets the winner of the game
     * @param player HumanPlayer the winner of the game
     */
    public void setWinner(HumanPlayer player) {

        win = true;
        player.setnumWin();
    }

    /**
     * sets the current turn of the game if turns are greater than 
     * maxMoves sets draw to true
     * @param turn int the current turn of the game
     */
    public void setTurn() {

        if(turnNum <= maxMoves) {

            turnNum += 1;
        } else {

            Draw();
        }
    }

    /**
     * gets the current turn of the game
     * @return the current turn of the game
     */
    public int getTurn() {

        return turnNum;
    }

    public void winChecker(){

        //TODO: implement winchecker here!
    }

    /**
     * resets the game state to defaults
     */
    public void resetGameState(){

        isOver = false;
        draw = false;
        win = false; 
        turnNum = 0;
        gameBoard = new Board();
    }

    public void applyMove(Position3D move, Colour colour) {

        gameBoard.getPeg(move.getRow(),move.getColumn()).setBead(move.getRow(),move.getColumn(),colour);
    }

    public void undoMove(Position3D move) {
    
        gameBoard.getPeg(move.getRow(),move.getColumn()).removeBead(turnNum, turnNum); 
        //TODO: add move undo logic
        //undo the move stored in lastMove
        //remove the last move from the game board
    }

    public void updatePossibleMoves() {
    
        //TODO: add logic to update possible moves
    }

    /**
     * gets an array list of possible moves
     * @return ArrayList<Position3D> the possible moves
     */
    public ArrayList<Position3D> getPossibleMoves() {
    
        return possibleMoves;
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
        clone.lastMove = lastMove.clone();
        return clone;
    }
}
