package score4.view.gameboy.gamepanel.gamepanelcomponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import score4.view.Bead;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a BlackBeadComponent class these are the black beads
 * for the view
 *
 * @author Tristen Sandhu
 * @version 1
 */

public class BlackBeadComponent extends JComponent implements Bead{

    private int xIndex;
    private int yIndex;
    private final int beadSize = 32;

    private BufferedImage bead;

    /**
     * constructs a BlackBeadComponent with default x and y values
     */
    public BlackBeadComponent() {

        super();
        try {

            bead = ImageIO.read(getClass()
            .getResource("resources/beads/Bbead.png"));  // black beads
        } catch (IOException e) {

            System.err.println("Black bead not found");
        }
        
        xIndex = 0;
        yIndex = 0;
        setVisible(true);
    }

    /**
     * paints the Bead
     * @param Graphics g rendering object
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Draw the image at (0, 0) relative to the component
        if (bead != null) {
            g2.drawImage(bead, 0, 0, beadSize, beadSize, null);
        } else {
            System.err.println("Bead image is null!");
        }
    }

    /**
     * sets the beads xIndex and yIndex
     */
    @Override
    public void setBead(int x, int y) {

        xIndex = x;
        yIndex = y;
        setBounds(xIndex, yIndex, beadSize, beadSize);
        setVisible(true);
        repaint();
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
