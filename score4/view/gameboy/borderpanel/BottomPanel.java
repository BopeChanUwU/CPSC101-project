package score4.view.gameboy.borderpanel;

import javax.imageio.ImageIO;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;

public class BottomPanel extends JPanel {

    private final int originalTileSize = 16;// size of each tile 
    private final int tileSize = originalTileSize * 3;//real tile is 48x48
    private final int MaxScreenCol = 13;
    private final int MaxScreenRow = 10;
    private final int screenWidth = tileSize * MaxScreenCol;
    private final int screenHeight = tileSize * MaxScreenRow;

    /**
     * 
     */
    public BottomPanel() {

        setLayout(new GridLayout(10,12));
        for (int i = 0; i < 1; i++) {

            add(new JLabel());
        }
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(73,71,134));
    }

    // this draws my bottom game bit 
    /**
     * 
     * @param g2
     * @throws Exception
     */
    public void draw(Graphics2D g2) throws Exception {

        BufferedImage image = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./tlc.png"));
        g2.drawImage(image,0,48, tileSize, -tileSize,null);

        BufferedImage image2 = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./tb.png"));
        for (int i = 48; i < 576; i = i+48) {
            
            g2.drawImage(image2,i,48, tileSize, -tileSize,null);
        }

        BufferedImage image3 = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./trc2.png"));
        g2.drawImage(image3,575,48, tileSize, -tileSize,null);

        BufferedImage image4 = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./Direction.png"));
        g2.drawImage(image4,75,150, tileSize * 3, tileSize * 3,null); 

        BufferedImage image5 = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./A.png"));
        g2.drawImage(image5,430,225, tileSize * 12 / 10, tileSize * 12 / 10,null);

        BufferedImage image6 = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./B.png"));
        g2.drawImage(image6,500,150, tileSize * 12 / 10, tileSize * 12 / 10,null);
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
