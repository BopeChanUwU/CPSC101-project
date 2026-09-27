package score4.viewtwo.gameboy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import score4.model.player.AIPlayer;
import score4.model.player.HumanPlayer;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a MenuPanel for the button-based (view two) UI. Offers
 * Single Player (Human vs AI) and multi-Player (Human vs Human) - unlike
 * the text-box UI's menu, there is no AI vs AI mode here, since this app
 * is meant purely as a more user-friendly, click-to-place alternative for
 * human play.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class MenuPanel extends JPanel{

    private final int originalTileSize = 16;// size of each tile
    public final int tileSize = originalTileSize*3;//real tile is 48x48
    private final int MaxScreenCol = 13;
    private final int MaxScreenRow = 30;
    private final int screenWidth = tileSize*MaxScreenCol-1;
    private final int screenHeight = tileSize*MaxScreenRow;

    private final JButton onePlayer = new JButton("Single Player");
    private final JButton twoPlayer = new JButton("multi-Player");

    /**
     * constructs a MenuPanel
     */
    public MenuPanel(){

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setLayout(new GridLayout(7,1));
        initialize();
    }

    /**
     * creates all the stuff inside the panel
     */
    public final void initialize(){

        // wire up button behaviour once; showMainMenu() (re)builds the
        // visible layout and can be called again later, e.g. from a
        // "back to menu" button, without re-registering listeners
        onePlayer.addActionListener((ActionEvent e) -> {

            if(e.getSource() != onePlayer) return;
            GameboyPanel gbp1 = new GameboyPanel(new HumanPlayer(1), new AIPlayer(2), this::showMainMenu);
            MenuPanel.this.removeAll();
            MenuPanel.this.setLayout(new BorderLayout());
            MenuPanel.this.add(gbp1, BorderLayout.CENTER);
            MenuPanel.this.revalidate();
            MenuPanel.this.repaint();
        });

        twoPlayer.addActionListener((ActionEvent e) -> {

            if(e.getSource() != twoPlayer) return;
            GameboyPanel gbp2 = new GameboyPanel(new HumanPlayer(1), new HumanPlayer(2), this::showMainMenu);
            MenuPanel.this.removeAll();
            MenuPanel.this.setLayout(new BorderLayout());
            MenuPanel.this.add(gbp2, BorderLayout.CENTER);
            MenuPanel.this.revalidate();
            MenuPanel.this.repaint();
        });

        setBackground(new Color(73,71,134)); // currently purple
        showMainMenu();
        setVisible(true);
    }

    /**
     * (re)builds the main menu layout; safe to call repeatedly, e.g.
     * to return here from a game screen's back button
     */
    public void showMainMenu(){

        removeAll();
        setLayout(new GridLayout(7,1));

        add(new JLabel("<html><H1>SCORE 4!</H1></html>")); //title label
        for (int i = 0; i < 4; i++) {
            add(new JLabel());
        }

        add(onePlayer);
        add(twoPlayer);

        revalidate();
        repaint();
    }

    public void update() {

        repaint();
    }
}
