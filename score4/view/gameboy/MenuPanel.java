package score4.view.gameboy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import score4.model.player.AIPlayer;
import score4.model.player.HumanPlayer;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a GameboyPanel class this is the main panel for the game
 * it contains the game screen and the title bar and the bottom bar
 * and the left and right side panels
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class MenuPanel extends JPanel{
    
    private final int originalTileSize = 16;// size of each tile 
    public final int tileSize = originalTileSize*3;//real tile is 48x48
    private final int MaxScreenCol = 13;
    private final int MaxScreenRow = 30;
    private final int screenWidth = tileSize*MaxScreenCol-1;
    private final int screenHeight = tileSize*MaxScreenRow;

    private  final JButton onePlayer = new JButton("Single Player");
    private final JButton twoPlayer = new JButton("multi-Player");
    private final JButton testingMinimax = new JButton("Minimax Test");
    private final JButton about = new JButton("About");
    /* idea use input from this panel to initialize the gamepanel  */

    /**
     * constructs a GameboyPanel
     */
    public MenuPanel(){
    
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setLayout(new GridLayout(9,1));
        initialize();
    }
    
    /**
     * creates all the stuff inside the panel
     */
    public final void initialize(){
        
        add (new JLabel("<html><H1>SCORE 4!</H1></html>"
                        )); //title label
        //might need to add blank jlabels to fill space and push things down into location
        for (int i = 0; i < 4; i++) {
            add(new JLabel());
        }

        // 1 Player button
        add(onePlayer);
        onePlayer.addActionListener((ActionEvent e) -> {
            
            if(e.getSource() != onePlayer) return;
            GameboyPanel gbp1 = new GameboyPanel(new HumanPlayer(1), new AIPlayer(2));
            MenuPanel.this.removeAll();
            MenuPanel.this.setLayout(new BorderLayout());
            MenuPanel.this.add(gbp1, BorderLayout.CENTER);
            MenuPanel.this.revalidate();
            MenuPanel.this.repaint();
            System.out.println("hello 1");
        });

        //Multi Player button
        add(twoPlayer);
        twoPlayer.addActionListener((ActionEvent e) -> {

            if(e.getSource() != twoPlayer) return;
            GameboyPanel gbp2 = new GameboyPanel(new HumanPlayer(1), new HumanPlayer(2));
            MenuPanel.this.removeAll();
            MenuPanel.this.setLayout(new BorderLayout());
            MenuPanel.this.add(gbp2, BorderLayout.CENTER);
            MenuPanel.this.revalidate();
            MenuPanel.this.repaint();
            System.out.println("hello 2");
        });

        //Minimax test button
        add(testingMinimax);
        testingMinimax.addActionListener((ActionEvent e) -> {

           if(e.getSource() != testingMinimax) return;
           GameboyPanel gbp3 = new GameboyPanel(new AIPlayer(1), new AIPlayer(2));
           MenuPanel.this.removeAll();
           MenuPanel.this.setLayout(new BorderLayout());
           MenuPanel.this.add(gbp3, BorderLayout.CENTER);
           MenuPanel.this.revalidate();
           MenuPanel.this.repaint();
           System.out.println("hello 3");
        }); 

        //About button
        add(about);
        about.addActionListener((ActionEvent e) -> {
            
            if(e.getSource() != about) return;
            MenuPanel.this.removeAll();
            MenuPanel.this.setLayout(new BorderLayout());
            JLabel aboutLabel = new JLabel("<html><h1>About</h1><p>This is a Score4 game created by Tristen Sandhu.</p></html>");
            MenuPanel.this.add(aboutLabel, BorderLayout.CENTER);
            MenuPanel.this.revalidate();
            MenuPanel.this.repaint();
            System.out.println("hello 4");});

        setBackground(new Color(73,71,134)); // currently purple
        setVisible(true); 
    }

    /**
     * draws the menu panel
     * @param g2 graphics 2D object
     * @throws Exception resource not found
     */
    public void draw(Graphics2D g2) throws Exception {

        //this is for what gets painted on screen

    }

    /**
     * paints the menu panel
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

            System.err.println("Error while drawing in MenuPanel");
        }

        paintChildren(g2);
    }

    public void update() {

        repaint();
    }
}
