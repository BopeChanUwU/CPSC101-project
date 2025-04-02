package score4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import score4.model.Board;

import score4.view.gameboy.gamepanel.GamePanel;

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

    private JTextField textField;

    private Board gameBoard;

    private GamePanel gp;

    /**
     * a one parameter constructor that makes a controller for 
     * a given GamePanel
     * @param gap GamePanel 
     */
    public Controller(GamePanel gap) {

        gp = gap;
        gameBoard = new Board();
    }

    /**
     * gets the game board
     * @return Board the game board
     */
    public Board getGameBoard() {

        return gameBoard;
    }

    public GamePanel getGamePanel(){

        return gp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       /* doing this in the view cause meh */
    }

    /**
     * 
     * @param x 
     * @param y 
     */
    public void update(int x, int y){

        
    } 
}