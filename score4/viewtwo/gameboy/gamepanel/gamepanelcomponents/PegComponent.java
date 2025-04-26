package score4.viewtwo.gameboy.gamepanel.gamepanelcomponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a PegComponent class these are the pegs
 * for the view
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class PegComponent extends JComponent{

    private int locationX;
    private int locationY;
    private final int pegSizeX = 48;
    private final int pegSizeY = 96;

    private BufferedImage peg;
    private BufferedImage pegFocused;

    /**
     * constructs a peg with default start locations
     */
    public PegComponent() {

        try {

            peg = ImageIO.read(getClass().getResource("resources/misc./pegs.png"));
            pegFocused = ImageIO.read(getClass().getResource("resources/misc./pegFocused.png"));
        } catch (IOException e) {

            System.err.println("Peg not found");
        }

        locationX = 0;
        locationY = 0;
    }
    
    /**
     * gets the image of the peg
     * @return BufferedImage peg
     */
    public BufferedImage getPeg() {

        return peg;
    }

    /**
     * gets the image of the peg to the focused image
     * @return BufferedImage pegFocused
     */
    public BufferedImage getPegFocused() {

        return pegFocused;
    }

    /**
     * paints the Peg
     * @param Graphics g rendering object
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(peg,locationX, locationY, pegSizeX, pegSizeY, null);
    }

    /**
     * 
     * @param x
     */
    public void setXLocation(int x) {

        locationX = x;
    }

    /**
     * 
     * @param y
     */
    public void setYLocation(int y) {

        locationY = y;
    }

    /**
     * 
     * @return
     */
    public int getXLocation() {

        return locationX;
    }

    /**
     * 
     * @return
     */
    public int getYLocation() {

        return locationY;
    }

    /**
     * 
     * @return
     */
    public int getPegSizeX() {

        return pegSizeX;
    }

    public int getPegSizeY() {

        return pegSizeY;
    }
}
