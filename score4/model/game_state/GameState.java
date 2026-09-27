package score4.model.game_state;

import java.util.ArrayList;
import java.util.Random;
import score4.model.game_state.board.Board;
import score4.model.game_state.board.Line;
import score4.model.game_state.board.Peg;
import score4.model.game_state.board.Position3D;
import score4.model.player.AIPlayer;
import score4.model.player.Bead;
import score4.model.player.ClassicEvaluator;
import score4.model.player.Colour;
import score4.model.player.Evaluator;
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
    private final TranspositionTable transpositionTable = new TranspositionTable();
    private final Random random = new Random();

    private final Evaluator whiteEvaluator;
    private final Evaluator blackEvaluator;
    // whichever colour's search is currently running; findBestMove sets this once at the
    // start of a search so every leaf in that ENTIRE tree is scored consistently with that
    // colour's own evaluator, regardless of whose turn it happens to be at any given leaf
    private Evaluator activeEvaluator;

    // negamax negates alpha/beta on every recursive call (-beta, -alpha); using
    // Integer.MIN_VALUE here would overflow back to itself when negated, so the
    // "infinite" bound is kept one away from the int range's edge instead. Its
    // positive counterpart is simply -NEGATIVE_INFINITY wherever it's needed,
    // which negates cleanly for the same reason
    private static final int NEGATIVE_INFINITY = -Integer.MAX_VALUE;

    /**
     * a 2 arg constructor
     * @param player1 Player the first player
     * @param player2 Player the second player
     */
    public GameState(Player player1, Player player2) {

        this(player1, player2, new ClassicEvaluator());
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
        // both colours share ONE evaluator instance here (not two separate ones) so
        // findBestMove's "did the evaluator change?" check never sees a false switch
        // and clears the transposition table needlessly during normal, single-evaluator play
        Evaluator defaultEvaluator = new ClassicEvaluator();
        whiteEvaluator = defaultEvaluator;
        blackEvaluator = defaultEvaluator;
        activeEvaluator = defaultEvaluator;
    }

    /**
     * constructs a GameState where both players are scored using the same
     * evaluator, for comparing how that evaluator plays against itself
     * @param player1 Player the first player
     * @param player2 Player the second player
     * @param evaluator Evaluator used to score positions for both colours
     */
    public GameState(Player player1, Player player2, Evaluator evaluator) {

        this(player1, player2, evaluator, evaluator);
    }

    /**
     * constructs a GameState where each colour is scored using its own
     * evaluator, for pitting two evaluators against each other directly
     * @param player1 Player the first player
     * @param player2 Player the second player
     * @param whiteEvaluator Evaluator used to score White's searches
     * @param blackEvaluator Evaluator used to score Black's searches
     */
    public GameState(Player player1, Player player2, Evaluator whiteEvaluator, Evaluator blackEvaluator) {

        gameBoard = new Board();
        thePlayers[0] = player1;
        thePlayers[1] = player2;
        turn = 0; // Player 1 starts
        this.whiteEvaluator = whiteEvaluator;
        this.blackEvaluator = blackEvaluator;
        this.activeEvaluator = whiteEvaluator;
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
     * this method finds the best possible move given the current game state using iterative deepening.
     * When several moves tie for the best score, one is picked at random from among them rather than
     * always taking the first, so self-play games don't replay the exact same line every time.
     * <p>
     * Root candidates are searched with a real, narrowing alpha-beta window (each candidate's beta is
     * the best score found so far among its predecessors), and between depth passes the move that won
     * the previous, shallower pass is moved to the front of the candidate list. Together these mean the
     * strongest move tends to get searched first, so later candidates fail low quickly and prune hard -
     * searching them in a weak or random order gets almost none of that benefit. The narrow window means
     * a candidate that merely matches the current best score isn't necessarily an exact value - it can
     * be a bound left over from a cutoff, and the move's true value could actually be lower - so before
     * trusting an apparent tie it's re-searched once with a full window to confirm it's a genuine tie
     * rather than risk randomly choosing a move that only looked as good as the best one.
     * @param maxDepth int the maximum depth of search
     * @param colour Colour to find best move for
     * @return Position3D the best move
     */
    public Position3D findBestMove(int maxDepth, Colour colour) {

        // a position's cached score is only valid under the evaluator that computed it, so
        // switching which evaluator is driving this search invalidates whatever's cached
        Evaluator evaluator = evaluatorFor(colour);
        if (evaluator != activeEvaluator) {
            transpositionTable.clear();
        }
        activeEvaluator = evaluator;

        // any cached position with fewer beads than the game currently has can never be
        // reached again - the game only ever adds beads - so it's safe to drop outright
        // rather than just deprioritize it
        transpositionTable.evictBelowBeadCount(turn);

        Position3D bestMove = null;
        ArrayList<Position3D> currentMoves = getPossibleMoves(colour);

        for (int depth = 1; depth <= maxDepth; depth++) {
            int alpha = NEGATIVE_INFINITY;
            int bestScore = NEGATIVE_INFINITY;
            ArrayList<Position3D> bestMoves = new ArrayList<>();

            for (Position3D move : currentMoves) {
                applyMove(move, colour);
                int score = -negamax(depth - 1, colour.opposite(), NEGATIVE_INFINITY, -alpha);

                if (score == bestScore) {
                    // the narrowed window above can return a bound rather than an exact
                    // value right at this boundary; confirm this is a genuine tie (and
                    // not actually worse) with one full-window re-search before trusting it
                    score = -negamax(depth - 1, colour.opposite(), NEGATIVE_INFINITY, -NEGATIVE_INFINITY);
                }
                undoMove(move, colour);

                if (score > bestScore) {
                    bestScore = score;
                    bestMoves.clear();
                    bestMoves.add(move);
                } else if (score == bestScore) {
                    bestMoves.add(move);
                }
                alpha = Math.max(alpha, bestScore);
            }

            // grab a random winner if there is a tie (needed for self-play games)
            bestMove = bestMoves.get(random.nextInt(bestMoves.size()));

            // try this depth's winner first on the next, deeper pass
            currentMoves.remove(bestMove);
            currentMoves.add(0, bestMove);
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

        if (createsWinAt(move, colour)) {
            setWinner(getPlayer(turn % 2));
        }
        turn++;
    }

    /**
     * checks whether the bead just placed at a position completes a line of 4. A move can
     * only create a NEW win through a line that passes through the cell it was just played
     * on - every other cell is unchanged, so only the (at most 7) lines through that one
     * position need checking, instead of scanning every bead on the board pairwise the way
     * containsLine() does.
     * @param position Position3D the position that was just played
     * @param colour Colour the colour that was just placed there
     * @return boolean true if that placement completed a line of 4
     */
    private boolean createsWinAt(Position3D position, Colour colour) {

        for (Line line : Line.allLinesThrough(position)) {

            boolean allMatch = true;
            for (int k = 0; k < 4; k++) {
                Position3D p = line.getPosition3D(k);
                if (gameBoard.getBeadAt(p.getRow(), p.getColumn(), p.getHeight()).getColour() != colour) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                return true;
            }
        }
        return false;
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
     * gets the evaluator assigned to a given colour
     * @param colour Colour White or Black
     * @return Evaluator that colour's evaluator
     */
    private Evaluator evaluatorFor(Colour colour) {

        return colour == Colour.White ? whiteEvaluator : blackEvaluator;
    }

    /**
     * evaluates the game state for a given colour, using whichever evaluator is
     * currently active for the search this call is part of (see findBestMove).
     * <p>
     * Precondition: the game must not already be over (getIsOver() must be false).
     * negamax, the only caller, already checks this immediately before reaching its
     * depth-0 leaf case, so re-checking containsLine()/getDrawStatus() here would just
     * repeat two expensive full-board scans that are guaranteed to already be false -
     * applyMove() keeps winner in sync with the board on every move, so getIsOver()
     * being false already means neither colour currently has a line of 4.
     * @param colour Colour of the player
     * @return int score of the game state
     */
    public int evaluate(Colour colour) {

        int score = activeEvaluator.evaluate(colour, gameBoard);
        score -= activeEvaluator.evaluate(colour.opposite(), gameBoard);
        return score;
    }

    /**
     * Negamax algorithm with alpha-beta pruning. Equivalent to minimax, but
     * exploits the fact that evaluate() is antisymmetric (evaluate(White) ==
     * -evaluate(Black)) to always search from the current mover's point of
     * view and negate child scores, instead of keeping separate maximizing
     * and minimizing branches.
     * @param depth int the depth of the search
     * @param toMove Colour whoever's turn it is to move at this node
     * @param alpha int the alpha value for pruning
     * @param beta int the beta value for pruning
     * @return int score, from toMove's point of view
     */
    public int negamax(int depth, Colour toMove, int alpha, int beta) {

        if (getIsOver()) {
            if (winner != null) {
                return winner.getColour().equals(toMove) ? 100000 - depth : -100000 + depth;
            }
            return 0;
        }

        long positionHash = TranspositionTable.withSideToMove(TranspositionTable.hash(gameBoard), toMove);
        Integer cached = transpositionTable.get(positionHash, depth, alpha, beta);
        if (cached != null) {
            return cached;
        }

        if (depth == 0) {
            int score = evaluate(toMove);
            transpositionTable.put(positionHash, depth, score, TranspositionTable.Flag.EXACT, turn);
            return score;
        }

        int alphaOrig = alpha;
        int betaOrig = beta;
        ArrayList<Position3D> moves = getPossibleMoves(toMove);

        int best = NEGATIVE_INFINITY;
        for (Position3D move : moves) {
            applyMove(move, toMove);
            int score = -negamax(depth - 1, toMove.opposite(), -beta, -alpha);
            undoMove(move, toMove);
            best = Math.max(best, score);
            alpha = Math.max(alpha, best);
            if (alpha >= beta) break;
        }

        transpositionTable.put(positionHash, depth, best, classify(best, alphaOrig, betaOrig), turn);
        return best;
    }

    /**
     * classifies a freshly searched score against the alpha-beta window it
     * was searched under, so the transposition table knows whether the
     * score is exact or only a bound
     * @param score int the score negamax settled on
     * @param alpha int the alpha bound at the start of the search
     * @param beta int the beta bound at the start of the search
     * @return TranspositionTable.Flag how the score should be trusted later
     */
    private TranspositionTable.Flag classify(int score, int alpha, int beta) {

        if (score <= alpha) {
            return TranspositionTable.Flag.UPPER;
        }
        if (score >= beta) {
            return TranspositionTable.Flag.LOWER;
        }
        return TranspositionTable.Flag.EXACT;
    }

    /**
     * gets the number of positions currently cached in this game's transposition table -
     * an instrumentation hook, not used by gameplay itself, for measuring how memory use
     * grows with search depth (the table has no eviction policy)
     * @return int the number of cached entries
     */
    public int getTranspositionTableSize() {

        return transpositionTable.size();
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