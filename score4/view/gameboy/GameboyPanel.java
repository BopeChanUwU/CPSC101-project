package score4.view.gameboy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import score4.controller.ControllerOne;
import score4.model.game_state.GameState;
import score4.model.player.Player;
import score4.view.gameboy.borderpanel.BottomPanel;
import score4.view.gameboy.borderpanel.LeftPanel;
import score4.view.gameboy.borderpanel.RightPanel;
import score4.view.gameboy.borderpanel.TitlePanel;
import score4.view.gameboy.gamepanel.*;

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

    private ImageIcon image2;

    private final ControllerOne controller;
    public TextField textField = new TextField();

    /**
     * constructs a GameboyPanel
     */
    public GameboyPanel(Player player1, Player player2){

        controller = new ControllerOne(this, new GameState(player1,player2));
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

        bp.add(textField);
        textField.setBackground(new Color(159,146,189));
        textField.setFont(new java.awt.Font(TOOL_TIP_TEXT_KEY, ABORT, 
            32));

        JButton button = new JButton();
        bp.add(button);
        button.addActionListener(controller); //action event happens in controller class

        // gets image icon "enter" 
        try {

            image2 = new ImageIcon(ImageIO.read(getClass().getResource("resources/enter3.png")));
        } catch (IOException e) {

            System.err.println("This should never happen so what did you do!!!!");
        }
        button.setBorder(BorderFactory.createLineBorder(new Color(73,71,134)));
        button.setIcon(image2);
        button.setSize(64, 32);

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
