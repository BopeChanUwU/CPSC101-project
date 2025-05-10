package score4.model.game_state.board;

import score4.model.player.Bead;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Board class that represents the game board.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class Board {

    private final Peg[][] gameBoard = new Peg[4][4];
    private int x;
    private int y;

    /**
     * Board constructor
     * <p> This constructor initializes the game board with Peg objects.
     * <p> Each Peg object is initialized with a default constructor.
     * <p> The game board is a 4x4 array of Peg objects.
     * <p> The Peg class is used to represent the pegs on the game board.
     */
    public Board() {

        for (Peg[] gameBoard1 : gameBoard) {
            for (int j = 0; j < gameBoard1.length; j++) {
                gameBoard1[j] = new Peg();
            }
        }
    }

    /**
     * gets the peg at a given location
     * @param row int row
     * @param col int column
     * @return Peg peg
     * @throws IllegalArgumentException if row or column is out of bounds
     */
    public Peg getPeg(int row, int col) {

        if (row < 0 || row > 3) {

            throw new IllegalArgumentException("row is out of bounds");
        }
        if (col < 0 || col > 3) {

            throw new IllegalArgumentException("column is out of bounds");
        }
        return gameBoard[row][col];
    }

    /**
     * gets pegs x location on the board
     * @return int peg location in the x 
     */
    public int getX() {

        return x;
    }

    /**
     * gets pegs y location on the board
     * @return int peg location in the y
     */
    public int getY() {

        return y;
    }

    /**
     * gets the colour of the bead at a given location
     * @param row int row
     * @param col int column
     * @param hieght int height
     * @return Bead bead
     * @throws IllegalArgumentException if row, column, or height is out of bounds
     * @throws NullPointerException if the peg is null
     * @throws ArrayIndexOutOfBoundsException if the height is out of bounds
     */
    public Bead getColourAt(int row, int col, int hieght) {

        return getPeg(row, col).getBead(hieght);
    }

    /**
     * checks if the game is over
     * @return boolean true if the game is over, false otherwise
     */
    public boolean checkWinner() {

        return false;
    }

    /**
     * takes in a string and returns the location of the peg to play on
     * @param input A String denoting the peg you wish to play on
     * @throws IllegalArgumentException if input is invalid
     */
    public void realMove(String input) {

        switch(input){
            case "A1" -> {
                x = 0;
                y = 0;
            }
            case "A2" -> {
                x = 0;
                y = 1;
            }
            case "A3" -> {
                x = 0;
                y = 2;
            }
            case "A4" -> {
                x = 0;
                y = 3;
            }
            case "B1" -> {
                x = 1;
                y = 0;
            }
            case "B2" -> {
                x = 1;
                y = 1;
            }
            case "B3" -> {
                x = 1;
                y = 2;
            }
            case "B4" -> {
                x = 1;
                y = 3;
            }
            case "C1" -> {
                x = 2;
                y = 0;
            }
            case "C2" -> {
                x = 2;
                y = 1;
            }
            case "C3" -> {
                x = 2;
                y = 2;
            }
            case "C4" -> {
                x = 2;
                y = 3;
            }
            case "D1" -> {
                x = 3;
                y = 0;
            }
            case "D2" -> {
                x = 3;
                y = 1;
            }
            case "D3" -> {
                x = 3;
                y = 2;
            }
            case "D4" -> {
                x = 3;
                y = 3;
            }
            default -> {
                
                throw new IllegalArgumentException("Oi! what made you think this input was okay? " + input);
            }
        }
    }  
}
