package score4.view.gameboy.gamepanel.gamepanelcomponents;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PegComponent extends JComponent{

    private int locationX;
    private int locationY;
    private final int pegSizeX = 48;
    private final int pegSizeY = 96;
    private BufferedImage peg;

    public PegComponent() {

        try {
            peg = ImageIO.read(new File("score4/resources/misc./pegs.png"
                /* "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/misc./pegs.png") */));
        } catch (IOException e) {
            e.printStackTrace();
        }

        locationX = 0;
        locationY = 0;
    }
    
    public BufferedImage getPeg(){
        return this.peg;
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(peg,locationX, locationY, pegSizeX, pegSizeY, null);
    }

    public void setPeg(int x, int y) {

        locationX = x;
        locationY = y;
        repaint();
    }

    public void draw(Graphics2D g2){

        g2.drawImage(peg, locationX, locationY, null);
    }
}
