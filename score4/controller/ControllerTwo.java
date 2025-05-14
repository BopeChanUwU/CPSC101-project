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
                    /* peg.setBead(player1); */ //sets bead at location problem

                    /* set beads location in view */
                    /* wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D()); */

                    gamePanel.update(); // repaint 
                    player1 = false;
                } else {

                    //model stuff
                    BlackBeadComponent bBead = gamePanel.getBlackBead(gamePanel.getCountBlack());
                    Peg peg = gameBoard.getPeg(0, 0);

                    /* model stuff */
                    /* peg.setBead(player1); */ //sets bead at location problem

                    /* set beads location in view */
                    /* bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                    System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D()); */

                    gamePanel.update(); // repaint 
                    player1 = true;
                }

                gamePanel.repaint();
            }
            case "1" -> {
            }
            case "2" -> {
            }
            case "3" -> {
            }
            case "4" -> {
            }
            case "5" -> {
            }
            case "6" -> {
            }
            case "7" -> {
            }
            case "8" -> {
            }
            case "9" -> {
            }
            case "10" -> {
            }
            case "11" -> {
            }
            case "12" -> {
            }
            case "13" -> {
            }
            case "14" -> {
            }
            case "15" -> {
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
