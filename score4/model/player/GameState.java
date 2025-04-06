package score4.model.player;

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


}
