package score4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import score4.model.Board;

import score4.view.gameboy.gamepanel.GamePanel;

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
        /* peg = new Peg(); */
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