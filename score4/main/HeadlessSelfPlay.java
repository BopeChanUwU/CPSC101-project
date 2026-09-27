package score4.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import score4.model.game_state.GameState;
import score4.model.game_state.board.Position3D;
import score4.model.player.AIPlayer;
import score4.model.player.ClassicEvaluator;
import score4.model.player.Colour;
import score4.model.player.Evaluator;
import score4.model.player.PracticalEvaluator;

/**
 * This file is part of a Score4 game
 *
 * <p> A headless, command-line entry point for running large batches of AI
 * vs AI self-play games unattended (e.g. overnight) and recording each
 * game's outcome and full move order to a CSV file for later analysis.
 * Prompts for the number of games, search depth, and which evaluator
 * (Classic or Practical) plays each colour, then plays every game to
 * completion with no GUI involved.
 * <p>
 * Each row of the output file is one complete game: game id, timestamp,
 * depth, the evaluator used by each colour, how many plies the game took,
 * the winner (or Draw), how long the game took to compute, and the full
 * move order (semicolon-separated Position3D notation, e.g. "A1(1);D4(1);...").
 * The file is opened in append mode and flushed after every game, so an
 * interrupted run still leaves every completed game intact, and a single
 * failed game (e.g. an out-of-memory search at a very high depth) is
 * recorded and skipped rather than losing the rest of the batch.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class HeadlessSelfPlay {

    private static final int MAX_PLIES = 64;
    private static final int MAX_RECOMMENDED_DEPTH = 9;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int numGames = promptInt(scanner, "Number of games to run: ", 1, Integer.MAX_VALUE);
        int depth = promptInt(scanner, "Search depth (used by both sides): ", 1, 20);
        if (depth > MAX_RECOMMENDED_DEPTH) {
            System.out.println("  warning: depths above " + MAX_RECOMMENDED_DEPTH
                + " have been observed to run out of memory partway through a game"
                + " (the transposition table has no eviction policy) - proceeding anyway");
        }
        Evaluator whiteEvaluator = promptEvaluator(scanner, "White's evaluator (classic/practical): ");
        Evaluator blackEvaluator = promptEvaluator(scanner, "Black's evaluator (classic/practical): ");
        String outputPath = promptOutputPath(scanner);

        System.out.println();
        System.out.println("Running " + numGames + " game(s) at depth " + depth
            + " (White=" + evaluatorName(whiteEvaluator) + ", Black=" + evaluatorName(blackEvaluator) + ")");
        System.out.println("Writing results to " + outputPath);
        System.out.println();

        Path path = Path.of(outputPath);
        boolean fileIsNew = !Files.exists(path) || Files.size(path) == 0;

        int whiteWins = 0, blackWins = 0, draws = 0, failures = 0;
        long batchStart = System.nanoTime();

        try (PrintWriter out = new PrintWriter(new FileWriter(outputPath, true))) {

            if (fileIsNew) {
                out.println("game_id,timestamp,depth,white_evaluator,black_evaluator,plies,winner,elapsed_ms,moves");
                out.flush();
            }

            for (int g = 1; g <= numGames; g++) {

                long gameStart = System.nanoTime();
                try {
                    GameResult result = playOneGame(depth, whiteEvaluator, blackEvaluator);
                    long elapsedMs = (System.nanoTime() - gameStart) / 1_000_000;

                    writeRow(out, g, depth, whiteEvaluator, blackEvaluator, result, elapsedMs);
                    out.flush();

                    if ("White".equals(result.winner)) whiteWins++;
                    else if ("Black".equals(result.winner)) blackWins++;
                    else draws++;

                    System.out.printf("game %d/%d: %s in %d plies (%.1fs)%n",
                        g, numGames, result.winner, result.plies, elapsedMs / 1000.0);

                } catch (Throwable t) {
                    // a single bad game (e.g. an OOM at too high a depth) shouldn't cost
                    // the rest of an unattended overnight batch - log it and move on
                    long elapsedMs = (System.nanoTime() - gameStart) / 1_000_000;
                    failures++;
                    out.printf("%d,%s,%d,%s,%s,,ERROR:%s,%d,\"\"%n",
                        g, timestamp(), depth, evaluatorName(whiteEvaluator), evaluatorName(blackEvaluator),
                        t.getClass().getSimpleName(), elapsedMs);
                    out.flush();
                    System.out.println("game " + g + "/" + numGames + ": FAILED (" + t.getClass().getSimpleName() + ")");
                }
            }
        }

        long totalMs = (System.nanoTime() - batchStart) / 1_000_000;
        System.out.println();
        System.out.println("=== batch complete ===");
        System.out.println("White wins: " + whiteWins + "   Black wins: " + blackWins
            + "   Draws: " + draws + "   Failed: " + failures);
        System.out.printf("Total time: %.1fs (avg %.1fs/game)%n", totalMs / 1000.0, totalMs / 1000.0 / numGames);
    }

    /**
     * plays one complete AI vs AI game and collects its result
     * @param depth int search depth for both sides
     * @param whiteEvaluator Evaluator used to score White's searches
     * @param blackEvaluator Evaluator used to score Black's searches
     * @return GameResult the completed game's outcome
     */
    private static GameResult playOneGame(int depth, Evaluator whiteEvaluator, Evaluator blackEvaluator) {

        GameState gs = new GameState(new AIPlayer(1), new AIPlayer(2), whiteEvaluator, blackEvaluator);
        Colour[] turnOrder = {Colour.White, Colour.Black};
        StringBuilder moves = new StringBuilder();

        int ply;
        for (ply = 0; ply < MAX_PLIES && !gs.getIsOver(); ply++) {

            Colour c = turnOrder[ply % 2];
            Position3D move = gs.findBestMove(depth, c);
            gs.applyMove(move, c);

            if (ply > 0) {
                moves.append(';');
            }
            moves.append(move);
        }

        String winner = (gs.getIsOver() && ply < MAX_PLIES) ? gs.getWinner().getColour().toString() : "Draw";

        return new GameResult(ply, winner, moves.toString());
    }

    private static void writeRow(PrintWriter out, int gameId, int depth, Evaluator whiteEvaluator,
            Evaluator blackEvaluator, GameResult result, long elapsedMs) {

        out.printf("%d,%s,%d,%s,%s,%d,%s,%d,\"%s\"%n",
            gameId, timestamp(), depth, evaluatorName(whiteEvaluator), evaluatorName(blackEvaluator),
            result.plies, result.winner, elapsedMs, result.moves);
    }

    private static String timestamp() {

        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    private static String evaluatorName(Evaluator evaluator) {

        return evaluator instanceof PracticalEvaluator ? "Practical" : "Classic";
    }

    private static int promptInt(Scanner scanner, String prompt, int min, int max) {

        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("  please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("  please enter a whole number");
            }
        }
    }

    private static Evaluator promptEvaluator(Scanner scanner, String prompt) {

        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.startsWith("c")) {
                return new ClassicEvaluator();
            }
            if (line.startsWith("p")) {
                return new PracticalEvaluator();
            }
            System.out.println("  please enter 'classic' or 'practical'");
        }
    }

    private static String promptOutputPath(Scanner scanner) {

        String defaultName = "selfplay_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".csv";
        System.out.print("Output file [" + defaultName + "]: ");
        String line = scanner.nextLine().trim();
        return line.isEmpty() ? defaultName : line;
    }

    /**
     * the outcome of one completed self-play game
     */
    private static class GameResult {

        final int plies;
        final String winner;
        final String moves;

        GameResult(int plies, String winner, String moves) {

            this.plies = plies;
            this.winner = winner;
            this.moves = moves;
        }
    }
}
