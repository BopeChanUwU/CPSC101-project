package score4.view;

import java.awt.image.BufferedImage;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Position3D class
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
 * @version 1
 */
public interface Bead {

    /**
     * 
     * @param x
     * @param y
     */
    void setBead(int x,int y);

    /**
     * 
     * @return
     */
    BufferedImage getBead();

    /**
     * 
     * @return
     */
    int getXIndex();

    /**
     * 
     * @return
     */
    int getYIndex();

    /**
     * 
     * @return
     */
    int getBeadSize();
}
