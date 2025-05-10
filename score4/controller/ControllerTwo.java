package score4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import score4.model.game_state.board.Board;
import score4.model.game_state.board.Peg;
import score4.model.player.Bead;
import score4.viewtwo.gameboy.gamepanel.GamePanel;
import score4.viewtwo.gameboy.gamepanel.gamepanelcomponents.BlackBeadComponent;
import score4.viewtwo.gameboy.gamepanel.gamepanelcomponents.WhiteBeadComponent;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Controller class that handles the game logic
 * and communication between the model and the view. It listens for
 * user input from the GameboyPanel and updates the game state
 *
 * @author Tristen Sandhu

 * @version 1
 */
public class ControllerTwo implements ActionListener, GameboyController {

    private final Board gameBoard;

    private final GamePanel gamePanel;

    private boolean player1;

    /**
     * a one parameter constructor that makes a controller for 
     * a given GamePanel
     * @param gbp GamePanel 
     */
    public ControllerTwo(GamePanel gp) {

        gamePanel = gp;
        gameBoard = new Board();
        player1 = true;
    }

    /**
     * gets the game board
     * @return Board the game board
     */
    @Override
    public Board getGameBoard() {

        return gameBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "0" -> {

                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(0, 0);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(0, 0);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "1" -> {

                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(1, 0);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(1, 0);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "2" -> {
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(2, 0);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint  
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(2, 0);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "3" -> {
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(3, 0);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(3, 0);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "4" -> {

                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(0, 1);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(0, 1);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "5" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(1, 1);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(1, 1);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "6" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(2, 1);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(2, 1);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "7" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(3, 1);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(3, 1);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "8" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(0, 2);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(0, 2);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "9" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(1, 2);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(1, 2);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "10" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(2, 2);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(2, 2);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "11" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(3, 2);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(3, 2);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "12" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(0, 3);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(0, 3);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "13" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(1, 3);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(1, 3);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "14" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(2, 3);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(2, 3);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "15" -> {
                
                if (player1) {

                    //model stuff
                    WhiteBeadComponent wBead = gamePanel.getWhiteBead(gamePanel.getCountWhite());
                    Peg peg = gameBoard.getPeg(3, 3);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(3, 3);

                    /* model stuff */
                    peg.setBead(player1); //sets bead at location problem

                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
        }
        if(score4.model.game_state.board.Line.containsLine(Bead.getTheBeads())) { // check if game is over

            //add win screen
            //find way to check if player1 or player2 won
            //System.out.println("Player 1 wins");
            //System.out.println("Player 2 wins");
            System.out.println("Game Over");
        } 
    }
}
