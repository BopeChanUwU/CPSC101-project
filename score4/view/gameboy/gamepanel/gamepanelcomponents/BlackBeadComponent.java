package score4.view.gameboy.gamepanel.gamepanelcomponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import score4.view.Bead;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Position3D class
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
 * @version 1
 */
//this class is incharge of painting black beads
public class BlackBeadComponent extends JComponent implements Bead{

    private int xIndex;
    private int yIndex;
    private final int beadSize = 32;

    private BufferedImage bead;

    /**
     * constructs a BlackBeadComponent with default x and y values
     */
    public BlackBeadComponent() {

        try {
            bead = ImageIO.read(new File(
                "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/beads/Bbead.png"));  // black beads
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        xIndex = 0;
        yIndex = 0;
    }

    /**
     * paints the Bead
     * @param Graphics g rendering object
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bead,xIndex, yIndex, beadSize, beadSize, null);
    }

    /**
     * sets the beads xIndex and yIndex
     */
    @Override
    public void setBead(int x, int y) {

        xIndex = x;
        yIndex = y;
    }

    /**
     * gets image of a black bead
     * @return BufferedImage image of a black bead
     */
    @Override
    public BufferedImage getBead() {

        return bead;
    }

    /**
     * gets the position of the bead on the x-axis
     * @return int X position
     */
    @Override
    public int getXIndex() {

        return xIndex;
    }

    /**
     * gets the position of the bead on the y-axis
     * @return int Y position
     */
    @Override
    public int getYIndex() {

        return yIndex;
    }

    /**
     * gets the size of the bead
     * @return int size of the bead
     */
    @Override
    public int getBeadSize() {
        
        return beadSize;
    }
}
