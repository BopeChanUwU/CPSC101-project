package score4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import score4.model.Board;
/* import score4.model.Peg; */
import score4.view.gameboy.gamepanel.GamePanel;

//Controller exposes data and commands needed by the view (acts as bridge from V -> M)///////////////////////////////////////////
public class Controller implements ActionListener {
    private JTextField textField;
    private Board gameBoard;
    private GamePanel gp;
    /* private Peg peg; */

    public Controller(GamePanel gap) {

        gp = gap;
        /* peg = new Peg(); */
        gameBoard = new Board();
    }

    /* public Peg getPeg() {

        return this.peg;
    } */

    public Board getGameBoard() {

        return gameBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //implement methods mad inside of model
        /* if(textField.getText().equals("")){

            System.out.println("please input move...");
        }else {

            String input = textField.getText();
            String realInput = input.substring(18, 20);
            System.out.println(realInput);
            getPeg().realMove(realInput);
            getPeg().convertTo2D();
            System.out.println(getPeg().getX()+"  "+getPeg().getY());
            update(getPeg().getXLocation(),getPeg().getYLocation());
        } */
    }

    /* public void update(int x, int y){

        gp.passComponentManager().setX(peg.getXLocation());
        gp.passComponentManager().setY(peg.getXLocation());
        gp.repaint();
    } */
}