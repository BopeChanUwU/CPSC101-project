package score4.view.gameboy.gamepanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import score4.view.gameboy.gamepanel.gamepanelcomponents.BlackBeadComponent;
import score4.view.gameboy.gamepanel.gamepanelcomponents.ComponentManager;
import score4.view.gameboy.gamepanel.gamepanelcomponents.WhiteBeadComponent;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a GamePanel class it pretty much just draws the game board 
 * and moves the beads around
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class GamePanel extends JPanel {

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
            bBead[i].setSize(32, 32);
            bBead[i].setPreferredSize(new Dimension(32, 32));
            bBead[i].setMinimumSize(new Dimension(32, 32));
            bBead[i].setVisible(true);
        }

        for (int i = 0; i < 32; i++) {
            
            wBead[i] = new WhiteBeadComponent();
            this.add(wBead[i]);
            wBead[i].setBounds(wBead[i].getXIndex(), wBead[i].getYIndex(), 32, 32);
            wBead[i].setSize(32, 32);
            wBead[i].setPreferredSize(new Dimension(32, 32));
            wBead[i].setMinimumSize(new Dimension(32, 32));
            setVisible(true);
        }

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
