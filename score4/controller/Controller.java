package score4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import score4.model.board.Board;
import score4.model.board.Peg;
import score4.view.gameboy.GameboyPanel;
import score4.view.gameboy.gamepanel.GamePanel;
import score4.view.gameboy.gamepanel.gamepanelcomponents.BlackBeadComponent;
import score4.view.gameboy.gamepanel.gamepanelcomponents.WhiteBeadComponent;

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
//Controller exposes data and commands needed by the view (acts as bridge from V -> M)///////////////////////////////////////////
public class Controller implements ActionListener {

    private final Board gameBoard;

    private final GameboyPanel gameBoyPanel;

    private boolean player1 = true;

    /**
     * a one parameter constructor that makes a controller for 
     * a given GamePanel
     * @param gbp GamePanel 
     */
    public Controller(GameboyPanel gbp) {

        gameBoyPanel = gbp;
        gameBoard = new Board();
    }

    /**
     * gets the game board
     * @return Board the game board
     */
    public Board getGameBoard() {

        return gameBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!gameBoyPanel.textField.getText().equals("")){

            if(player1 == true){    // white player

                GamePanel gp = gameBoyPanel.getGamePanel();
                WhiteBeadComponent wBead = gp.getWhiteBead(gp.getCountWhite());
                Peg peg = gameBoard.getPeg(gameBoard.getX(),gameBoard.getY());
                String input = gameBoyPanel.textField.getText();
                String realInput = input.substring(18, 20);

                System.out.println(realInput); // gives a parsable play
                gameBoard.realMove(realInput); //gets location of peg to play on
                peg.setBead(player1); //sets bead at location

                /* set beads location in view */
                wBead.setBead(gameBoard.getX(),gameBoard.getY());
                gp.update(); /* repaint */
                player1 = false;
            } else {    // black player

                GamePanel gp = gameBoyPanel.getGamePanel();
                BlackBeadComponent bBead = gp.getBlackBead(gp.getCountBlack());
                Peg peg = gameBoard.getPeg(gameBoard.getX(),gameBoard.getY());
                String input = gameBoyPanel.textField.getText();
                String realInput = input.substring(18, 20);

                /* model stuff */
                System.out.println(realInput); // gives a parsable play
                getGameBoard().realMove(realInput);
                peg.setBead(player1); //sets bead at location
                
                /* set beads location in view */
                bBead.setBead(gameBoard.getX(),gameBoard.getY());
                gp.update(); /* repaint */
                player1 = true;
            }
        }
    }
}