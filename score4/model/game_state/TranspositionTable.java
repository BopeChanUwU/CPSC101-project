package score4.model.game_state;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.BiFunction;
import score4.model.game_state.board.Board;
import score4.model.player.Colour;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a TranspositionTable used by GameState's negamax search.
 * Different sequences of moves can lead to the exact same board position
 * (a "transposition"), and this table lets negamax recognize a position it
 * has already searched and reuse that result instead of re-searching the
 * whole subtree again.
 *
 * <p> Positions are identified using Zobrist hashing: every (row, column,
 * height, colour) combination gets its own random 64 bit key, and a board's
 * hash is the XOR of the keys for every bead currently on it. XOR makes the
 * hash cheap to compute. Negamax scores are always relative to whoever is
 * about to move, so that side-to-move colour is folded into the key too -
 * otherwise a score computed for "White to move" could get confused with a
 * score for "Black to move" on the exact same physical board.
 *
 * <p> The board also has real symmetry: all 8 transformations of a square
 * (identity, 3 rotations, 4 reflections) map the game's line structure and
 * high-value positions onto themselves, so a position and its 8 symmetric
 * images are provably equal in value. hash() computes the Zobrist hash of
 * every one of those 8 images and returns the smallest one as the canonical
 * key - so all 8 physically different boards that are really "the same
 * position, rotated or reflected" share one cache entry instead of each
 * paying for their own independent search.
 *
 * <p> Entries also carry the bead count the board had when they were
 * stored. Since a real game only ever adds beads - undoMove during search
 * backtracking always gets paired with a later redo, but a real move never
 * gets un-played - any entry whose bead count is below the current game's
 * actual bead count describes a position that can never be reached again,
 * and is safe to evict outright rather than merely deprioritized.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class TranspositionTable {

    /**
     * describes how a stored score relates to the true minimax value
     */
    public enum Flag {

        EXACT, // the true minimax value for this position
        LOWER, // a fail-high: the true value is at least this score
        UPPER  // a fail-low: the true value is at most this score
    }

    /**
     * one cached search result for a single board position
     */
    private static final class Entry {

        private final int depth;
        private final int score;
        private final Flag flag;
        private final int beadCount;

        private Entry(int depth, int score, Flag flag, int beadCount) {

            this.depth = depth;
            this.score = score;
            this.flag = flag;
            this.beadCount = beadCount;
        }
    }

    // one random key per (row, column, height, colour) cell/colour combination
    private static final long[][][][] ZOBRIST_KEYS = new long[4][4][4][2];

    // the same keys, permuted for each of the board's 8 symmetries - ZOBRIST_KEYS_BY_SYMMETRY[s]
    // is what ZOBRIST_KEYS would be if the whole board were transformed by symmetry s first
    private static final long[][][][][] ZOBRIST_KEYS_BY_SYMMETRY = new long[8][4][4][4][2];

    // salts the hash with which colour is about to move at a given node
    private static final long WHITE_TO_MOVE_KEY;
    private static final long BLACK_TO_MOVE_KEY;

    @SuppressWarnings("unchecked")
    private static final BiFunction<Integer, Integer, int[]>[] SYMMETRIES = new BiFunction[]{
        (BiFunction<Integer, Integer, int[]>) (r, c) -> new int[]{r, c},         // identity
        (BiFunction<Integer, Integer, int[]>) (r, c) -> new int[]{c, 3 - r},     // rotate 90
        (BiFunction<Integer, Integer, int[]>) (r, c) -> new int[]{3 - r, 3 - c}, // rotate 180
        (BiFunction<Integer, Integer, int[]>) (r, c) -> new int[]{3 - c, r},     // rotate 270
        (BiFunction<Integer, Integer, int[]>) (r, c) -> new int[]{3 - r, c},     // flip rows
        (BiFunction<Integer, Integer, int[]>) (r, c) -> new int[]{r, 3 - c},     // flip columns
        (BiFunction<Integer, Integer, int[]>) (r, c) -> new int[]{c, r},         // transpose
        (BiFunction<Integer, Integer, int[]>) (r, c) -> new int[]{3 - c, 3 - r}, // anti-transpose
    };

    static {

        // fixed seed so hashes (and therefore any future debugging of them)
        // are reproducible from run to run
        Random random = new Random(0xC0FFEEL);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                for (int height = 0; height < 4; height++) {
                    for (int colourIndex = 0; colourIndex < 2; colourIndex++) {

                        ZOBRIST_KEYS[row][col][height][colourIndex] = random.nextLong();
                    }
                }
            }
        }

        WHITE_TO_MOVE_KEY = random.nextLong();
        BLACK_TO_MOVE_KEY = random.nextLong();

        // ZOBRIST_KEYS_BY_SYMMETRY[s][row][col][height][colourIndex] is the key that a bead at
        // (row,col,height) contributes when the whole board is viewed through symmetry s - i.e.
        // the key that actually lives at the transformed cell in the untransformed table
        for (int s = 0; s < 8; s++) {
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    int[] transformed = SYMMETRIES[s].apply(row, col);
                    for (int height = 0; height < 4; height++) {
                        for (int colourIndex = 0; colourIndex < 2; colourIndex++) {

                            ZOBRIST_KEYS_BY_SYMMETRY[s][row][col][height][colourIndex] =
                                ZOBRIST_KEYS[transformed[0]][transformed[1]][height][colourIndex];
                        }
                    }
                }
            }
        }
    }

    // when the table is this full, put() sheds the shallowest entries rather than let the
    // table grow without bound - a single very deep search can otherwise exhaust the heap
    // well before any individual entry is stale enough for evictBelowBeadCount() to catch it
    // measured: a depth-9 search from an empty board settles at ~4M entries and ~420MB,
    // comfortably fast and safe - 8M turned out to already be deep into the danger zone
    // (a depth-10 search hit ~5.85M entries and 1.4GB, using GB, before hitting the cap),
    // so this stays close to that measured-safe working set rather than a round number
    private static final int DEFAULT_MAX_ENTRIES = 4_000_000;
    // fraction of the table to reclaim per eviction pass, so a full table isn't re-scanned
    // on every single put() once it's at capacity
    private static final double EVICTION_FRACTION = 0.2;

    private final Map<Long, Entry> table = new HashMap<>();
    private final int maxEntries;

    /**
     * constructs a TranspositionTable with the default entry cap
     */
    public TranspositionTable() {

        this(DEFAULT_MAX_ENTRIES);
    }

    /**
     * constructs a TranspositionTable with a custom entry cap, mainly for testing how
     * eviction behaves at a smaller size than the real default
     * @param maxEntries int the maximum number of entries to hold before evicting
     */
    public TranspositionTable(int maxEntries) {

        this.maxEntries = maxEntries;
    }

    /**
     * computes the canonical Zobrist hash of a board: the smallest of the 8 hashes obtained by
     * viewing the board through each of the square's symmetries, so a position and its rotated
     * or reflected equivalents all resolve to the same cache entry
     * @param board Board the board to hash
     * @return long the canonical Zobrist hash of the given board
     */
    public static long hash(Board board) {

        long[] candidates = new long[8];

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {

                int filled = board.getPeg(row, col).getNextHeight();
                for (int height = 0; height < filled; height++) {

                    Colour colour = board.getBeadAt(row, col, height).getColour();
                    int colourIndex = colourIndex(colour);

                    for (int s = 0; s < 8; s++) {
                        candidates[s] ^= ZOBRIST_KEYS_BY_SYMMETRY[s][row][col][height][colourIndex];
                    }
                }
            }
        }

        long canonical = candidates[0];
        for (int s = 1; s < 8; s++) {
            if (candidates[s] < canonical) {
                canonical = candidates[s];
            }
        }
        return canonical;
    }

    /**
     * gets the Zobrist key for a single occupied cell, under the identity symmetry
     * @param row int row 0-3
     * @param col int column 0-3
     * @param height int height 0-3
     * @param colour Colour White or Black
     * @return long the Zobrist key for that cell/colour
     * @throws IllegalArgumentException if colour is Null
     */
    public static long keyFor(int row, int col, int height, Colour colour) {

        return ZOBRIST_KEYS[row][col][height][colourIndex(colour)];
    }

    private static int colourIndex(Colour colour) {

        return switch (colour) {
            case White -> 0;
            case Black -> 1;
            default -> throw new IllegalArgumentException("cannot hash a Null coloured bead");
        };
    }

    /**
     * folds which colour is about to move into a board hash, so a score
     * found for "White to move" here can never be mistaken for a score
     * found for "Black to move" on the exact same physical board
     * @param boardHash long the hash returned by hash(Board)
     * @param toMove Colour whoever's turn it is to move at this node
     * @return long the side-to-move-aware hash to use as a table key
     */
    public static long withSideToMove(long boardHash, Colour toMove) {

        return boardHash ^ (toMove == Colour.White ? WHITE_TO_MOVE_KEY : BLACK_TO_MOVE_KEY);
    }

    /**
     * looks up a cached search result for a position
     * @param hash long the (side-to-move-aware) hash of the position
     * @param depth int the minimum search depth the caller needs
     * @param alpha int the caller's current alpha bound
     * @param beta int the caller's current beta bound
     * @return the cached score if it can be trusted at this depth and
     *         window, otherwise null
     */
    public Integer get(long hash, int depth, int alpha, int beta) {

        Entry entry = table.get(hash);
        if (entry == null || entry.depth < depth) {

            return null;
        }

        return switch (entry.flag) {
            case EXACT -> entry.score;
            case LOWER -> entry.score >= beta ? entry.score : null;
            case UPPER -> entry.score <= alpha ? entry.score : null;
        };
    }

    /**
     * stores a search result for a position, keeping whichever result was
     * searched deepest if one is already cached for this hash
     * @param hash long the (side-to-move-aware) hash of the position
     * @param depth int the depth this score was searched to
     * @param score int the score that was found
     * @param flag Flag how the score relates to the true minimax value
     * @param beadCount int how many beads were on the board for this position -
     *                  used later to evict entries that have fallen behind the
     *                  game's actual progress, see evictBelowBeadCount()
     */
    public void put(long hash, int depth, int score, Flag flag, int beadCount) {

        Entry existing = table.get(hash);
        if (existing == null || existing.depth <= depth) {

            if (existing == null && table.size() >= maxEntries) {
                evictShallowest((int) (maxEntries * EVICTION_FRACTION));
            }
            table.put(hash, new Entry(depth, score, flag, beadCount));
        }
    }

    /**
     * reclaims space by removing the shallowest-searched entries in the table. A deep entry
     * cost exponentially more to compute than a shallow one - re-deriving an evicted depth-8
     * result means re-searching a whole subtree, while re-deriving a depth-0 result is a
     * single evaluate() call - so when the table has to shed entries to stay within its cap,
     * shedding the cheap-to-recompute ones first is the better trade even though they may be
     * looked up slightly more often.
     * <p>
     * Depth values are a small, bounded range (0..maxDepth), so this uses a histogram to find
     * a depth cutoff rather than sorting the whole table.
     * @param targetRemovals int roughly how many entries to remove
     */
    private void evictShallowest(int targetRemovals) {

        Map<Integer, Integer> countsByDepth = new TreeMap<>();
        for (Entry entry : table.values()) {
            countsByDepth.merge(entry.depth, 1, Integer::sum);
        }

        int cutoff = -1;
        int cumulative = 0;
        for (Map.Entry<Integer, Integer> depthCount : countsByDepth.entrySet()) {
            cumulative += depthCount.getValue();
            cutoff = depthCount.getKey();
            if (cumulative >= targetRemovals) {
                break;
            }
        }

        if (cutoff < 0) {
            return;
        }
        final int depthCutoff = cutoff;
        table.values().removeIf(entry -> entry.depth <= depthCutoff);
    }

    /**
     * removes every entry whose bead count is below the given threshold. A real game only
     * ever adds beads, so once the game's actual bead count has passed a stored entry's bead
     * count, that position can never be reached again - it isn't just stale, it's provably
     * unreachable, so this is always safe to call with the current game's real bead count
     * @param minBeadCount int entries with fewer beads than this are removed
     * @return int how many entries were evicted
     */
    public int evictBelowBeadCount(int minBeadCount) {

        int evicted = 0;
        Iterator<Entry> it = table.values().iterator();
        while (it.hasNext()) {
            if (it.next().beadCount < minBeadCount) {
                it.remove();
                evicted++;
            }
        }
        return evicted;
    }

    /**
     * removes every cached entry
     */
    public void clear() {

        table.clear();
    }

    /**
     * gets the number of positions currently cached
     * @return int the number of entries in the table
     */
    public int size() {

        return table.size();
    }
}
