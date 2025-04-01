package score4.view.gameboy.borderpanel;

import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
     * 
     */
    public TitlePanel() {
        
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(73,71,134));

        // makes title jlabel
        add(title = new JLabel()); 
        title.setHorizontalAlignment(SwingConstants.CENTER);
        //gets image icon "little contruction boy"
        try{
            image = new ImageIcon(ImageIO.read(new File(
                "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/misc./construction worker.png")));
        } catch (IOException e) {
            System.out.println(
                "This should never happen so what did you do!!!!");
        }
        title.setIcon(image);
    }

    /**
     * 
     * @param g2
     * @throws Exception
     */
    public void draw(Graphics2D g2) throws Exception{
        BufferedImage image1 = ImageIO.read(new File("/Users/tristensandhu/Desktop/CPSC101 project/score4/resources/misc./tlc.png"));
        g2.drawImage(image1,0,0, tileSize, tileSize,null);

        BufferedImage image2 = ImageIO.read(new File("/Users/tristensandhu/Desktop/CPSC101 project/score4/resources/misc./tb.png"));
        for (int i = 48; i < 576; i = i+48) {
            
            g2.drawImage(image2,i,0, tileSize, tileSize,null);
        }

        BufferedImage image3 = ImageIO.read(new File("/Users/tristensandhu/Desktop/CPSC101 project/score4/resources/misc./trc2.png"));
        g2.drawImage(image3,575,0,this.tileSize,this.tileSize,null);
    }

    /**
     * 
     */
    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; //casts g from graphics to graphics 2D
        try {
            draw(g2);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        paintChildren(g2);
        g2.dispose(); //get rid of g2 for mem management
    }
}
