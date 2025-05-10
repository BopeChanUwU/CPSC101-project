package score4.viewtwo.gameboy.gamepanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

import javax.swing.JPanel;

import score4.controller.ControllerTwo;
import score4.viewtwo.gameboy.gamepanel.gamepanelcomponents.BlackBeadComponent;
import score4.viewtwo.gameboy.gamepanel.gamepanelcomponents.ComponentManager;
import score4.viewtwo.gameboy.gamepanel.gamepanelcomponents.WhiteBeadComponent;
/**
 * This file is part of a Score4 game
 *
 * <p> Implements a GamePanel class it pretty much just draws the game board 
 * and moves the beads around
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class GamePanel extends JPanel /* implements PanelListener */{

    private final int originalTileSize = 16;// size of each tile 
    public final int tileSize = originalTileSize*3;//real tile is 48x48
    private final int MaxScreenCol = 11;
    private final int MaxScreenRow = 6;
    private final int screenWidth = tileSize*MaxScreenCol;
    private final int screenHeight = tileSize*MaxScreenRow+1;
    private int countBlack = 0;
    private int countWhite = 0;

    private final WhiteBeadComponent[] wBead = new WhiteBeadComponent[32];
    private final BlackBeadComponent[] bBead = new BlackBeadComponent[32];
    private final ComponentManager compManager = new ComponentManager(this);
    private final JButton[] buttons = new JButton[16];
    private final ControllerTwo controller = new ControllerTwo(this);

    /**
     * constructs a GamePanel
     */
    public GamePanel() {

        super();
        setPreferredSize(new Dimension(screenWidth, screenHeight));

        setLayout(null);

        for (int i = 0; i < 32; i++) {
            
            bBead[i] = new BlackBeadComponent();
            this.add(bBead[i]);
            bBead[i].setBounds(bBead[i].getXIndex(), bBead[i].getYIndex(), 32, 32);
            bBead[i].setVisible(true);
        }

        for (int i = 0; i < 32; i++) {
            
            wBead[i] = new WhiteBeadComponent();
            this.add(wBead[i]);
            wBead[i].setBounds(wBead[i].getXIndex(), wBead[i].getYIndex(), 32, 32);
            setVisible(true);
        }

        for (int i = 0; i < 16; i++) {
            
            buttons[i] = new JButton();
            this.add(buttons[i]);
            buttons[i].setVisible(true);
            buttons[i].addActionListener(controller);
            buttons[i].setActionCommand(String.valueOf(i));
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setFocusable(false);
        }
        //x jumps by 120 y jumps by 64
        buttons[0].setBounds(13, 195, 21, 96); // location 0x0
        buttons[1].setBounds(133, 195, 21, 96); // location 1x0
        buttons[2].setBounds(253, 195, 21, 96); // location 2x0
        buttons[3].setBounds(373, 195, 21, 96); // location 3x0
        buttons[4].setBounds(53, 131, 21, 96); // location 0x1
        buttons[5].setBounds(173, 131, 21, 96); // location 1x1
        buttons[6].setBounds(293, 131, 21, 96); // location 2x1
        buttons[7].setBounds(413, 131, 21, 96); // location 3x1
        buttons[8].setBounds(93, 67, 21, 96); // location 0x2
        buttons[9].setBounds(213, 67, 21, 96); // location 1x2
        buttons[10].setBounds(333, 67, 21, 96); // location 2x2
        buttons[11].setBounds(453, 67, 21, 96); // location 3x2
        buttons[12].setBounds(133, 7, 21, 96); // location 0x3
        buttons[13].setBounds(253, 7, 21, 96); // location 1x3
        buttons[14].setBounds(373, 7, 21, 96); // location 2x3
        buttons[15].setBounds(493, 7, 21, 96); // location 3x3

        setVisible(true);
    }

    /**
     * This method returns the number of white beads
     * @return int number of white beads
     */
    public int getCountWhite() {

        return countWhite;
    }

    /**
     * This method returns the number of white beads
     * @return int number of white beads
     */
    public WhiteBeadComponent getWhiteBead(int i) {

        countWhite++;
        return wBead[i];
    }

    /**
     * This method returns the number of black beads
     * @return int number of black beads
     */
    public int getCountBlack() {

        return countBlack;
    }

    /**
     * This method returns the number of black beads
     * @return int number of black beads
     */
    public BlackBeadComponent getBlackBead(int i) {

        countBlack++;
        return bBead[i];
    }

    /**
     * paints the game panel 
     */
    @Override
    protected  void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;//casts g from graphics to graphics 2D
        compManager.draw(g2);//tile comes before beads
        paintChildren(g); //paints buttons and stuff back 
    }

    /**
     * repaints GamePanel
     */
    public void update(){

        repaint();
    }
}
