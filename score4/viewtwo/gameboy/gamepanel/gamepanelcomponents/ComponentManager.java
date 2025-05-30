package score4.viewtwo.gameboy.gamepanel.gamepanelcomponents;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import score4.viewtwo.gameboy.gamepanel.*;
import score4.viewtwo.gameboy.tile.Tile;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a ComponentManager class has the images for 
 * the game board and the pegs tells the game panel to draw them
 *
 * @author Tristen Sandhu
 * @version 1
 */

public class ComponentManager {

    private final GamePanel gp;
    private final Tile[] tile;
    private final PegComponent pC;


    /**
     * 
     * @param gp
     */
    public ComponentManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[3];
        getTileImage();

        pC = new PegComponent();
    }

    /**
     * Gets some of my assests i didnt make into objects
     */
    public final void getTileImage() {

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("resources/tile/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("resources/board/board.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResource("resources/misc./AIbuddy.png"));
        }catch(IOException e) {

            System.err.println("Component manager image not found");
        }
    }

    /**
     * 
     * @param Graphics2D g2 rendering object
     */
    public void draw(Graphics2D g2){

        //paint the grass background
        for (int x = 0; x < 768; x += 48) {
            for (int y = 0; y < 576; y += 48) {

                g2.drawImage(tile[0].image, x, y,
                    gp.tileSize, // width 
                    gp.tileSize, // height 
                    null);//this is grass (0)
            } 
        }

        //paint board
        g2.drawImage(tile[1].image, 25, 96,
            480,
            192,
            null);

        //build the pegs
        for (int x = 0; x < 480; x += 120) {

            g2.drawImage(pC.getPeg(),
                x, 192,
                pC.getPegSizeX(), // width 
                pC.getPegSizeY(), // height 
                null);//this is a peg (2)
        }

        for (int x = 40; x < 450; x += 120) {
            
            g2.drawImage(pC.getPeg(),
                x, 128,
                pC.getPegSizeX(), // width 
                pC.getPegSizeY(), // height 
                null);//this is a peg (2)
        }
        
        for (int x = 80; x < 550; x += 120) {
            
            g2.drawImage(pC.getPeg(),
                x, 64,
                pC.getPegSizeX(), // width 
                pC.getPegSizeY(), // height 
                null);//this is a peg (2)
        }
        
        for (int x = 120; x < 660; x += 120) {
            
            g2.drawImage(pC.getPeg(),
                x, 4,
                pC.getPegSizeX(), // width 
                pC.getPegSizeY(), // height 
                null);//this is a peg (2)
        }

        //ai buddy for when you select vs computer figure out the details later 
        g2.drawImage(tile[2].image,523,201,
            -101, // width is inverted so he faces the correct direction 
            91,
            null); 
    }
}
