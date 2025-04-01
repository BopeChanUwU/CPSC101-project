package score4.view.gameboy.borderpanel;

import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Position3D class
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
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
     * 
     */
    public LeftPanel(){

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(73,71,134));
    }

    /**
     * 
     * @param g2
     * @throws Exception
     */
    public void draw(Graphics2D g2) throws Exception{
        BufferedImage image = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./LeftBorder.png"));
        for (int i = 0; i < 336; i= i+48) {
            
            g2.drawImage(image,0, i, tileSize, tileSize,null);
        }
    }

    /**
     * 
     */
    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;//casts g from graphics to graphics 2D
        
        try {
            draw(g2);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        paintChildren(g2);
        g2.dispose();//get rid of g2 for mem management
    }
}
