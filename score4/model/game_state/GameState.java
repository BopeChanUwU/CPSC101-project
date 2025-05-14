package score4.model.game_state;

import score4.model.game_state.board.Board;
import score4.model.player.AIPlayer;
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
    private int turn = 0;
    private Board gameBoard;
    private int[] lastMove; // not sure how to store this right now
    private int moveCount = 0;
    private final int maxMoves = 64;
    private final int maxTurns = 32;
    /* IDEA!!! have move both update game state and game view (via model) */

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
        lastMove = new int[2]; // ? whats my plan with this ? 
    }

    public GameState(HumanPlayer player1, AIPlayer player2) {

        gameBoard = new Board();
        lastMove = new int[2]; // ? whats my plan with this ? 
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
        this.isOver = isOver;
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
     * @param isDraw true if game is a draw
     */
    public void setDraw() {
        
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

        player.setnumWin();
    }

    /**
     * sets the current turn of the game if turns are greater than 
     * maxMoves sets draw to true
     * @param turn int the current turn of the game
     */
    public void setTurn() {

        if(turn <= maxMoves) {

            turn += 1;
        } else {

            setDraw();
        }
    }

    /**
     * gets the current turn of the game
     * @return the current turn of the game
     */
    public int getTurn() {

        return turn;
    }

    /**
     * resets the game state to defaults
     */
    public void resetGameState(){

        isOver = false;
        draw = false;
        win = false; 
        turn = 0;
        gameBoard = new Board();
        moveCount = 0;
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
