package score4.model.game_state;

import java.util.ArrayList;
import score4.model.game_state.board.Board;
import score4.model.game_state.board.Position3D;
import score4.model.player.AIPlayer;
import score4.model.player.Colour;
import score4.model.player.HumanPlayer;
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
    private Player turn;
    private final Player[] thePlayers = new Player[2];
    private Board gameBoard;
    private final int maxMoves = 64;
    private ArrayList<Position3D> playedMoves = new ArrayList<>();
    private ArrayList<Position3D> possibleMoves = Position3D.allPositions(); //should i store all 64 at start or just bottom layer and tweak as i go?

    /**
     * 
     * @param player1
     * @param player2
     */
    public GameState(HumanPlayer player1, HumanPlayer player2) {

        gameBoard = new Board();
        thePlayers[0] = player1;
        thePlayers[1] = player2;
    }

    /**
     * 
     * @param player1
     * @param player2
     * @param size
     */
    public GameState(HumanPlayer player1, HumanPlayer player2, int size){

        gameBoard = new Board(size);
        thePlayers[0] = player1;
        thePlayers[1] = player2;
    }

    /**
     * 
     * @param player1
     * @param player2
     */
    public GameState(HumanPlayer player1, AIPlayer player2) {

        gameBoard = new Board();
        thePlayers[0] = player1;
        thePlayers[1] = player2;
    }

    /**
     * 
     * @param player1
     * @param player2
     * @param size
     */
    public GameState(HumanPlayer player1, AIPlayer player2, int size) {

        gameBoard = new Board(size);
        thePlayers[0] = player1;
        thePlayers[1] = player2;
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
    public boolean isGameOver() {

        return isOver;
    }

    /**
     * Sets game status to draw
     */
    public void Draw() {
        
        draw = true; 
        isOver = true;
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
    public void Win(Player player) {

        win = true;
        isOver = true;
        player.increaseWins();
    }

    /**
     * gets the current turn of the game
     * @return the current turn of the game
     */
    public Player getTurn() {
    
        if(thePlayers[0].getTurn()) {
            return thePlayers[0];
        } else {
            return thePlayers[1];
        }
    }

    /**
     * checks to see if a player has won the game
     */
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
        getTurn();
        gameBoard = new Board();
    }

    /**
     * 
     * @param move
     * @param colour
     */
    public void applyMove(Position3D move, Colour colour) {

        gameBoard.getPeg(move.getRow(),move.getColumn()).setBead(move.getRow(),move.getColumn(),colour);
        removePossibleMove(move);
        addPlayedMove(move);
    }

    /**
     * undoes a given move and adds it to possibleMoves
     * @param move Position3D location of the move to undo
     */
    public void undoMove(Position3D move) {
    
        gameBoard.getPeg(move.getRow(),move.getColumn()).removeBead(); 
        addPossibleMove(move);
        removePlayedMove(move);
    }

    /**
     * 
     * @param move
     */
    private void addPlayedMove(Position3D move) {

        if(!playedMoves.contains(move))
            playedMoves.add(move);
    }

    /**
     * 
     * @param move
     */
    private void removePlayedMove(Position3D move) {

        if(playedMoves.contains(move))
            playedMoves.remove(move);
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
     * 
     * @return
     */
    public ArrayList<Position3D> getPlayedMoves(){

        return playedMoves;
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
