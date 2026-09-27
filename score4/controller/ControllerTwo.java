package score4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import score4.model.game_state.GameState;
import score4.model.game_state.board.Board;
import score4.model.game_state.board.Peg;
import score4.model.game_state.board.Position3D;
import score4.model.player.Bead;
import score4.model.player.Colour;
import score4.model.player.HumanPlayer;
import score4.viewtwo.gameboy.gamepanel.GamePanel;
import score4.viewtwo.gameboy.gamepanel.gamepanelcomponents.BlackBeadComponent;
import score4.viewtwo.gameboy.gamepanel.gamepanelcomponents.WhiteBeadComponent;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Controller class that handles the game logic and
 * communication between the model and the view for the button-based
 * (view two) UI. Each of the 16 peg buttons fires an actionCommand of
 * "0".."15"; clicking one plays a bead on whichever peg it represents
 * for whoever's turn it currently is, exactly the way typing a move and
 * pressing Enter does in ControllerOne, just without needing to type
 * board notation.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class ControllerTwo implements ActionListener, GameboyController {

    private final GameState gameState;

    private final GamePanel gamePanel;

    private final Consumer<String> onStatus;

    /**
     * constructs a controller for a given GamePanel
     * @param gp GamePanel the button-based game screen
     * @param gs GameState the game state to drive
     * @param onStatus Consumer<String> called with a message when the game ends
     */
    public ControllerTwo(GamePanel gp, GameState gs, Consumer<String> onStatus) {

        gamePanel = gp;
        gameState = gs;
        this.onStatus = onStatus;
    }

    /**
     * gets the game state
     * @return GameState the game state
     */
    @Override
    public GameState getGameState() {

        return gameState;
    }

    /**
     * handles a peg button click: places a bead for whoever's turn it is on
     * the peg that button represents, then - in Human vs AI games - lets the
     * AI reply immediately, the same way ControllerOne's Enter button does
     * @param e ActionEvent the button click, whose action command ("0".."15")
     *          identifies which peg was clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameState.getIsOver()) {
            return;
        }
        if (!(gameState.getTurn() instanceof HumanPlayer)) {
            return; // not a human's turn to be clicking buttons
        }

        // buttons are laid out in 4 groups of 4 (see GamePanel's button bounds):
        // each group of 4 consecutive indices is one column, varying row within it
        int index = Integer.parseInt(e.getActionCommand());
        int row = index % 4;
        int col = index / 4;

        Colour movingColour = gameState.getTurn().getColour();
        Board board = gameState.getBoard();
        Peg peg = board.getPeg(row, col);

        if (peg.isFull()) {
            return; // clicked a peg that's already full - ignore
        }

        applyAndShowMove(new Position3D(row, col, peg.getNextHeight()), movingColour);

        if (checkGameOver(movingColour)) {
            return;
        }

        if (gameState.isAI()) {

            Colour aiColour = gameState.getTurn().getColour();
            Position3D aiMove = gameState.findBestMove(4, aiColour);
            applyAndShowMove(aiMove, aiColour);
            checkGameOver(aiColour);
        }
    }

    /**
     * applies a move to the model and places the matching bead in the view
     * @param move Position3D the move to apply
     * @param colour Colour the colour making the move
     */
    private void applyAndShowMove(Position3D move, Colour colour) {

        gameState.applyMove(move, colour);

        if (colour == Colour.White) {

            WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
            wBead.setBead(move);
        } else {

            BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
            bBead.setBead(move);
        }
        gamePanel.update();
    }

    /**
     * checks whether the move that was just made ended the game, reporting
     * the result via onStatus if so
     * @param justMoved Colour the colour that just moved
     * @return boolean true if the game is now over
     */
    private boolean checkGameOver(Colour justMoved) {

        if (GameState.containsLine(Bead.getTheBeads(), justMoved)) {

            onStatus.accept(justMoved + " Wins! Game Over");
            return true;
        }
        if (gameState.getDrawStatus()) {

            onStatus.accept("Draw! Game Over");
            return true;
        }
        return false;
    }
}
