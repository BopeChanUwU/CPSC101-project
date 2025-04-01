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
     * 
     * @param gap
     */
    public Controller(GamePanel gap) {

        gp = gap;
        gameBoard = new Board();
    }

    /**
     * 
     * @return
     */
    public Board getGameBoard() {

        return gameBoard;
    }

    /**
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e) {

       
    }

    /**
     * 
     * @param x
     * @param y
     */
    public void update(int x, int y){

        
    } 
}