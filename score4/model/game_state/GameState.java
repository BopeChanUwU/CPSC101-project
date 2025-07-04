package score4.model.game_state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import score4.model.game_state.board.Board;
import score4.model.game_state.board.Line;
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
    private boolean win = false;
    private int turn;
    private final Player[] thePlayers = new Player[2];
    private Board gameBoard;
    private final int maxMoves = 64;
    // Possible moves start at 4x4x0 (this is the bottom layer of the board)
    private ArrayList<Position3D> possibleMoves = new ArrayList<>(Arrays.asList( new Position3D(0, 0, 0), new Position3D(0, 1, 0), new Position3D(0, 2, 0), new Position3D(0, 3, 0),
            new Position3D(1, 0, 0), new Position3D(1, 1, 0), new Position3D(1, 2, 0), new Position3D(1, 3, 0),
            new Position3D(2, 0, 0), new Position3D(2, 1, 0), new Position3D(2, 2, 0), new Position3D(2, 3, 0),
            new Position3D(3, 0, 0), new Position3D(3, 1, 0), new Position3D(3, 2, 0), new Position3D(3, 3, 0)));
    private ArrayList<Bead> playedMoves = new ArrayList<>();

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
     * sets the winner of the game
     * @param player HumanPlayer the winner of the game
     */
    public void setWinner(Player player) {

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
     * 
     * @return
     */
    public boolean isAI() {

        return thePlayers[turn % 2] instanceof AIPlayer;
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
     * @param state
     * @param depth
     * @param colour
     * @return
     */
    public Position3D findBestMove(GameState state, int depth, Colour colour) {
        int bestValue = Integer.MIN_VALUE;
        Position3D bestMove = null;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        // Filter possibleMoves to ensure all positions have valid heights
        List<Position3D> moves = new ArrayList<>();
        for (Position3D move : possibleMoves) {
            if (move.getHeight() < 4) {
                moves.add(move);
            }
        }

        for (Position3D move : moves) {
            applyMove(move, colour);
            int value = minimax(state, depth - 1, false, colour, alpha, beta);
            undoMove();

            System.out.println("Evaluating move: " + move + " with score: " + value);

            if (value > bestValue && possibleMoves.contains(move)) {

                // If the move is better than the best found so far, update bestValue and bestMove
                System.out.println("New best move found: " + move + " with score: " + value);
                bestValue = value;
                bestMove = move;
            }

            alpha = Math.max(alpha, bestValue);
        }

        return bestMove;
    }

    /**
     * gets the player at a given index
     * @param index int the index of the player
     * @return Player the player at the given index
     */
    // Note: Index 0 is Player 1, Index 1 is Player 2
    public Player getPlayer(int index) {

        if(index < 0 || index >= thePlayers.length) {
            throw new IndexOutOfBoundsException("Invalid player index: " + index);
        }
        return thePlayers[index];
    }

    /**
     * applies a move to the game board and removes it from possibleMoves
     * @param move
     * @param colour
     */
    public synchronized void applyMove(Position3D move, Colour colour) {
        
        if (move.getHeight() >= 4) {
            throw new IllegalArgumentException("Height is out of bounds: " + move.getHeight());
        }

        gameBoard.getPeg(move.getRow(), move.getColumn()).setBead(move.getRow(), move.getColumn(), colour);
        playedMoves.add(new Bead(colour, move));
        removePossibleMove(move);

        if (move.getHeight() < 3) {
            addPossibleMove(new Position3D(move.getRow(), move.getColumn(), move.getHeight() + 1));
        }

        turn++;
        if (Line.containsLine(playedMoves)) {
            setWinner(thePlayers[turn % 2]);
        }
        if (turn > maxMoves) {
            setDraw();
        }
    }

    /**
     * undoes the last move made in the game
     */
    public void undoMove() {
        if (playedMoves.isEmpty()) {
            return; // No moves to undo
        }

        Bead move = playedMoves.remove(playedMoves.size() - 1);
        gameBoard.getPeg(move.getPosition3D().getRow(), move.getPosition3D().getColumn()).removeBead();

        if (move.getPosition3D().getHeight() < 4) {
            addPossibleMove(move.getPosition3D());
        }

        turn--;
    }

    /**
     * adds a given possible move if it doesnt already exist
     * @param move Position3D location to add to list
     */
    private void addPossibleMove(Position3D move) {
        
        if (move.getHeight() < 4 && !possibleMoves.contains(move)) {
            possibleMoves.add(move);
        }
    }

    /**
     * removes a given possible move from the list of possible moves
     * This method uses an iterator to safely remove the move from the list.
     * This is necessary because modifying a list while iterating over it can cause
     * a ConcurrentModificationException.
     * @param move Position3D location to remove from list
     */
    private void removePossibleMove(Position3D move) {
        Iterator<Position3D> iterator = possibleMoves.iterator();
        while (iterator.hasNext()) {
            Position3D possibleMove = iterator.next();
            if (possibleMove.equals(move)) {
                iterator.remove(); // Safe removal
            }
        }
    }

    /**
     * gets an array list of possible moves
     * @return ArrayList<Position3D> the possible moves
     */
    public ArrayList<Position3D> getPossibleMoves() {
    
        //TO-DO: this is temporary, for testing purposes
        /* for (Position3D possibleMove : possibleMoves) {
            
            System.out.println("Possible Move: " + possibleMove);
        } */
        return possibleMoves;
    }

    /**
     * evaluates the game state for a given colour
     * @param colour Colour of the player
     * @return int score of the game state 
     */
    public int evaluate(Colour colour) {

        //checks the game board to see the value of the current game state
        //search through the beads and check for lines spanning 1-3 if 1 give 10 if 2 give 100 if 3 give 1000
        /* int totalsingles = 0;
        int totalDoubles = 0;
        int totalTriples = 0;
        ArrayList<Bead> beads = playedMoves;
        
        if (win) {
            return Integer.MAX_VALUE; // AI wins
        } else if (draw) {
            return 0; // draw max moves reached
        }

        for (int i = 0; i < beads.size(); i++) {
            Bead firstBead = beads.get(i);
            if (firstBead.getColour() == colour) { // skips if not correct colour
                int count = 1;
                // Check for lines in all directions
                for (int j = i + 1; j < beads.size(); j++) {
                    Bead secondBead = beads.get(j);
                    if (secondBead.getColour() == colour && secondBead.getPosition3D().isBeside(firstBead.getPosition3D())) {
                        count++;
                        for(int k = j + 1; k < beads.size(); k++) {
                            Bead thirdBead = beads.get(k);
                            if (thirdBead.getColour() == colour && thirdBead.getPosition3D().areCollinear(firstBead.getPosition3D(), secondBead.getPosition3D())) {
                                count++;
                                break; // No need to check further for triples
                            }
                        }
                    } else {
                        break;  // break if not matching colour or adjacent
                    }
                    switch (count) {
                        case 1 -> totalsingles++;
                        case 2 -> totalDoubles++;
                        case 3 -> totalTriples++;
                    }
                }
            }
        }
        return (totalsingles * 10) + (totalDoubles * 100) + (totalTriples * 1000); */

        int score = 0;

        if (Line.containsLine(playedMoves, colour)) {
            score += 10000; // Winning State
            System.out.println("Winning State Detected for Colour: " + colour);
        }
        if (Line.containsLine(playedMoves, colour == Colour.White ? Colour.Black : Colour.White)) {
            score -= 10000; // Opponent Winning State
            System.out.println("Opponent Winning State Detected for Colour: " + (colour == Colour.White ? Colour.Black : Colour.White));
        }

        int aiPotentialLines = Line.countPotentialLines(playedMoves, colour);
        int opponentPotentialLines = Line.countPotentialLines(playedMoves, colour == Colour.White ? Colour.Black : Colour.White);
        score += aiPotentialLines * 10; // AI potential lines
        score -= opponentPotentialLines * 10; // Opponent potential lines

        System.out.println("AI Potential Lines: " + aiPotentialLines + ", Opponent Potential Lines: " + opponentPotentialLines);
        System.out.println("Final Score : " + score);

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
                undoMove(); // Undo the move
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
                undoMove(); // Undo the move
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, minEval);
                if (beta <= alpha) {
                    break; // Prune
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
