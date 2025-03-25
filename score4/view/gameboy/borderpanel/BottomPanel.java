package score4.view.gameboy.borderpanel;

import javax.imageio.ImageIO;
/* import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton; */
import javax.swing.JLabel;
import javax.swing.JPanel;
/* import javax.swing.JTextField;
import score4.controller.Controller;
import score4.view.gameboy.gamepanel.GamePanel;
import score4.view.gameboy.gamepanel.gamepanelcomponents.ComponentManager; */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;

public class BottomPanel extends JPanel{

    private final int originalTileSize = 16;// size of each tile 
    private final int tileSize = originalTileSize*3;//real tile is 48x48
    private final int MaxScreenCol = 13;
    private final int MaxScreenRow = 10;
    private final int screenWidth = tileSize*MaxScreenCol;
    private final int screenHeight = tileSize*MaxScreenRow;
    /* private ImageIcon image2;
    private JButton button;
    private GamePanel gp; */

    public BottomPanel(){

        this.setLayout(new GridLayout(10,12));
        for (int i = 0; i < 1; i++) {
            this.add(new JLabel());
        }
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(73,71,134));

        //creates new text field
       /*  JTextField textField = new JTextField();
        textField.setBackground(new Color(159,146,189));
        textField.setFont(new java.awt.Font(TOOL_TIP_TEXT_KEY, ABORT, 
            originalTileSize*2));
        this.add(textField);  */

        //create new button
       /*  this.add(button = new JButton()); */
        //add behavior
       /*  button.addActionListener(new Controller() { // action listener reads txtfield and prints it to terminal
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(textField.getText().equals("")){
                    System.out.println("please input move...");
                }else {
                    String input = textField.getText();
                    String realInput = input.substring(18, 20);
                    System.out.println(realInput);
                    peg.realMove(realInput);
                    peg.convertTo2D();
                    //ComponentManager.add(new BlackBeadComponent());
                    //how do i get this repaint to repaint gp
                    super.update(peg.getXLocation(), peg.getYLocation());
                    System.out.println(peg.getX()+"  "+peg.getY());

                }
            }
        }); */
        
        //gets image icon "enter"
        /* try{
            image2 = new ImageIcon(ImageIO.read(new File(
                "/Users/tristensandhu/Desktop/CPSC101 "+
                "project/score4/resources/misc./enter3.png")));
        } catch (Exception e) {
            System.out.println("This should never happen so what did you do!!!!");
        }
        button.setBorder(BorderFactory.createLineBorder(new Color(73,71,134)));
        button.setIcon(image2);
        button.setSize(64, 32); */
    }

    // this draws my bottom game bit 
    public void draw(Graphics2D g2) throws Exception{
        BufferedImage image = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./tlc.png"));
        g2.drawImage(image,0,48,this.tileSize,-this.tileSize,null);

        BufferedImage image2 = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./tb.png"));
        for (int i = 48; i < 576; i = i+48) {
            
            g2.drawImage(image2,i,48,this.tileSize,-this.tileSize,null);
        }

        BufferedImage image3 = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./trc2.png"));
        g2.drawImage(image3,575,48,this.tileSize,-this.tileSize,null);

        BufferedImage image4 = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./Direction.png"));
        g2.drawImage(image4,75,150,this.tileSize*3,this.tileSize*3,null); 

        BufferedImage image5 = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./A.png"));
        g2.drawImage(image5,430,225,this.tileSize*12/10,this.tileSize*12/10,null);

        BufferedImage image6 = ImageIO.read(new File(
            "/Users/tristensandhu/Desktop/CPSC101 "+
            "project/score4/resources/misc./B.png"));
        g2.drawImage(image6,500,150,this.tileSize*12/10,this.tileSize*12/10,null);
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;//casts g from graphics to graphics 2D
        try {
            this.draw(g2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.paintChildren(g2);
        g2.dispose();//get rid of g2 for mem management
    }
}
