package score4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import score4.model.game_state.GameState;
import score4.model.game_state.board.Board;
import score4.model.game_state.board.Peg;
import score4.model.game_state.board.Position3D;
import score4.model.player.Bead;
import score4.model.player.Colour;
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
public class ControllerOne implements ActionListener, GameboyController {

    private final GameState gameState;

    private final GameboyPanel gameBoyPanel;

    /**
     * a one parameter constructor that makes a controller for 
     * a given GamePanel
     * @param gbp GamePanel 
     */
    public ControllerOne(GameboyPanel gbp, GameState gs) {

        gameBoyPanel = gbp;
        gameState = gs;
    }

    /**
     * gets the game board
     * @return Board the game board
     */
    @Override
    public GameState getGameState() {

        return gameState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!gameBoyPanel.getTextField().getText().equals("")){

            //check if its Human vs Human or Human vs AI
            if(!gameState.isAI()){ // Human vs Human

                if(gameState.getTurn() == gameState.getPlayer(0)){    // white player

                    // Human vs Human
                    String input = gameBoyPanel.getTextField().getText();
                    Pattern pattern = Pattern.compile("[ABCD][1234]");
                    Matcher matcher = pattern.matcher(input);
                    int matches = 0;
                    while (matcher.find()) {

                        matches++;
                    }

                    if(matches != 1) {

                        System.out.println("Invalid input");
                        gameBoyPanel.getTextField().setText("");
                        return;
                    }

                    Board gameBoard = gameState.getBoard();
                    GamePanel gp = gameBoyPanel.getGamePanel();
                    WhiteBeadComponent wBead = gp.getWhiteBead(gp.getCountWhite());
                    gameBoard.realMove(input); //gets location of peg to play on
                    Peg peg = gameBoard.getPeg(gameBoard.getRow(),gameBoard.getColumn());

                    /* model stuff */
                    gameState.applyMove(new Position3D(gameBoard.getRow(), gameBoard.getColumn(), peg.getNextHeight()), Colour.White);

                    System.out.println("human v human");
                    /* set beads location in view */
                    wBead.setBead(peg.getBead(peg.getNextHeight()-1).getPosition3D());
                    gp.update(); // repaint 
                    gameBoyPanel.getTextField().setText(""); // clear text field

                    if(GameState.containsLine(Bead.getTheBeads(), Colour.White)) { // check if game is over

                        System.out.println("Game Over");
                        gameBoyPanel.getTextField().setText("White Wins! Game Over");
                        gameBoyPanel.getTextField().setEditable(false);
                    } 
                
                } else {    // black player

                    // Human vs Human
                    String input = gameBoyPanel.getTextField().getText();
                    Pattern pattern = Pattern.compile("[ABCD][1234]");
                    Matcher matcher = pattern.matcher(input);
                    int matches = 0;
                    while (matcher.find()) {

                        matches++;
                    }

                    if(matches != 1) {

                        System.out.println("Invalid input");
                        gameBoyPanel.getTextField().setText("");
                        return;
                    }
                
                    Board gameBoard = gameState.getBoard();
                    GamePanel gp = gameBoyPanel.getGamePanel();
                    BlackBeadComponent bBead = gp.getBlackBead(gp.getCountBlack());
                    gameBoard.realMove(input);
                    Peg peg = gameBoard.getPeg(gameBoard.getRow(),gameBoard.getColumn());

                    /* model stuff */
                    gameState.applyMove(new Position3D(gameBoard.getRow(), gameBoard.getColumn(), peg.getNextHeight()), Colour.Black);
                
                    /* set beads location in view */
                    bBead.setBead(peg.getBead(peg.getNextHeight()-1).getPosition3D());
                    gp.update(); // repaint 
                    gameBoyPanel.getTextField().setText(""); // clear text field
                
                    if(GameState.containsLine(Bead.getTheBeads(), Colour.Black)) { // check if game is over

                        System.out.println("Game Over");
                        gameBoyPanel.getTextField().setText("Black Wins! Game Over");
                        gameBoyPanel.getTextField().setEditable(false);
                    } 

                }
        
            } else if(gameState.bothAI()) {
                
                // AI vs AI
                GamePanel gp = gameBoyPanel.getGamePanel();
                WhiteBeadComponent wBead = gp.getWhiteBead(gp.getCountWhite());
                BlackBeadComponent bBead = gp.getBlackBead(gp.getCountBlack());
    
                // White AI's turn
                /* model stuff */
                Position3D bestMoveWhite = gameState.findBestMove(4, Colour.White);
                System.err.println("White AI move: " + bestMoveWhite);
                
                gameState.applyMove(bestMoveWhite, Colour.White);
    
                System.err.println("White AI played on " + bestMoveWhite);
    
                /* set beads location in view */
                wBead.setBead(bestMoveWhite);
                gp.update(); // repaint 
                
                if(GameState.containsLine(Bead.getTheBeads(), Colour.White)) { // check if game is over
    
                    System.out.println("Game Over");
                    gameBoyPanel.getTextField().setText("White Wins! Game Over");
                    gameBoyPanel.getTextField().setEditable(false);
                    
                } else {
    
                //Black AI's turn
                /* model stuff */
                Position3D bestMoveBlack = gameState.findBestMove(2, Colour.Black);
                System.err.println("Black AI move: " + bestMoveBlack);
                
                gameState.applyMove(bestMoveBlack, Colour.Black);
    
                System.err.println("Black AI played on " + bestMoveBlack);
    
                /* set beads location in view */
                bBead.setBead(bestMoveBlack);
                gp.update(); // repaint 
                
                if(GameState.containsLine(Bead.getTheBeads(), Colour.Black)) { // check if game is over
    
                    System.out.println("Game Over");
                    gameBoyPanel.getTextField().setText("Black Wins! Game Over");
                    gameBoyPanel.getTextField().setEditable(false);
                } 
            }
            } else {

                // Human vs AI
                String input = gameBoyPanel.getTextField().getText();
                Pattern pattern = Pattern.compile("[ABCD][1234]");
                Matcher matcher = pattern.matcher(input);
                int matches = 0;
                while (matcher.find()) {

                    matches++;
                }

                if(matches != 1) {

                    System.out.println("Invalid input");
                    gameBoyPanel.getTextField().setText("");
                    return;
                }

                Board gameBoard = gameState.getBoard();
                GamePanel gp = gameBoyPanel.getGamePanel();
                WhiteBeadComponent wBead = gp.getWhiteBead(gp.getCountWhite());
                BlackBeadComponent bBead = gp.getBlackBead(gp.getCountBlack());
                gameBoard.realMove(input); //gets location of peg to play on
                Peg peg = gameBoard.getPeg(gameBoard.getRow(),gameBoard.getColumn());

                /* model stuff */
                gameState.applyMove(new Position3D(gameBoard.getRow(), gameBoard.getColumn(), peg.getNextHeight()), Colour.White);

                System.err.println("White played on " + input);

                /* set beads location in view */
                wBead.setBead(peg.getBead(peg.getNextHeight()-1).getPosition3D());
                gp.update(); // repaint 
                gameBoyPanel.getTextField().setText(""); // clear text field
                
                if(GameState.containsLine(Bead.getTheBeads(), Colour.White)) { // check if game is over

                    System.out.println("Game Over");
                    gameBoyPanel.getTextField().setText("White Wins! Game Over");
                    gameBoyPanel.getTextField().setEditable(false);
                } else {

                //AI's turn
                /* model stuff */
                Position3D bestMove = gameState.findBestMove(4, Colour.Black);
                System.err.println("AI move: " + bestMove);
                
                gameState.applyMove(bestMove, Colour.Black);

                System.err.println("AI played on " + bestMove);
                /* set beads location in view */
                bBead.setBead(bestMove);

                gp.update(); // repaint 
                System.err.println("Possible Moves;" + gameState.getPossibleMoves(Colour.Black));
                gameBoyPanel.getTextField().setText(""); // clear text field
                if(GameState.containsLine(Bead.getTheBeads(), Colour.Black)) { // check if game is over

                    System.out.println("Game Over");
                    gameBoyPanel.getTextField().setText("Black Wins! Game Over");
                    gameBoyPanel.getTextField().setEditable(false);
                } 
            }
            }
        }
        
    }
}