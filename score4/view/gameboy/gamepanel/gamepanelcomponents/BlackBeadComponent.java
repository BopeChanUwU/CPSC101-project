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
     * 
     */
    public BlackBeadComponent() {

        try {
            bead = ImageIO.read(new File(
                "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/beads/Bbead.png"));  //black beads
        } catch (IOException e) {
            //e.printStackTrace();
        }
        
        xIndex = 0;
        yIndex = 0;
    }

    /**
     * 
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bead,xIndex, yIndex, beadSize, beadSize, null);
    }

    /**
     * 
     */
    @Override
    public void setBead(int x, int y) {

        xIndex = x;
        yIndex = y;
        repaint();
    }

    /**
     * 
     */
    @Override
    public BufferedImage getBead() {

        return this.bead;
    }

    /**
     * 
     */
    @Override
    public int getXIndex() {

        return this.xIndex;
    }

    /**
     * 
     */
    @Override
    public int getYIndex() {

        return this.yIndex;
    }

    /**
     * 
     */
    @Override
    public int getBeadSize(){
        
        return beadSize;
    }
}
