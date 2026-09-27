package score4.model.player;

import score4.model.game_state.board.Board;

/**
 * This file is part of a Score4 game
 *
 * <p> A pluggable heuristic for scoring how good a board looks for a given
 * colour, used by GameState's negamax search at its leaves. Different
 * implementations can be swapped in to compare how a change in strategy
 * affects actual play - see ClassicEvaluator and PracticalEvaluator.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public interface Evaluator {

    /**
     * scores the current board from the given colour's point of view
     * @param colour Colour to score the board for
     * @param board Board the current game board
     * @return int a heuristic score - higher is better for colour
     */
    int evaluate(Colour colour, Board board);
}
