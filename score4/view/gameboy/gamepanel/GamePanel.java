package score4.view.gameboy.gamepanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

//import score4.controller.PanelEvent;
//import score4.controller.PanelListener;
import score4.view.gameboy.gamepanel.gamepanelcomponents.ComponentManager;

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

    //private EventListenerList listenerList = new EventListenerList();
    private final ComponentManager compManager = new ComponentManager(this);//tile manager draws game board

    /**
     * constructs a GamePanel
     */
    public GamePanel() {

        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    /**
     * paints the game panel 
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;//casts g from graphics to graphics 2D
        compManager.draw(g2);//tile comes before beads
        paintChildren(g2);//paints buttons and stuff back 
        g2.dispose();//get rid of g2 for mem management
    }

    /**
     * gets the Component Manager
     * @return ComponentManager
     */
    public ComponentManager passComponentManager() {

        return this.compManager;
    }

    /**
     * 
     */
    public void updateComponentManager(){

        //maybe change position here and see what happens
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
