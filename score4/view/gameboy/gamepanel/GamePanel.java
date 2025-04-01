package score4.view.gameboy.gamepanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import score4.controller.PanelEvent;
import score4.controller.PanelListener;
import score4.view.gameboy.gamepanel.gamepanelcomponents.ComponentManager;

public class GamePanel extends JPanel /* implements PanelListener */{

    final int originalTileSize = 16;// size of each tile 
    public final int tileSize = originalTileSize*3;//real tile is 48x48
    private final int MaxScreenCol = 11;
    private final int MaxScreenRow = 6;
    private final int screenWidth = tileSize*MaxScreenCol;
    private final int screenHeight = tileSize*MaxScreenRow+1;

    private EventListenerList listenerList = new EventListenerList();
    private ComponentManager compManager = new ComponentManager(this);//tile manager draws game board
    
    /**
     * 
     */
    public GamePanel(){

        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    /**
     * 
     */
    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;//casts g from graphics to graphics 2D
        /* peg.draw(g2); */
        compManager.draw(g2);//tile comes before beads
        this.paintChildren(g2);//paints buttons and stuff back 
        g2.dispose();//get rid of g2 for mem management
    }

    /**
     * 
     * @param event
     */
    public void firePanelEvent(PanelEvent event){

        Object[] listeners = listenerList.getListenerList();

        for(int i = 0; i < listeners.length; i = i + 2){     //steps through list 2 at a time because first is class second is listener
            if(listeners[i] == PanelListener.class){
                ((PanelListener) listeners[i+1]).panelEventOccured(event);
            }
        }
    }

    /**
     * 
     * @param listener
     */
    public void addPanelListener(PanelListener listener) {

        listenerList.add(PanelListener.class, listener);
    }

    /**
     * 
     * @param listener
     */
    public void removePanelListener(PanelListener listener) {

        listenerList.remove(PanelListener.class, listener);
    }

    /**
     * 
     * @return
     */
    public ComponentManager passComponentManager(){

        return this.compManager;
    }
}
