package score4.view;

import java.awt.image.BufferedImage;

public interface Bead {

    final int beadSize = 32;

    void setBead(int x,int y);

    BufferedImage getBead();

    int getXIndex();

    int getYIndex();

    int getBeadSize();
}
