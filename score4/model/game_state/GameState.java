package score4.model.game_state;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    private boolean isOver; //calculate dynamically
    private boolean draw; //calculate dynamicaly
    private Player winner;
    private int turn;
    private final Player[] thePlayers = new Player[2];
    private Board gameBoard;
    private final int maxMoves = 64;
    private ArrayList<Position3D> possibleMoves;

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
        ArrayList<Position3D> possibleMoves = new ArrayList<>();

        // Generate possible moves dynamically
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {
                Peg peg = gameBoard.getPeg(i, j);
                if (peg.getNextHeight() < gameBoard.getSize()) {
                    possibleMoves.add(new Position3D(i, j, peg.getNextHeight()));
                }
            }
        }

        // Sort the moves based on the heuristic
        possibleMoves.sort(moveComparator(colour));

        return possibleMoves;
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
    }

    /**
     * this method finds the best possible move given the current game state using iterative deepening
     * @param state GameState the current gamestate
     * @param maxDepth int the maximum depth of search
     * @param colour Colour to find best move for
     * @param lastMove Position3D the last move made
     * @return Position3D the best move
     */
    public Position3D findBestMove(GameState state, int maxDepth, Colour colour, Position3D lastMove) {

        Position3D bestMove = null;

        // Perform iterative deepening
        for (int depth = 1; depth <= maxDepth; depth++) {
            System.out.println("Searching at depth: " + depth);
            MoveResult result = minimaxWithMove(state, depth, true, colour, Integer.MIN_VALUE, Integer.MAX_VALUE, lastMove);

            // Update the best move found so far
            if (result.bestMove != null)
                bestMove = result.bestMove;

            System.out.println("Best Move at Depth " + depth + ": " + bestMove + ", Score: " + result.score);
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

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Applying move: " + move);
        System.out.println("Possible Moves Before Apply: " + possibleMoves);

        if (getIsOver()) {
            return;
        }
        if (move.getHeight() >= 4) {
            throw new IllegalArgumentException("Height is out of bounds: " + move.getHeight());
        }
        Peg peg = gameBoard.getPeg(move.getRow(), move.getColumn());
        peg.setBead(move.getRow(), move.getColumn(), colour); 
        turn++;

        System.out.println("Possible Moves After Apply: " + possibleMoves);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        if (Line.containsLine(Bead.getTheBeads(), colour)) {
            setWinner(getPlayer(turn % 2));
        }
    }

    /**
     * undoes the last move made in the game
     */
    public synchronized void undoMove(Position3D move, Colour colour) {

        System.out.println("..................................");
        System.out.println("Undoing move: " + move);
        System.out.println("Possible Moves Before Undo: " + possibleMoves);

        if (move.getHeight() < 0) {
            throw new IllegalArgumentException("Height is out of bounds: " + move.getHeight());
        }
        Peg peg = gameBoard.getPeg(move.getRow(), move.getColumn());
        peg.removeBead();
        turn--;

        System.out.println("Possible Moves After Undo: " + possibleMoves);
        System.out.println("..................................");

        if (winner != null) {
            removeWinner();
        }
    }

    /**
     * evaluates the game state for a given colour
     * @param colour Colour of the player
     * @return int score of the game state 
     */
    public int evaluate(Colour colour) {

        int score = 0;

        // Check for winning or losing state
        if (Line.containsLine(Bead.getTheBeads(), colour)) {
            score = 100000; // Winning State
            System.out.println("Winning State Detected for Colour: " + colour);
            return score;
        }

        if (Line.containsLine(Bead.getTheBeads(), colour == Colour.White ? Colour.Black : Colour.White)) {
            score = -100000; // Opponent Winning State
            System.out.println("Opponent Winning State Detected for Colour: " + (colour == Colour.White ? Colour.Black : Colour.White));
            return score;
        }

        if (getDrawStatus()) {
            score = 0; // Draw State
            System.out.println("Draw State Detected");
            return score;
        }

        // Count potential lines for both players
        int aiPotentialLines = Line.countPotentialLines(Bead.getTheBeads(), colour);
        int opponentPotentialLines = Line.countPotentialLines(Bead.getTheBeads(), colour == Colour.White ? Colour.Black : Colour.White);
        score += aiPotentialLines; // AI potential lines
        score -= opponentPotentialLines; // Opponent potential lines

        System.out.println("AI Potential Lines: " + aiPotentialLines + ", Opponent Potential Lines: " + opponentPotentialLines);
        System.out.println("Total Potential Lines Score : " + score);

        return score;
    }

    /**
     * Minimax algorithm with alpha-beta pruning
     * @param state GameState the current game state
     * @param depth int the depth of the search
     * @param maximizingPlayer boolean true if maximizing player, false otherwise
     * @param colour Colour the colour of the player to evaluate
     * @param alpha int the alpha value for pruning
     * @param beta int the beta value for pruning
     * @return MoveResult containing the best move and its score
     * @throws IllegalStateException if the AI selects an invalid move
     */
    public MoveResult minimaxWithMove(GameState state, int depth, boolean maximizingPlayer, Colour colour, int alpha, int beta, Position3D lastMove) {
        // Base case: return the evaluation score if the game is over or depth is 0
        if (getIsOver() || depth == 0) {
            int score = evaluate(colour);
            return new MoveResult(lastMove, score); // Return the last move that led to this state
        }

        List<Position3D> moves = new ArrayList<Position3D>(getPossibleMoves(colour));
        Position3D bestMove = null;

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;

            for (Position3D move : moves) {
                applyMove(move, colour); // Apply the move
                MoveResult result = minimaxWithMove(state, depth - 1, false, colour, alpha, beta, move);
                undoMove(move, colour); // Undo the move

                System.err.println("Depth: " + depth + ", Maximizing: " + maximizingPlayer + ", Move: " + move + ", Score: " + result.score);
                System.err.println("-----------------------------------------");

                if (result.score > maxEval) {
                    maxEval = result.score;
                    bestMove = move; // Update the best move
                }

                alpha = Math.max(alpha, maxEval);
                if (alpha >= beta) {
                    break; // Alpha-beta pruning
                }
            }

            System.err.println("Best Move at Depth " + depth + ": " + bestMove + ", Best Score: " + maxEval);
            System.err.println("=========================================");

            if (!getPossibleMoves(colour).contains(bestMove)) {
                throw new IllegalStateException("AI selected an invalid move: " + bestMove);
            }

            return new MoveResult(bestMove, maxEval);
        } else {
            int minEval = Integer.MAX_VALUE;

            for (Position3D move : moves) {
                applyMove(move, colour == Colour.White ? Colour.Black : Colour.White); // Apply the move
                MoveResult result = minimaxWithMove(state, depth - 1, true, colour, alpha, beta, move);
                undoMove(move, colour); // Undo the move

                System.err.println("Depth: " + depth + ", Maximizing: " + maximizingPlayer + ", Move: " + move + ", Score: " + result.score);
                System.err.println("-----------------------------------------");

                if (result.score < minEval) {
                    minEval = result.score;
                    bestMove = move; // Update the best move
                }

                beta = Math.min(beta, minEval);
                if (beta <= alpha) {
                    break; // Alpha-beta pruning
                }
            }

            System.err.println("Best Move at Depth " + depth + ": " + bestMove + ", Best Score: " + minEval);
            System.err.println("=========================================");
            System.err.println("Possible Moves at Depth " + depth + ": " + getPossibleMoves(colour));

            if (!getPossibleMoves(colour).contains(bestMove)) {
                throw new IllegalStateException("AI selected an invalid move: " + bestMove);
            }

            return new MoveResult(bestMove, minEval);
        }
    }

    /**
     * Comparator to sort moves based on their heuristic evaluation (eval method)
     * @param colour Colour the colour of the player
     * @return Comparator<Position3D> the comparator to sort moves
     */
    private Comparator<Position3D> moveComparator(Colour colour) {
        return (move1, move2) -> {
            // Apply the move temporarily
            applyMove(move1, colour);
            int score1 = evaluate(colour);
            undoMove(move1, colour);

            applyMove(move2, colour);
            int score2 = evaluate(colour);
            undoMove(move2, colour);

            // Sort in descending order (higher score first)
            return Integer.compare(score2, score1);
        };
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