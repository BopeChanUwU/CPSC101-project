package score4.viewtwo.gameboy.gamepanel.gamepanelcomponents;

import java.awt.image.BufferedImage;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Bead interface for the view
 *
 * @author Tristen Sandhu
 * @version 1
 */
public interface Bead {

    /**
     * sets the bead location
     * @param x int x location
     * @param y int y location
     */
    void setBead(int x,int y);

    /**
     * gets the bead image
     * @return BufferedImage bead
     */
    BufferedImage getBead();

    /**
     * gets the beads x location
     * @return int x location
     */
    int getXIndex();

    /**
     * gets the beads y location
     * @return int y location
     */
    int getYIndex();

    /**
     * gets the beads size
     * @return int bead size
     */
    int getBeadSize();
}
