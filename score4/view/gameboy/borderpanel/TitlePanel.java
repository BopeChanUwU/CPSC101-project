package score4.view.gameboy.borderpanel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a TitlePanel class this is the title bar for the game
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class TitlePanel extends JPanel {

    private final int originalTileSize = 16;// size of each tile 
    private final int tileSize = originalTileSize*3;//real tile is 48x48
    private final int MaxScreenCol = 13;
    private final int MaxScreenRow = 1;
    private final int screenWidth = tileSize*MaxScreenCol;
    private final int screenHeight = tileSize*MaxScreenRow;

    private JLabel title;

    private ImageIcon image;

    /**
     * TitlePanel constructor
     */
    public TitlePanel() {
        
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(73,71,134));

        // makes title jlabel
        add(title = new JLabel()); 
        title.setHorizontalAlignment(SwingConstants.CENTER);
        //gets image icon "little contruction boy"
        try {

            //construction worker
            image = new ImageIcon(ImageIO.read(getClass().getResource("resources/construction worker.png")));
        } catch (IOException e) {
            
            System.err.println("construction worker not found");
        }
        title.setIcon(image);
    }

    /**
     * draws the title panel
     * @param g2 graphics 2D object
     * @throws Exception resource not found
     */
    public void draw(Graphics2D g2) throws Exception {

        //tlc
        BufferedImage image1 = ImageIO.read(getClass().getResource("resources/tlc.png"));
        g2.drawImage(image1,0,0, tileSize, tileSize,null);

        //tb
        BufferedImage image2 = ImageIO.read(getClass().getResource("resources/tb.png"));
        for (int i = 48; i < 576; i = i+48) {
            
            g2.drawImage(image2,i,0, tileSize, tileSize,null);
        }

        //trc
        BufferedImage image3 = ImageIO.read(getClass().getResource("resources/trc.png"));
        g2.drawImage(image3,575,0,this.tileSize,this.tileSize,null);
    }

    /**
     * paints the title panel
     * @param g graphics object
     * @throws Exception draw error
     */
    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 

        try {

            draw(g2);
        } catch (Exception e) {

            System.err.println("Error while drawing in TitlePanel");
        }

        paintChildren(g2);
    }
}
