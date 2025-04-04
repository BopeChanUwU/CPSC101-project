package score4.view.gameboy.gamepanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import score4.view.gameboy.gamepanel.gamepanelcomponents.BlackBeadComponent;
//import score4.controller.PanelEvent;
//import score4.controller.PanelListener;
import score4.view.gameboy.gamepanel.gamepanelcomponents.ComponentManager;
import score4.view.gameboy.gamepanel.gamepanelcomponents.WhiteBeadComponent;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Position3D class
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
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
    //private EventListenerList listenerList = new EventListenerList();
    private final ComponentManager compManager = new ComponentManager(this);//tile manager draws game board

    /**
     * constructs a GamePanel
     */
    public GamePanel() {

        super();
        setPreferredSize(new Dimension(screenWidth, screenHeight));

        setLayout(null);

        for (int i = 0; i < 32; i++) {
            
            bBead[i] = new BlackBeadComponent();
            bBead[i].setSize(32, 32);
            bBead[i].setVisible(false);
            this.add(bBead[i]);
            bBead[i].setLocation(0, 0);
            bBead[i].setSize(32, 32);
            bBead[i].setPreferredSize(new Dimension(32, 32));
            bBead[i].setMinimumSize(new Dimension(32, 32));
        }

        for (int i = 0; i < 32; i++) {
            
            wBead[i] = new WhiteBeadComponent();
            wBead[i].setSize(32, 32);
            wBead[i].setVisible(true);
            this.add(wBead[i]);
            wBead[i].setLocation(0, 0);
            wBead[i].setSize(32, 32);
            wBead[i].setPreferredSize(new Dimension(32, 32));
            wBead[i].setMinimumSize(new Dimension(32, 32));
        }

        setVisible(true);
    }

    /**
     * This method sets the location of a white bead on the screen
     * @param x int x position
     * @param y int y position
     * @param z int z position
     */
    public void setWhiteBead(int x, int y, int z) {
        
        int x2D;
        int y2D;

        switch (x) {
            case 0 -> { x2D = 0;
            }
            case 1 -> { x2D = 40;
            }
            case 2 -> { x2D = 80;
            }
            case 3 -> { x2D = 120;
            }
            default -> throw new AssertionError();
        }

        switch (y) {
            case 0 -> { y2D = 0 + z*32;
            }
            case 1 -> { y2D = 64 + z*32;
            }
            case 2 -> { y2D = 128 + z*32;
            }
            case 3 -> { y2D = 192 + z*32;
            }
            default -> throw new AssertionError();
        }

        wBead[countWhite].setBead(x2D, y2D);
        countWhite++;
    }

    /**
     * This method sets the location of a black bead on the screen
     * @param x int x position
     * @param y int y position
     * @param z int z position
     */
    public void setBlackBead(int x, int y, int z) {

        int x2D;
        int y2D;

        switch (x) {
            case 0 -> { x2D = 0;
            }
            case 1 -> { x2D = 40;
            }
            case 2 -> { x2D = 80;
            }
            case 3 -> { x2D = 120;
            }
            default -> throw new AssertionError();
        }

        switch (y) {
            case 0 -> { y2D = 0 + z*32;
            }
            case 1 -> { y2D = 64 + z*32;
            }
            case 2 -> { y2D = 128 + z*32;
            }
            case 3 -> { y2D = 192 + z*32;
            }
            default -> throw new AssertionError();
        }

        bBead[countBlack].setBead(x2D, y2D);
        countBlack++;    
    }

    /**
     * paints the game panel 
     */
    @Override
    protected  void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;//casts g from graphics to graphics 2D
        compManager.draw(g2);//tile comes before beads

        System.out.println("gamepanel is painting!");

        paintChildren(g); //paints buttons and stuff back 

    }

    /**
     * gets the Component Manager
     * @return ComponentManager
     */
    public ComponentManager passComponentManager() {

        return this.compManager;
    }

    /**
     * repaints GamePanel
     */
    public void update(){

        repaint();
    }

    /**
     * 
     * @param event
     */
    /* public void firePanelEvent(PanelEvent event) {

        Object[] listeners = listenerList.getListenerList();

        for(int i = 0; i < listeners.length; i = i + 2) { //steps through list 2 at a time because first is class second is listener
            
            if(listeners[i] == PanelListener.class) {

                ((PanelListener) listeners[i+1]).panelEventOccured(event);
            }
        }
    } */

    /**
     * 
     * @param listener
     */
    /* public void addPanelListener(PanelListener listener) {

        listenerList.add(PanelListener.class, listener);
    } */

    /**
     * 
     * @param listener
     */
    /* public void removePanelListener(PanelListener listener) {

        listenerList.remove(PanelListener.class, listener);
    } */
}
