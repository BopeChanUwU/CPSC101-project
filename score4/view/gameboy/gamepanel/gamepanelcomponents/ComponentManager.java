package score4.view.gameboy.gamepanel.gamepanelcomponents;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import score4.view.gameboy.gamepanel.*;
import score4.view.gameboy.tile.Tile;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Position3D class
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
 * @version 1
 */
// THIS IS GAME BOARD!!!!!
public class ComponentManager {

    private final GamePanel gp;
    Tile[] tile;
    PegComponent pC;
    WhiteBeadComponent[] wBead = new WhiteBeadComponent[32];
    BlackBeadComponent[] bBead = new BlackBeadComponent[32];

    private int countBlack = 0;
    private int countWhite = 0;

    private final int[][] xPosition = new int[2][32]; //plan store game locations to move stuff 
    private final int[][] yPosition = new int[2][32];


    /**
     * 
     * @param gp
     */
    public ComponentManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[3];
        getTileImage();

        pC = new PegComponent();

        wBead = new WhiteBeadComponent[32];
        for (int i = 0; i < 32; i++) {
            wBead[i] = new WhiteBeadComponent();
            xPosition[0][i] = wBead[i].getXIndex();
            yPosition[0][i] = wBead[i].getYIndex();
        }
        
        bBead = new BlackBeadComponent[32];
        for (int i = 0; i < 32; i++) {
            bBead[i] = new BlackBeadComponent();
            xPosition[1][i] = bBead[i].getXIndex();
            yPosition[1][i] = bBead[i].getYIndex();
        }
    }
             
    /**
     * This method sets the location of a black bead on the screen
     * @param x int x position
     * @param y int y position
     * @param z int z position
     */
    public void setBlackBead(int x, int y, int z) {

        int x2D;
        int y2D;

        switch (x) {
            case 0 -> { x2D = 0;
            }
            case 1 -> { x2D = 40;
            }
            case 2 -> { x2D = 80;
            }
            case 3 -> { x2D = 120;
            }
            default -> throw new AssertionError();
        }

        switch (y) {
            case 0 -> { y2D = 0 + z*32;
            }
            case 1 -> { y2D = 64 + z*32;
            }
            case 2 -> { y2D = 128 + z*32;
            }
            case 3 -> { y2D = 192 + z*32;
            }
            default -> throw new AssertionError();
        }

        bBead[countBlack].setBead(x2D, y2D);
        countBlack++;
    }
        
    /**
     * This method sets the location of a white bead on the screen
     * @param x int x position
     * @param y int y position
     * @param z int z position
     */
    public void setWhiteBead(int x, int y, int z) {
        
        int x2D;
        int y2D;

        switch (x) {
            case 0 -> { x2D = 0;
            }
            case 1 -> { x2D = 40;
            }
            case 2 -> { x2D = 80;
            }
            case 3 -> { x2D = 120;
            }
            default -> throw new AssertionError();
        }

        switch (y) {
            case 0 -> { y2D = 0 + z*32;
            }
            case 1 -> { y2D = 64 + z*32;
            }
            case 2 -> { y2D = 128 + z*32;
            }
            case 3 -> { y2D = 192 + z*32;
            }
            default -> throw new AssertionError();
        }

        xPosition[0][countWhite] = x2D;
        yPosition[0][countWhite] = y2D;

        wBead[countWhite].setBead(x2D, y2D);
        countWhite++;
    }

    /**
     * Gets some of my assests i didnt make into objects
     */
    public final void getTileImage() {

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File(
                "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/tile/Grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File(
                "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/board/board.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File(
                "/Users/tristensandhu/Desktop/CPSC101 " +
                "project/score4/resources/misc./AIbuddy.png"));
        }catch(IOException e) {

            System.err.println("image not found");
            //e.printStackTrace();
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
                    gp.tileSize, /* width */
                    gp.tileSize, /* height */
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
                pC.getPegSizeX(), /* width */
                pC.getPegSizeY(), /* height */
                null);//this is a peg (2)
        }

        for (int x = 40; x < 450; x += 120) {
            
            g2.drawImage(pC.getPeg(),
                x, 128,
                pC.getPegSizeX(), /* width */
                pC.getPegSizeY(), /* height */
                null);//this is a peg (2)
        }
        
        for (int x = 80; x < 550; x += 120) {
            
            g2.drawImage(pC.getPeg(),
                x, 64,
                pC.getPegSizeX(), /* width */
                pC.getPegSizeY(), /* height */
                null);//this is a peg (2)
        }
        
        for (int x = 120; x < 660; x += 120) {
            
            g2.drawImage(pC.getPeg(),
                x, 4,
                pC.getPegSizeX(), /* width */
                pC.getPegSizeY(), /* height */
                null);//this is a peg (2)
        }

        //ai buddy for when you select vs computer figure out the details later 
        g2.drawImage(tile[2].image,523,201,
            -101, /* width is inverted so he faces the correct direction */
            91,
            null); 

        g2.translate(8, 260);
        //paint black beads
        int i = 0;
        for (BlackBeadComponent blackBead : bBead) {

            g2.drawImage(blackBead.getBead(), /*image */
            xPosition[1][i] = blackBead.getXIndex(), /* int x */
            yPosition[1][i] = blackBead.getYIndex(),  /* int y */
            blackBead.getBeadSize(),  /* int hight */
            blackBead.getBeadSize(),null);   /* int width / img observer */
            i++;
        }

        //paint white beads
        i = 0;
        for (WhiteBeadComponent whiteBead : wBead) {

            g2.drawImage(whiteBead.getBead(), /*image */
            xPosition[0][i] = whiteBead.getXIndex(), /* int x */
            yPosition[0][i] = whiteBead.getYIndex(),  /* int y */
            whiteBead.getBeadSize(),  /* int hight */
            whiteBead.getBeadSize(),null);   /* int width / img observer */
            i++;
        } 

        g2.drawImage(wBead[1].getBead(), /*image */
            /* wBead[0].getXIndex() */40, /* int x */
            /* wBead[1].getYIndex() */ xPosition[0][1],  /* int y */
            wBead[1].getBeadSize(),  /* int hight */
            wBead[1].getBeadSize(),null);   /* int width / img observer */

        g2.dispose();
    }
}
