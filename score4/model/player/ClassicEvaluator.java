package score4.model.player;

import score4.model.game_state.board.Board;

/**
 * This file is part of a Score4 game
 *
 * <p> The original board-scoring heuristic (AIPlayer.countPotentialLines),
 * wrapped as an Evaluator so it can be swapped for alternatives. Grants a
 * flat bonus for every high-value bead regardless of how many of that
 * bead's lines are still actually open - see PracticalEvaluator for an
 * alternative that accounts for this.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class ClassicEvaluator implements Evaluator {

    @Override
    public int evaluate(Colour colour, Board board) {

        return AIPlayer.countPotentialLines(colour, board);
    }
}
