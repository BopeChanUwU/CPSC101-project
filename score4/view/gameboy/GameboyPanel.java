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
import score4.model.player.ClassicEvaluator;
import score4.model.player.Evaluator;
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

    private boolean aiGame;

    private ImageIcon image2;

    private final ControllerOne controller;
    private final TextField textField = new TextField();
    private final Runnable onBack;

    /**
     * constructs a GameboyPanel, with both colours scored by the classic evaluator
     * @param onBack Runnable invoked when the back button is pressed to
     *               return to the main menu
     */
    public GameboyPanel(Player player1, Player player2, Runnable onBack){

        this(player1, player2, new ClassicEvaluator(), new ClassicEvaluator(), onBack);
    }

    /**
     * constructs a GameboyPanel with each colour scored by its own evaluator,
     * so two evaluators can be compared against each other directly
     * @param player1 Player white
     * @param player2 Player black
     * @param whiteEvaluator Evaluator used to score White's searches
     * @param blackEvaluator Evaluator used to score Black's searches
     * @param onBack Runnable invoked when the back button is pressed to
     *               return to the main menu
     */
    public GameboyPanel(Player player1, Player player2, Evaluator whiteEvaluator, Evaluator blackEvaluator, Runnable onBack){

        controller = new ControllerOne(this, new GameState(player1, player2, whiteEvaluator, blackEvaluator));
        this.onBack = onBack;
        setLayout(new BorderLayout());
        initialize();
    }

    /**
     * creates all the stuff inside the panel
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

        // create text field
        bp.add(textField);
        textField.setBackground(new Color(159,146,189));
        textField.setFont(new java.awt.Font(TOOL_TIP_TEXT_KEY, ABORT,
            32));

        JButton button = new JButton();
        bp.add(button);
        button.addActionListener(controller); //action event happens in controller class

        // back to menu button
        JButton backButton = new JButton("Back");
        bp.add(backButton);
        backButton.addActionListener((java.awt.event.ActionEvent e) -> {

            controller.stopAIVsAI();
            onBack.run();
        });

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

        return gp;
    }

    /**
     * this method gets the textfield
     * @return TextField textfield
     */
    public TextField getTextField(){

        return textField;
    }
}
