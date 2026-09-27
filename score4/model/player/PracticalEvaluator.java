package score4.model.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import score4.model.game_state.board.Board;
import score4.model.game_state.board.Line;
import score4.model.game_state.board.Position3D;

/**
 * This file is part of a Score4 game
 *
 * <p> An alternative to ClassicEvaluator with two changes:
 * <p>
 * 1. The high-value position bonus is scaled by how many of that position's
 * lines are still actually live (unblocked) on the current board, instead
 * of being granted in full regardless of context. A corner about to be
 * topped off late in the game typically only has a few of its 7 lines
 * still open - measured at ~4.5 on average in self-play, not 7 - so
 * granting the full bonus every time overvalues it.
 * <p>
 * 2. The base per-in-a-row scores are constructor parameters instead of
 * hardcoded, so different weightings can be tried and compared against
 * each other or against ClassicEvaluator.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class PracticalEvaluator implements Evaluator {

    private final int oneInARowScore;
    private final int twoInARowScore;
    private final int threeInARowScore;
    private final int threeInARowImmediateScore;
    private final int fourInARowScore;
    private final int highValueBonusPerLine;

    // which lines pass through a given position is pure board geometry - it never changes
    // as the game is played - so it's cached once for the life of the JVM instead of being
    // recomputed with a fresh O(76)-line scan every time it's needed. Shared by scaledBonus()
    // (which only needs the count) and countLiveLines() (which needs to iterate the lines).
    private static final Map<Position3D, ArrayList<Line>> LINES_THROUGH_CACHE = new HashMap<>();

    private static ArrayList<Line> linesThrough(Position3D pos) {

        return LINES_THROUGH_CACHE.computeIfAbsent(pos, Line::allLinesThrough);
    }

    /**
     * constructs a PracticalEvaluator using the same base weights as
     * ClassicEvaluator, so the only behavioural difference from the default
     * constructor is the live-line discount on the high-value bonus
     */
    public PracticalEvaluator() {

        this(1, 25, 150, 5000, 10000, 5);
    }

    /**
     * constructs a PracticalEvaluator with custom weights, for experimenting
     * with how different values affect play
     * @param oneInARowScore int score for an unblocked line with 1 bead
     * @param twoInARowScore int score for an unblocked line with 2 beads
     * @param threeInARowScore int score for an unblocked line with 3 beads that isn't an immediate threat
     * @param threeInARowImmediateScore int score for an unblocked line with 3 beads where the 4th spot is immediately playable
     * @param fourInARowScore int score for a completed line (shouldn't normally be reached - a win ends the game first)
     * @param highValueBonusPerLine int the full bonus per line for a high-value bead, before the live-line discount is applied
     */
    public PracticalEvaluator(int oneInARowScore, int twoInARowScore, int threeInARowScore,
            int threeInARowImmediateScore, int fourInARowScore, int highValueBonusPerLine) {

        this.oneInARowScore = oneInARowScore;
        this.twoInARowScore = twoInARowScore;
        this.threeInARowScore = threeInARowScore;
        this.threeInARowImmediateScore = threeInARowImmediateScore;
        this.fourInARowScore = fourInARowScore;
        this.highValueBonusPerLine = highValueBonusPerLine;
    }

    @Override
    public int evaluate(Colour colour, Board board) {

        int totalLines = 0;
        Map<Position3D, Integer> liveLineCache = new HashMap<>();

        for (Line line : Line.allLines()) {
            int inArow = 0;
            boolean blocked = false;
            int highValueBonus = 0;
            Position3D emptyPos = null;

            for (int k = 0; k < 4; k++) {
                Position3D pos = line.getPosition3D(k);
                Colour c = board.getBeadAt(pos.getRow(), pos.getColumn(), pos.getHeight()).getColour();

                if (c.equals(colour.opposite())) {
                    blocked = true;
                    break;
                } else if (c.equals(colour)) {
                    inArow++;
                    if (AIPlayer.isHighValuePosition(pos)) {
                        highValueBonus += scaledBonus(pos, board, liveLineCache);
                    }
                } else {
                    emptyPos = pos;
                }
            }

            if (!blocked) {
                boolean immediate = inArow == 3 && emptyPos != null &&
                    emptyPos.getHeight() == board.getPeg(emptyPos.getRow(), emptyPos.getColumn()).getNextHeight();

                switch (inArow) {
                    case 1 -> totalLines += oneInARowScore;
                    case 2 -> totalLines += twoInARowScore;
                    case 3 -> totalLines += (immediate ? threeInARowImmediateScore : threeInARowScore);
                    case 4 -> totalLines += fourInARowScore;
                }
                totalLines += highValueBonus;
            }
        }
        return totalLines;
    }

    /**
     * scales the high-value bonus for a position by the fraction of its lines
     * that are still live (not yet blocked by an opposing bead)
     * @param pos Position3D the high-value bead's position
     * @param board Board the current game board
     * @param cache Map a per-evaluate-call cache so a position's live-line count
     *              isn't recomputed once for every line that happens to pass through it
     * @return int the discounted bonus for this position
     */
    private int scaledBonus(Position3D pos, Board board, Map<Position3D, Integer> cache) {

        int totalLinesThroughPos = linesThrough(pos).size();
        int liveLines = cache.computeIfAbsent(pos, p -> countLiveLines(p, board));
        return Math.round(highValueBonusPerLine * (float) liveLines / totalLinesThroughPos);
    }

    /**
     * counts how many of the lines through a position are still winnable by
     * somebody - i.e. don't yet contain beads of both colours
     * @param pos Position3D the position to check
     * @param board Board the current game board
     * @return int the number of still-live lines through pos
     */
    private int countLiveLines(Position3D pos, Board board) {

        int live = 0;
        for (Line line : linesThrough(pos)) {
            boolean hasWhite = false;
            boolean hasBlack = false;

            for (int k = 0; k < 4; k++) {
                Position3D p = line.getPosition3D(k);
                Colour c = board.getBeadAt(p.getRow(), p.getColumn(), p.getHeight()).getColour();
                if (c == Colour.White) hasWhite = true;
                if (c == Colour.Black) hasBlack = true;
            }
            if (!(hasWhite && hasBlack)) live++;
        }
        return live;
    }
}
