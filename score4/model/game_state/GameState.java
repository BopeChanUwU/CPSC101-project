package score4.model.game_state;

import java.util.ArrayList;
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

    private boolean isOver = false;
    private boolean draw = false;
    private Player winner = null;
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
    public ArrayList<Position3D> getPossibleMoves() {

        possibleMoves = new ArrayList<>();

        for(int i = 0; i < gameBoard.getSize(); i++) {
            for(int j = 0; j < gameBoard.getSize(); j++) {

                if(gameBoard.getPeg(i, j).getBeadCount() < gameBoard.getSize()) 
                    possibleMoves.add(new Position3D(i, j, gameBoard.getPeg(i, j).getBeadCount()) );
            }

            
        }
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

        return isOver;
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
     * sets the winner of the game
     * @param player HumanPlayer the winner of the game
     */
    public void setWinner(Player player) {

        winner = player;
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
     * this method checks to see if there is a computer player
     * @return Boolean whether or not the game contains a computer player
     */
    public boolean isAI() {

        return thePlayers[turn % 1] instanceof AIPlayer;
    }

    /**
     * resets the game state to defaults
     */
    public void resetGameState(){

        isOver = false;
        draw = false;
        winner = null; 
        gameBoard = new Board();
    }

    /**
     *  this method finds the best possible move given the current game state
     * @param state GameState the current gamestate
     * @param depth int the depth of search
     * @param colour Colour to find best move for
     * @return Position3D the best move
     */
    public Position3D findBestMove(GameState state, int depth, Colour colour) {
        MoveResult result = minimaxWithMove(state, depth, true, colour, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (!state.getPossibleMoves().contains(result.bestMove)) {
            throw new IllegalStateException("findBestMove selected an invalid move: " + result.bestMove);
        }
        return result.bestMove;
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
        if (move.getHeight() >= 4) {
            throw new IllegalArgumentException("Height is out of bounds: " + move.getHeight());
        }

        Peg peg = gameBoard.getPeg(move.getRow(), move.getColumn());
        peg.setBead(move.getRow(), move.getColumn(), colour); // Place the bead on the board
        
        turn++;
        if (Line.containsLine(Bead.getTheBeads(), colour)) {
            setWinner(getPlayer(turn % 2));
        }
        if (turn > maxMoves) {
            setDraw();
        }
    }

    /**
     * undoes the last move made in the game
     */
    public void undoMove(Position3D move, Colour colour) {

        if (move.getHeight() < 0) {
            throw new IllegalArgumentException("Height is out of bounds: " + move.getHeight());
        }

        gameBoard.getPeg(move.getRow(), move.getColumn()).removeBead();
        //TODO: fix possibleMoves
        //removePossibleMove(move);

        turn--;
        if (Line.containsLine(Bead.getTheBeads(), colour)) {
            setWinner(thePlayers[turn % 2]);
        }
        if (turn > maxMoves) {
            setDraw();
        }
    }

    /**
     * evaluates the game state for a given colour
     * @param colour Colour of the player
     * @return int score of the game state 
     */
    public int evaluate(Colour colour) {

        int score = 0;

        if (Line.containsLine(Bead.getTheBeads(), colour)) {
            score += 10000; // Winning State
            System.out.println("Winning State Detected for Colour: " + colour);
        }
        if (Line.containsLine(Bead.getTheBeads(), colour == Colour.White ? Colour.Black : Colour.White)) {
            score -= 10000; // Opponent Winning State
            System.out.println("Opponent Winning State Detected for Colour: " + (colour == Colour.White ? Colour.Black : Colour.White));
        }

        int aiPotentialLines = Line.countPotentialLines(Bead.getTheBeads(), colour);
        int opponentPotentialLines = Line.countPotentialLines(Bead.getTheBeads(), colour == Colour.White ? Colour.Black : Colour.White);
        score += aiPotentialLines * 10; // AI potential lines
        score -= opponentPotentialLines * 10; // Opponent potential lines

        System.out.println("AI Potential Lines: " + aiPotentialLines + ", Opponent Potential Lines: " + opponentPotentialLines);
        System.out.println("Total Potential Lines Score : " + score);

        

        return score;
    }

    /** 
     * minimax algo  this takes in the game state and then checks 
     * to find the best possible move
     * @param GameState
     * @param int depth of search 
     * @param boolean the maximizing player
     * @param Colour computer players colour
     * @param int alpha
     * @param int beta
     * @return int the value of the end state
    */
    public int minimax(GameState state, int depth, boolean maximizingPlayer, Colour colour, int alpha, int beta) {
        // Create a copy of possibleMoves to iterate safely
        List<Position3D> moves = new ArrayList<>(state.getPossibleMoves());

        if (state.getIsOver() || depth == 0) {
            return state.evaluate(colour) - state.evaluate(colour == Colour.White ? Colour.Black : Colour.White);
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Position3D move : moves) {
                applyMove(move, colour); // Apply the move to the game board
                int eval = minimax(this, depth - 1, false, colour, alpha, beta);
                undoMove(move, colour); // Undo the move
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, maxEval);
                if (alpha >= beta) {
                    break; // Prune
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Position3D move : moves) {
                applyMove(move, colour == Colour.White ? Colour.Black : Colour.White); // Apply the move to the game board
                int eval = minimax(this, depth - 1, true, colour, alpha, beta);
                undoMove(move, colour); // Undo the move
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, minEval);
                if (beta <= alpha) {
                    break; // Prune
                }
            }
            return minEval;
        }
    }

    public MoveResult minimaxWithMove(GameState state, int depth, boolean maximizingPlayer, Colour colour, int alpha, int beta) {
        // Base case: return the evaluation score if the game is over or depth is 0
        if (state.getIsOver() || depth == 0) {
            int score = state.evaluate(colour);
            return new MoveResult(null, score); // No move at this level
        }

        List<Position3D> moves = new ArrayList<>(state.getPossibleMoves());
        Position3D bestMove = null;

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;

            for (Position3D move : moves) {
                state.applyMove(move, colour); // Apply the move
                MoveResult result = minimaxWithMove(state, depth - 1, false, colour, alpha, beta);
                state.undoMove(move, colour); // Undo the move

                System.out.println("Depth: " + depth + ", Maximizing: " + maximizingPlayer + ", Move: " + move + ", Score: " + result.score);

                if (result.score > maxEval) {
                    maxEval = result.score;
                    bestMove = move; // Update the best move
                }

                alpha = Math.max(alpha, maxEval);
                if (alpha >= beta) {
                    break; // Alpha-beta pruning
                }
            }

            System.out.println("Best Move at Depth " + depth + ": " + bestMove + ", Best Score: " + maxEval);

            if (!possibleMoves.contains(bestMove)) {
                throw new IllegalStateException("AI selected an invalid move: " + bestMove);
            }

            return new MoveResult(bestMove, maxEval);
        } else {
            int minEval = Integer.MAX_VALUE;

            for (Position3D move : moves) {
                state.applyMove(move, colour == Colour.White ? Colour.Black : Colour.White); // Apply the move
                MoveResult result = minimaxWithMove(state, depth - 1, true, colour, alpha, beta);
                state.undoMove(move, colour); // Undo the move

                System.out.println("Depth: " + depth + ", Maximizing: " + maximizingPlayer + ", Move: " + move + ", Score: " + result.score);

                if (result.score < minEval) {
                    minEval = result.score;
                    bestMove = move; // Update the best move
                }

                beta = Math.min(beta, minEval);
                if (beta <= alpha) {
                    break; // Alpha-beta pruning
                }
            }

            System.out.println("Best Move at Depth " + depth + ": " + bestMove + ", Best Score: " + minEval);

            if (!possibleMoves.contains(bestMove)) {
                throw new IllegalStateException("AI selected an invalid move: " + bestMove);
            }

            return new MoveResult(bestMove, minEval);
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