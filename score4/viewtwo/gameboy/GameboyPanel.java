package score4.viewtwo.gameboy;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import score4.model.player.Player;
import score4.viewtwo.gameboy.borderpanel.BottomPanel;
import score4.viewtwo.gameboy.borderpanel.LeftPanel;
import score4.viewtwo.gameboy.borderpanel.RightPanel;
import score4.viewtwo.gameboy.borderpanel.TitlePanel;
import score4.viewtwo.gameboy.gamepanel.*;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a GameboyPanel class this is the main panel for the game
 * it contains the game screen and the title bar and the bottom bar
 * and the left and right side panels
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class GameboyPanel extends JPanel{

    private final BottomPanel bp = new BottomPanel();
    private final TitlePanel tp = new TitlePanel();
    private final LeftPanel lp = new LeftPanel();
    private final RightPanel rp = new RightPanel();
    private final GamePanel gp;   /* game screen  */

    private final JLabel statusLabel = new JLabel(" ");
    private final Runnable onBack;

    /**
     * constructs a GameboyPanel
     * @param player1 Player white
     * @param player2 Player black
     * @param onBack Runnable invoked when the back button is pressed to
     *               return to the main menu
     */
    public GameboyPanel(Player player1, Player player2, Runnable onBack){

        super();
        this.onBack = onBack;
        gp = new GamePanel(player1, player2, this::setStatus);
        setLayout(new BorderLayout());
        initialize();
    }

    /**
     * creates all the stuff inside the panel
     */
    public final void initialize(){

        // create bottom bar with status message and back button
        add(bp, BorderLayout.SOUTH);

        // create title bar
        add(tp, BorderLayout.NORTH);

        // create left side frame
        add(lp, BorderLayout.WEST);

        // create panel4 right side frame
        add(rp, BorderLayout.EAST);

        // create game screen
        add(gp, BorderLayout.CENTER);

        statusLabel.setForeground(Color.WHITE);
        bp.add(statusLabel);

        JButton backButton = new JButton("Back");
        bp.add(backButton);
        backButton.addActionListener(e -> onBack.run());

        setVisible(true);
    }

    /**
     * sets the status message shown at the bottom of the screen, e.g. to
     * announce a win or a draw
     * @param text String the message to show
     */
    public void setStatus(String text){

        statusLabel.setText(text);
    }

    /**
     * This method gets the GamePanel
     * @return GamePanel the game screen
     */
    public GamePanel getGamePanel(){

        return this.gp;
    }
}
