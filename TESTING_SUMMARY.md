# Score4 — Testing Summary

Summary of all testing, benchmarking, and self-play experiments performed this session. Organized by topic area, with methodology and results for each.

---

## 1. AI vs AI mode hookup

- **Fix**: AI vs AI logic existed in `ControllerOne` but was dead code, gated behind a text-field-non-empty check meant for human input. Replaced with a `javax.swing.Timer` that drives moves automatically.
- **Testing**: build verification only (`javac` compile), manual reasoning about the Timer lifecycle. No automated test — first real bug surfaced by the *next* item.

## 2. Game-state-not-cleared bug (Back button)

- **Symptom reported**: after using Back and starting a new game, the AI would declare a winner after only a few beads, with no real line present.
- **Root cause found**: `Bead.theBeads` is a `static final ArrayList` shared for the whole JVM session. Every `new Peg()` (created inside `Board()`) appends placeholder beads to it, and it was never cleared between games — stale beads from the previous game were still being scanned by `containsLine()`.
- **Fix**: `Bead.clearBeads()` called at the top of both `Board()` constructors.
- **Testing**: manual code trace confirming `Board()` is the single point where every new game starts; verified by re-reading the win-check logic. No regression test written at the time (later covered by the explicit win-detection test in §7).

## 3. Minimax → Negamax + Transposition Table

- **Motivation**: AI vs AI was slow (600ms timer tick was shorter than the search itself in many cases).
- **Baseline benchmark**: `findBestMove(4, colour)` on a fresh game, timed per move.
  ```
  ply 0: 449ms   ply 1: 767ms   ply 2: 1975ms   ply 3: 2263ms
  ply 4: 2048ms  ply 5: 1572ms  ...
  ```
- **Transposition table** (Zobrist hashing, `EXACT`/`LOWER`/`UPPER` bound flags): re-ran the same benchmark — **identical move sequence**, ~25–35% faster (e.g. 1975ms → 1295ms).
- **Negamax conversion**: rewrote `minimax` as `negamax` (single recursive branch instead of two mirrored ones). Verified via:
  - Move-sequence comparison against the prior minimax+TT run — **byte-for-byte identical**.
  - Confirms the refactor preserved behavior exactly.
- **Random tie-breaking** (so self-play doesn't replay the same game every time): verified by running 3×3 batches of games and diffing move sequences — went from *always identical* openings to genuinely varied lines across runs.

## 4. Root-level alpha-beta + move ordering

- **Finding**: `findBestMove`'s root loop searched every candidate with a full `(-∞, +∞)` window — no pruning ever happened between sibling root moves, so reordering candidates (trying last depth's best move first) was a no-op.
- **Fix**: real alpha accumulation across root siblings, each subsequent candidate searched with a narrowing window; reorder winner-to-front between iterative-deepening passes.
- **Correctness risk found and fixed during testing**: a narrowed-window search can return a *bound* rather than an exact value right at the tie boundary — a move could look tied with the best without truly being tied. Fixed by re-searching apparent ties once with a full window before trusting them.
- **Verification — independent brute-force cross-check**: for 5 full game trials (50 positions total), computed the true best score for every candidate move via an unnarrowed full-window search, and confirmed `findBestMove`'s chosen move always matched the brute-force optimum exactly. **All 50/50 passed.**
- **Speed result**: per-move search time dropped from ~1.2–2.3s to ~0.1–1s (2–4x) on top of the earlier TT gain.

## 5. Self-play data mining: corner/center bias investigation

Triggered by the observation that AI vs AI games kept opening the same two corners, and the border filled faster than the middle.

- **Structural line-degree analysis** (`Line.allLinesThrough` per cell): confirmed corners and center columns are *structurally* equal in total line count (22 lines each across their 4 heights) — but corners peak (7 lines) at height 0, immediately playable, while centers peak (7 lines) at heights 1–2, which require an unlocking move first. Edges are flat at 16 total / 4 per height. This fully explains "border fills first" as rational play, not a bug.
- **Opening-move distribution test** (30 fresh trials, depth 4): White's first move was `A1` in **30/30** trials, despite all 4 corners being provably symmetric — a red flag.
- **Root-cause investigation** (multiple targeted debug harnesses):
  - Confirmed via isolated fresh-`GameState`-per-corner searches that the asymmetry was real, not a transposition-table artifact.
  - Traced it to `AIPlayer.countPotentialLines`: the high-value-position `+5` bonus was applied *inside* the line-scanning loop before the line's `blocked` status was fully known — a line's bonus could "leak through" if the blocking bead happened to be scanned late, but not if scanned early. Since `A1` always has the lowest array index, it systematically benefited.
- **Fix**: defer the bonus until the line is confirmed unblocked, matching how the base score is already gated.
- **Verification**:
  - Re-ran the depth 1–4 exact-score comparison across all 4 corners: **perfectly symmetric at every depth** post-fix (e.g. depth 2: all four corners score exactly `0`, vs. `5, -5, -5, -5` before).
  - Re-ran the opening-distribution test (40 trials): roughly uniform split (`7/10/11/12`) instead of `30/0/0/0`.
  - Re-ran the brute-force correctness check — still passed.

## 6. "Gift" dynamics on center pegs

- **User hypothesis**: placing a center peg's lower bead might just hand the opponent the more valuable upper bead.
- **Test**: instrumented 15 full self-play games (depth 4), tracking every center-peg height transition (who played it, who played the next height, and the ply gap).
  ```
  h0->h1: gifted 67.8% (avg gap 2.75 plies)   kept 32.2% (avg gap 8.42 plies)
  h1->h2: gifted 73.5% (avg gap 1.78 plies)   kept 26.5% (avg gap 6.15 plies)
  h2->h3: gifted 60.9% (avg gap 8.00 plies)   kept 39.1% (avg gap 13.78 plies)
  ```
- **Corner mirror test** (predicted by a "net exposure" model: own-value minus what you hand the opponent): corner h2→h3 predicted worst case, confirmed at **96.5% gifted, 2.38-ply average gap** — the fastest, most decisive snipe of any transition measured.
- **Conclusion**: confirmed empirically on both sides of the board; timing (gap length) tracked the net-exposure model more reliably than raw gift-rate, which is also modulated by how many alternative moves exist elsewhere at that stage of the game.

## 7. Deep-search value of corners vs. centers

- **Test**: exact full-window comparison of `A1` (corner) vs `B2` (center) as White's opening move, at increasing search depth.
  ```
  depth 2: gap=38    depth 6: gap=54
  depth 3: gap=38    depth 7: gap=24
  depth 4: gap=56    depth 8: gap=50
  depth 5: gap=84    depth 9: gap=5   (467s to compute, before the speed fixes)
  ```
- **Finding**: the corner-vs-center gap narrows substantially with depth (down to 5 at depth 9), suggesting the shallow-search center-aversion is partly a horizon artifact rather than a deep truth of the game.
- **Re-run after the speed fixes** (§10): depths 8–9 reproduced **exactly** the same scores (50, then 5) — confirms the speed work changed nothing about search correctness — but depth 9 now took **25s instead of 467s** (~19x). Depth 10 then hit `OutOfMemoryError` (the transposition table has no eviction policy); not fixed, just flagged as a limit.

## 8. Pluggable evaluator architecture (Classic vs. Practical)

- Built `Evaluator` interface, `ClassicEvaluator` (unchanged behavior, verified identical), and `PracticalEvaluator` (discounts the high-value bonus by how many of a position's lines are still live, plus tunable weights).
- **Live-line discount premise test**: measured live (unblocked) line count through a corner cell at height 0 (game start) vs. height 3 (about to be played), over 20 games.
  ```
  height-0: avg 7.00 (always — nothing's happened yet)
  height-3: avg 4.50, histogram {0:1, 1:4, 2:4, 3:3, 4:13, 5:31, 6:16}
  ```
  Confirms the static "7 lines" assumption overvalues a position by ~36% on average by the time it's actually played.
- **Architecture correctness testing** (3 modes): brute-force cross-check passed for (a) default Classic-only play, (b) Practical self-play (both sides same evaluator), and (c) cross-comparison (different evaluator per colour) — including a targeted test that the transposition table correctly invalidates itself when the active evaluator switches between colours, so cached scores from one evaluator are never reused by the other.
- **No regression**: default-play timing unaffected by the new evaluator indirection.

## 9. Evaluator comparison experiments

- **First cross-comparison** (depth 4, 12 games, colours alternated): Classic won 10/12.
- **"Should Classic always beat Practical at depth 5?"** — re-tested at depth 5 (8 games): result was **8/8 determined purely by who played White**, split exactly 4–4 between evaluators. First-move advantage fully swamped any evaluator signal at this depth — pooled win/loss across colours is not a valid comparison method at depth 5.
- **Mirror-matched comparison** (colour-controlled win rates, depths 4/6/7, 12 games each):
  ```
  depth 4: Practical 0/12 total (loses every game, both colours)
  depth 6: Practical 3/6 as White (vs Classic 2/6), 4/6 as Black (vs Classic 3/6) — edge to Practical
  depth 7: Practical 5/6 as White (vs Classic 1/6), 5/6 as Black (vs Classic 1/6) — strong Practical win
  ```
  Clear depth-dependent trend: Classic dominates shallow search, Practical overtakes it by depth 6–7 once colour is controlled for.
- **Practical-evaluator speed bug found via this testing**: `PracticalEvaluator` was noticeably slower than Classic per-move in GUI play. Traced to `scaledBonus()` recomputing `Line.allLinesThrough(pos).size()` (an O(76) scan) uncached on every call, up to 7x redundant per high-value bead. Fixed by caching the line list permanently per position (pure board geometry, never changes). Re-benchmarked: Practical (614ms/move) no longer slower than Classic (711ms/move) — within noise.

## 10. Search-speed audit — the big one

Asked to look for unnecessary loops/computations. Established a depth-5 baseline (**avg 2951ms/move**), then applied and individually verified five fixes:

1. **`Line.getPosition3D(k)`** allocated a new `Position3D` on every call (300+ times per leaf evaluation) instead of returning the already-immutable stored point.
2. **`applyMove`'s win-check** called `containsLine()`, an O(64²)-ish full-board pairwise scan, after *every* move applied anywhere in the search tree. Replaced with `createsWinAt()`, checking only the ≤7 lines through the position just played (a win can only form through that cell, since nothing else changed).
3. **`evaluate()`'s own win/draw checks** were provably dead code — its only caller (`negamax`'s leaf case) already guarantees `!getIsOver()` one line earlier. Removed, with the precondition documented instead.
4. **High-value-position lookup** was an O(16) `ArrayList.contains()` linear scan on nearly every bead checked; replaced with an O(1) `boolean[4][4][4]` array lookup.
5. **`Position3D.hashCode()`** used `Objects.hash(...)` (boxes 3 ints, allocates a varargs array) on every call; replaced with a direct bit-pack (`row<<4 | col<<2 | height`), safe since each coordinate is 0–3.

- **Correctness verification**:
  - Brute-force cross-check (5 trials × 10 plies): all passed.
  - **Explicit win-detection test**: built a real vertical 4-in-a-row across 7 moves and confirmed `getIsOver()`/`getWinner()` still fire correctly with the new `createsWinAt` logic.
- **Result**: **2951ms → 82ms average per move at depth 5 — a ~36x speedup**, almost entirely from fix #2 (the win-check was the dominant cost by a wide margin, not the leaf evaluation).

## 11. ControllerTwo / `viewtwo` (button-based UI) build

Rebuilt from a non-functional prototype into a separate, working application (`GameBoardTwo`), per explicit scope: Human vs Human + Human vs AI only, no AI vs AI, fully independent from the text-box app.

- **Button → board coordinate mapping** was reverse-engineered from the existing (correct) pixel layout and bead-placement formulas, rather than guessed: confirmed `row = index % 4, col = index / 4` by cross-checking screen coordinates against `WhiteBeadComponent`'s known placement formula for several buttons.
- **Headless smoke tests** (simulating real clicks through the actual Swing component tree, no mocking):
  - **Human vs AI flow**: clicked a peg button → white bead count incremented, AI replied automatically in the same handler → black bead count incremented. Clicked the same peg again → correctly stacked to the next height, AI replied again. All counts matched expectations exactly.
  - **Human vs Human flow**: clicked two different pegs → both colours incremented once each, **no** auto-AI-response fired (correctly, since neither side is AI).
  - **Back button**: fired its handler → menu was confirmed restored (Single Player / multi-Player buttons present again).
  - **Win detection**: programmatically stacked 4 White beads on one peg (interleaved with harmless Black moves) → status label correctly read `"White Wins! Game Over"`.
- **Full-project compile check**: the entire project (`view`, `viewtwo`, `main`, previously-excluded `ControllerTwo`) compiles clean together for the first time this session.

---

## Testing methodology notes

A few patterns were reused throughout and are worth naming explicitly, since they're the actual mechanism that caught real bugs (the corner-bias bug and the tie-detection correctness gap were both found *because* of these, not by inspection):

- **Independent brute-force cross-check**: whenever the search algorithm changed, an unrelated, deliberately-naive full-window search was used to compute the true best score for comparison, rather than trusting the optimized path to check itself.
- **Symmetry checks**: exploiting known structural symmetries (e.g. all 4 corners must score identically from an empty board) as a cheap, strong correctness signal — deviations pointed directly at real bugs twice.
- **Self-play data mining over single-position checks**: several findings (gift dynamics, corner/center fill timing, opening bias) only became visible by aggregating many full games, not by reasoning about one position in isolation.
- **Headless GUI smoke testing**: simulating actual Swing button clicks through the real component tree (not mocks) to verify end-to-end wiring, including one case (the original AI-selection smoke test) that correctly surfaced a real `java.awt.HeadlessException` limitation of this sandbox rather than a code bug.
