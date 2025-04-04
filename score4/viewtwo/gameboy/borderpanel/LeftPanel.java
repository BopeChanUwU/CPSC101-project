package score4.viewtwo.gameboy.borderpanel;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 * This file is part of a Score4 game
 *
 * <p> Implements a LeftPanel class 
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class LeftPanel extends JPanel{

    private final int originalTileSize = 16;// size of each tile 
    private final int tileSize = originalTileSize * 3;//real tile is 48x48
    private final int MaxScreenCol = 1;
    private final int MaxScreenRow = 6;
    private final int screenWidth = tileSize * MaxScreenCol;
    private final int screenHeight = tileSize * MaxScreenRow;

    /**
     * LeftPanel constructor
     */
    public LeftPanel() {

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(73,71,134));
    }

    /**
     * draws the left panel
     * @param g2 graphics 2D object
     * @throws Exception resource not found
     */
    public void draw(Graphics2D g2) throws Exception {
       
        //left border
        BufferedImage image = ImageIO.read(getClass().getResource("resources/LeftBorder.png"));
        for (int i = 0; i < 336; i += 48) {
            
            g2.drawImage(image,0, i, tileSize, tileSize,null);
        }
    }

    /**
     * paints the left panel
     * @param g graphics object
     * @throws Exception draw error
     */
    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;//casts g from graphics to graphics 2D
        
        try {

            draw(g2);
        } catch (Exception e) {

            System.err.println("LeftPanel not Painting");
        }
        paintChildren(g2);
    }
}
