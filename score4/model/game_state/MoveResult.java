package score4.model.game_state;

import score4.model.game_state.board.Position3D;

public class MoveResult {

    public Position3D bestMove;
    public int score;

    public MoveResult(Position3D bestMove, int score) {
        this.bestMove = bestMove;
        this.score = score;
    }
}
