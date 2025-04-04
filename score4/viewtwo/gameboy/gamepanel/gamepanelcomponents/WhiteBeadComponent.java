package score4.viewtwo.gameboy.gamepanel.gamepanelcomponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a WhiteBeadComponent class these are the white beads
 * for the view
 *
 * @author Tristen Sandhu
 * @version 1
 */

public class WhiteBeadComponent extends JComponent implements Bead {

    private int xIndex;
    private int yIndex;
    private final int beadSize = 32;

    private BufferedImage bead;

    /**
     * constructs a WhiteBeadComponent with default x and y values
     */
    public WhiteBeadComponent() {

        super();
        try {

            bead = ImageIO.read(getClass()
            .getResource("resources/beads/Wbead.png"));
        } catch (IOException e) {

            System.err.println("White bead not found");
        }
        
        xIndex = 50;
        yIndex = 50;
        setVisible(true);
    }

    /**
     * paints the Bead
     * @param Graphics g rendering object
     */
    @Override
    protected  void paintComponent(Graphics g) {

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
     * gets image of a white bead
     * @return BufferedImage image of a white bead
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
