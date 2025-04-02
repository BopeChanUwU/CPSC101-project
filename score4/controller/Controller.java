package score4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import score4.model.Board;
import score4.view.gameboy.GameboyPanel;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Position3D class
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
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


    /* public GameboyPanel getGamePanel(){

        return gameBoyPanel;
    } */

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!gameBoyPanel.textField.getText().equals("")){

            if(player1 == true){    // white player

            String input = gameBoyPanel.textField.getText();
            String realInput = input.substring(18, 20);
            System.out.println(realInput); // gives a parsable play
            getGameBoard().realMove(realInput); //gets location of peg to play on
            getGameBoard().getPeg(getGameBoard().getX(), getGameBoard().getY()).setBead(player1); //sets bead at location
            
            gameBoyPanel.getGamePanel().passComponentManager().setWhiteBead(getGameBoard().getX(),
                getGameBoard().getY(),
                getGameBoard().getPeg(getGameBoard().getX(),getGameBoard().getY())
                    .getPegHeight());
            
            gameBoyPanel.getGamePanel().update(); /* repaint */
            player1 = false;
            } else {    // black player

                /* model stuff */
                String input = gameBoyPanel.textField.getText();
                String realInput = input.substring(18, 20);
                System.out.println(realInput); // gives a parsable play
                getGameBoard().realMove(realInput);
                getGameBoard().getPeg(getGameBoard().getX(), getGameBoard().getY()).setBead(player1); //sets bead at location
                /* set beads location in view */
                gameBoyPanel.getGamePanel().passComponentManager().setBlackBead(getGameBoard().getX(),
                    getGameBoard().getY(),
                    getGameBoard().getPeg(getGameBoard().getX(), getGameBoard().getY())
                        .getPegHeight());

                gameBoyPanel.getGamePanel().update(); /* repaint */
                player1 = true;
            }
        }
    }
}