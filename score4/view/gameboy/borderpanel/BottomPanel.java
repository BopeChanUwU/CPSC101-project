package score4.view.gameboy.borderpanel;

import javax.imageio.ImageIO;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.Color;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a BottomPanel class
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class BottomPanel extends JPanel {

    private final int originalTileSize = 16;// size of each tile 
    private final int tileSize = originalTileSize * 3;//real tile is 48x48
    private final int MaxScreenCol = 13;
    private final int MaxScreenRow = 10;
    private final int screenWidth = tileSize * MaxScreenCol;
    private final int screenHeight = tileSize * MaxScreenRow;

    /**
     * BottomPanel constructor
     */
    public BottomPanel() {

        setLayout(new GridLayout(10,12));
        for (int i = 0; i < 1; i++) {

            add(new JLabel());
        }
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(73,71,134));
    }

    /**
     * draws the bottom panel
     * @param g2 graphics 2D object
     * @throws Exception resource not found
     */
    public void draw(Graphics2D g2) throws Exception {

        //tlc
        BufferedImage image = ImageIO.read(getClass().getResource("resources/tlc.png"));
        g2.drawImage(image,0,48, tileSize, -tileSize,null);

        //tb
        BufferedImage image2 = ImageIO.read(getClass().getResource("resources/tb.png"));
        for (int i = 48; i < 576; i = i+48) {
            
            g2.drawImage(image2,i,48, tileSize, -tileSize,null);
        }

        //trc2
        BufferedImage image3 = ImageIO.read(getClass().getResource("resources/trc2.png"));
        g2.drawImage(image3,575,48, tileSize, -tileSize,null);

        //Direction
        BufferedImage image4 = ImageIO.read(getClass().getResource("resources/direction.png"));
        g2.drawImage(image4,75,150, tileSize * 3, tileSize * 3,null); 

        //A
        BufferedImage image5 = ImageIO.read(getClass().getResource("resources/A.png"));
        g2.drawImage(image5,430,225, tileSize * 12 / 10, tileSize * 12 / 10,null);

        //B
        BufferedImage image6 = ImageIO.read(getClass().getResource("resources/B.png"));
        g2.drawImage(image6,500,150, tileSize * 12 / 10, tileSize * 12 / 10,null);
    }

    /**
     * paints the bottom panel
     * @param Graphics g rendering object
     * @throws Exception draw error
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;//casts g from graphics to graphics 2D

        try {

            draw(g2);
        } catch (Exception e) {

            System.err.println("Error while drawing in BottomPanel");
        }

        paintChildren(g2);
    }
}
