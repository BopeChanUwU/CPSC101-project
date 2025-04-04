package score4.view.gameboy.gamepanel.gamepanelcomponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;

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
//this class is incharge of painting white beads
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
        g2.drawImage(bead, xIndex, yIndex, beadSize, beadSize, null);
        System.out.println("white beads painting!");
        
    }

    /**
     * sets the beads xIndex and yIndex
     */
    @Override
    public void setBead(int x, int y) {

        xIndex = x;
        yIndex = y;
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
