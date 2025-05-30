package score4.view.gameboy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public class MenuPanel extends JPanel{
    
    private final int originalTileSize = 16;// size of each tile 
    public final int tileSize = originalTileSize*3;//real tile is 48x48
    private final int MaxScreenCol = 11;
    private final int MaxScreenRow = 6;
    private final int screenWidth = tileSize*MaxScreenCol;
    private final int screenHeight = tileSize*MaxScreenRow+1;

    private  JComboBox player1 = new JComboBox<>();
    private  JComboBox player2 = new JComboBox<>();
    private  final JButton enter = new JButton();
    private final JTextField p1Name = new JTextField();
    private final JTextField p2Name = new JTextField();
    /* idea use input from this panel to initialize the gamepanel  */

    /**
     * constructs a GameboyPanel
     */
    public MenuPanel(){
    
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setLayout(new BorderLayout());
        initialize();
    }
    
    /**
     * creates all the stuff inside the panel
     */
    public final void initialize(){
    
        //player select box 1
        add(player1);

        //player select box 2
        add(player2);

        // enter button
        add(enter);

        //player 1 name textfield
        add(p1Name);

        //player 2 name textfield
        add(p2Name);

        setBackground(new Color(73,71,134));
        setVisible(true); 
    }

    /**
     * draws the menu panel
     * @param g2 graphics 2D object
     * @throws Exception resource not found
     */
    public void draw(Graphics2D g2) throws Exception {

        
    }

    /**
     * paints the menu panel
     * @param Graphics g rendering object
     * @throws Exception draw error
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;//casts g from graphics to graphics 2D

        try {

            draw(g2);
        } catch (Exception e) {

            System.err.println("Error while drawing in MenuPanel");
        }

        paintChildren(g2);
    }
}
