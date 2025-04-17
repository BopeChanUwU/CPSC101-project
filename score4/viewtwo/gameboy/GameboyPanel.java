package score4.viewtwo.gameboy;

import java.awt.BorderLayout;
import java.awt.TextField;

import javax.swing.JPanel;

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
    private final GamePanel gp = new GamePanel();   /* game screen  */

    //private final ControllerTwo controller = new ControllerTwo(this);
    public TextField textField = new TextField();

    /**
     * constructs a GameboyPanel
     */
    public GameboyPanel(){

        super();
        setLayout(new BorderLayout());
        initialize();
    }

    /**
     * creates all the stuff inside the panel
     * @param gp GamePanel game view
     */
    public final void initialize(){

        // create bottom bar with enter and input field 
        add(bp, BorderLayout.SOUTH);

        // create title bar 
        add(tp, BorderLayout.NORTH); 

        // create left side frame 
        add(lp, BorderLayout.WEST);

        // create panel4 right side frame 
        add(rp, BorderLayout.EAST);

        // create game screen 
        add(gp, BorderLayout.CENTER);

        setVisible(true); 
    }

    /**
     * This method gets the GamePanel
     * @return GamePanel the game screen
     */
    public GamePanel getGamePanel(){

        return this.gp;
    }
}
