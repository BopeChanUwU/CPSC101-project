package score4.view.gameboy.gamepanel.gamepanelcomponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import score4.view.Bead;

//this class is incharge of painting white beads
public class WhiteBeadComponent extends JComponent implements Bead{

    private int xIndex;
    private int yIndex;
    private final int beadSize = 32;

    private BufferedImage bead;

    /**
     * 
     */
    public WhiteBeadComponent() {

        try {
            bead = ImageIO.read(new File(
                "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/beads/Wbead.png"));  //white beads
        } catch (IOException e) {
            e.printStackTrace();
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
    public int getBeadSize() {
        
        return beadSize;
    }
}
