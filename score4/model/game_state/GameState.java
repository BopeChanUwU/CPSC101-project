package score4.model.game_state;

import java.util.ArrayList;
import score4.model.game_state.board.Board;
import score4.model.game_state.board.Line;
import score4.model.game_state.board.Peg;
import score4.model.game_state.board.Position3D;
import score4.model.player.AIPlayer;
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

    private Player winner;
    private int turn;
    private final Player[] thePlayers = new Player[2];
    private Board gameBoard;
    private final int maxMoves = 64;

    /**
     * a 2 arg constructor 
     * @param player1 Player the first player
     * @param player2 Player the second player
     */
    public GameState(Player player1, Player player2) {

        gameBoard = new Board();
        thePlayers[0] = player1;
        thePlayers[1] = player2;
        turn = 0; // Player 1 starts
    }

    /**
     * a 3 arg constructor
     * @param player1 Player the first player
     * @param player2 Player the second player
     * @param size int the size of the game board
     */
    public GameState(Player player1, Player player2, int size){

        gameBoard = new Board(size);
        thePlayers[0] = player1;
        thePlayers[1] = player2;
        turn = 0; // Player 1 starts
    }

    /**
     * gets the possible moves for the current game state
     * @return ArrayList<Position3D> the list of possible moves
     */
    public ArrayList<Position3D> getPossibleMoves(Colour colour) {
        ArrayList<Position3D> moves = new ArrayList<>();
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {
                Peg peg = gameBoard.getPeg(i, j);
                if (peg.getNextHeight() < gameBoard.getSize()) {
                    moves.add(new Position3D(i, j, peg.getNextHeight()));
                }
            }
        }
        return moves;
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

        return (winner != null) || getDrawStatus();
    }

    /**
     * gets the winner of the game
     * @return Player the winner of the game
     * @throws IllegalStateException if no winner has been set yet
     */
    public Player getWinner() {

        if (winner == null) {
            throw new IllegalStateException("No winner has been set yet.");
        }

        return winner;
    }

    /**
     * checks if game is a draw
     * @return true if game is a draw
     * false if game is not a draw
     */
    public boolean getDrawStatus() {

        return turn >= maxMoves;
    }

    /**
     * sets the winner of the game
     * @param player HumanPlayer the winner of the game
     */
    public void setWinner(Player player) {

        winner = player;
        player.increaseWins();
    }

    /**
     * removes the winner of the game
     */
    public void removeWinner(){

        winner.decreaseWins();
        winner = null;
    }

    /**
     * gets the current turn of the game
     * @return the Player whose turn it is
     */
    public Player getTurn() {
    
        return thePlayers[turn % 2];
    }
    
    /**
     * this method checks to see if there is a computer player
     * @return Boolean whether or not the game contains a computer player
     */
    public boolean isAI() {

        return thePlayers[1] instanceof AIPlayer;
    }

    /**
     * this method checks to see if both players are computer players
     * @return Boolean whether or not both players are computer players
     */
    public boolean bothAI() {

        return thePlayers[0] instanceof AIPlayer && thePlayers[1] instanceof AIPlayer;
    }
    
    /**
     * resets the game state to defaults
     */
    public void resetGameState(){
        gameBoard = new Board();
        turn = 0;
        winner = null;
    }

    /**
     * this method finds the best possible move given the current game state using iterative deepening
     * @param maxDepth int the maximum depth of search
     * @param colour Colour to find best move for
     * @return Position3D the best move
     */
    public Position3D findBestMove(int maxDepth, Colour colour) {

        Position3D bestMove = null;
        ArrayList<Position3D> currentMoves = getPossibleMoves(colour);

        for (int depth = 1; depth <= maxDepth; depth++) {
            int bestScore = Integer.MIN_VALUE;
            for (Position3D move : currentMoves) {
                applyMove(move, colour);
                int score = minimax(depth - 1, false, colour, Integer.MIN_VALUE, Integer.MAX_VALUE);
                undoMove(move, colour);

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
        }

        return bestMove;
    }

    /**
     * gets the player at a given index
     * @param index int the index of the player
     * @return Player the player at the given index
     */
    public Player getPlayer(int index) {

        if(index < 0 || index >= thePlayers.length) {
            throw new IndexOutOfBoundsException("Invalid player index: " + index);
        }
        return thePlayers[index];
    }

    /**
     * applies a move to the game board and removes it from possibleMoves
     * @param move the move to be applied to the game board
     * @param colour the colour of the player
     */
    public synchronized void applyMove(Position3D move, Colour colour) {

        if (getIsOver()) {
            return;
        }
        if (move.getHeight() >= 4) {
            throw new IllegalArgumentException("Height is out of bounds: " + move.getHeight());
        }
        Peg peg = gameBoard.getPeg(move.getRow(), move.getColumn());
        peg.setBead(move.getRow(), move.getColumn(), colour); 

        if (containsLine(Bead.getTheBeads(), colour)) {
            setWinner(getPlayer(turn % 2));
        }
        turn++;
    }

    /**
     * undoes the last move made in the game
     */
    public synchronized void undoMove(Position3D move, Colour colour) {

        if (move.getHeight() < 0) {
            throw new IllegalArgumentException("Height is out of bounds: " + move.getHeight());
        }
        Peg peg = gameBoard.getPeg(move.getRow(), move.getColumn());
        peg.removeBead();
        turn--;

        if (winner != null) {
            removeWinner();
        }
    }

    /**
     * Checks to see if there is a line of 4 beads of the same colour
     * in the arraylist of beads passed in.
     * @param beads ArrayList of Beads to check
     * @param colour Colour to check for
     * @return A boolean whether or not there is a line of 4 beads of the same colour
     */
    public static boolean containsLine(ArrayList<Bead> beads, Colour colour) {

        boolean coloursMatch;
        int count;
        for (int i = 0; i < beads.size(); i++) {
            for (int j = i + 1; j < beads.size(); j++) {

                if(Line.isLegalStartEnd(beads.get(i).getPosition3D(),beads.get(j).getPosition3D())) {

                    Line line = new Line(beads.get(i).getPosition3D(), beads.get(j).getPosition3D());
                    coloursMatch = Bead.coloursMatch(beads.get(i).getColour(), beads.get(j).getColour());
                    count = 0;

                    for (Bead bead : beads) {
                        if(line.hasPosition3D(bead.getPosition3D()) && bead.getColour() == colour) {
                            count++;
                        }
                    }

                    if(coloursMatch && count == 4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * evaluates the game state for a given colour
     * @param colour Colour of the player
     * @return int score of the game state 
     */
    public int evaluate(Colour colour) {

        if (containsLine(Bead.getTheBeads(), colour)) {
            return 100000;
        }
        if (containsLine(Bead.getTheBeads(), colour.opposite())) {
            return -100000;
        }
        if (getDrawStatus()) {
            return 0;
        }

        int score = AIPlayer.countPotentialLines(colour, gameBoard);
        score -= AIPlayer.countPotentialLines(colour.opposite(), gameBoard);
        return score;
    }

    /**
     * Minimax algorithm with alpha-beta pruning
     * @param depth int the depth of the search
     * @param maximizingPlayer boolean true if maximizing player, false otherwise
     * @param colour Colour the colour of the player to evaluate
     * @param alpha int the alpha value for pruning
     * @param beta int the beta value for pruning
     * @return int score
     */
    public int minimax(int depth, boolean maximizingPlayer, Colour colour, int alpha, int beta) {

        if (getIsOver()) {
            if (winner != null) {
                return winner.getColour().equals(colour) ? 100000 - depth : -100000 + depth;
            }
            return 0;
        }
        if (depth == 0) {
            return evaluate(colour);
        }

        ArrayList<Position3D> moves = getPossibleMoves(colour);

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Position3D move : moves) {
                applyMove(move, colour);
                int score = minimax(depth - 1, false, colour, alpha, beta);
                undoMove(move, colour);
                maxEval = Math.max(maxEval, score);
                alpha = Math.max(alpha, maxEval);
                if (alpha >= beta) break;
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Position3D move : moves) {
                applyMove(move, colour.opposite());
                int score = minimax(depth - 1, true, colour, alpha, beta);
                undoMove(move, colour.opposite());
                minEval = Math.min(minEval, score);
                beta = Math.min(beta, minEval);
                if (beta <= alpha) break;
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