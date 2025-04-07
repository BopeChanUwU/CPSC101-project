package score4.controller;

import score4.model.board.Board;

public interface Controller {

    final Board gameBoard;

    final GameboyPanel gameBoyPanel;

    private boolean player1 = true;

    /**
     * a one parameter constructor that makes a controller for 
     * a given GamePanel
     * @param gbp GamePanel 
     */
    public Controller(GameboyPanel gbp);


    /**
     * gets the game board
     * @return Board the game board
     */
    public Board getGameBoard() ;


}
