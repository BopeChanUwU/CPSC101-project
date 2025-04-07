package score4.viewtwo.controller-two;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private boolean player1;

    /**
     * a one parameter constructor that makes a controller for 
     * a given GamePanel
     * @param gbp GamePanel 
     */
    public Controller(GameboyPanel gbp) {

        gameBoyPanel = gbp;
        gameBoard = new Board();
        player1 = true;
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

                String input = gameBoyPanel.textField.getText();
                Pattern pattern = Pattern.compile("[ABCD][1234]");
                Matcher matcher = pattern.matcher(input);
                int matches = 0;
                while (matcher.find()) {

                    matches++;
                }

                if(matches != 1) {

                    System.out.println("Invalid input");
                    gameBoyPanel.textField.setText("");
                    return;
                }

                GamePanel gp = gameBoyPanel.getGamePanel();
                WhiteBeadComponent wBead = gp.getWhiteBead(gp.getCountWhite());
                gameBoard.realMove(input); //gets location of peg to play on
                Peg peg = gameBoard.getPeg(gameBoard.getX(),gameBoard.getY());

                /* model stuff */
                System.out.println(input); // gives a parsable play
                gameBoard.realMove(input); //gets location of peg to play on
                System.out.println(gameBoard.getX() + " " + gameBoard.getY());
                peg.setBead(player1); //sets bead at location problem

                /* set beads location in view */
                wBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());

                gp.update(); // repaint 
                gameBoyPanel.textField.setText(""); // clear text field

                if(score4.model.board.Line.containsLine(Bead.getTheBeads())) { // check if game is over

                    System.out.println("Game Over");
                    gameBoyPanel.textField.setText("White Wins! Game Over");
                    gameBoyPanel.textField.setEditable(false);
                    return;
                } 
                player1 = false;
            } else {    // black player

                String input = gameBoyPanel.textField.getText();
                Pattern pattern = Pattern.compile("[ABCD][1234]");
                Matcher matcher = pattern.matcher(input);
                int matches = 0;
                while (matcher.find()) {

                    matches++;
                }

                if(matches != 1) {

                    System.out.println("Invalid input");
                    gameBoyPanel.textField.setText("");
                    return;
                }
                
                GamePanel gp = gameBoyPanel.getGamePanel();
                BlackBeadComponent bBead = gp.getBlackBead(gp.getCountBlack());
                gameBoard.realMove(input);
                Peg peg = gameBoard.getPeg(gameBoard.getX(),gameBoard.getY());

                /* model stuff */
                System.out.println(input); // gives a parsable play
                
                System.out.println(gameBoard.getX() + " " + gameBoard.getY());
                peg.setBead(player1); //sets bead at location
                
                /* set beads location in view */
                bBead.setBead(peg.getBead(peg.getPegHeight()-1).getPosition3D());
                System.out.println(peg.getBead(peg.getPegHeight()-1).getPosition3D());
            
                gp.update(); // repaint 
                gameBoyPanel.textField.setText(""); // clear text field
                
                if(score4.model.board.Line.containsLine(Bead.getTheBeads())) { // check if game is over

                    System.out.println("Game Over");
                    gameBoyPanel.textField.setText("Black Wins! Game Over");
                    gameBoyPanel.textField.setEditable(false);
                    return;
                } 
                player1 = true;
            }
        }
    }
}