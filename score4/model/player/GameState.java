package score4.model.player;
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
public class GameState {

    boolean isOver; // game is over
    boolean isDraw;
    int winner;
    int turn; // 0 = white, 1 = black
    int[][][] boardHistory;
    int[][][] board;
    int[] lastMove;
    int moveCount;
    private final int maxMoves = 64;
    private final int maxTurns = 32;
    private final int maxTurnsPerMove = 1;

    public GameState() {
        isOver = false;
        isDraw = false;
        winner = -1;
        turn = 0;
        boardHistory = new int[maxTurns][maxMoves][2];
        board = new int[4][4][2];
        lastMove = new int[2];
        moveCount = 0;
    }

    public void setGameOver(boolean isOver) {
        this.isOver = isOver;
    }

    public boolean isGameOver() {
        return isOver;
    }

    public void setDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }
}
