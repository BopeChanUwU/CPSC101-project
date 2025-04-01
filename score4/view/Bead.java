package score4.view;

import java.awt.image.BufferedImage;

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
