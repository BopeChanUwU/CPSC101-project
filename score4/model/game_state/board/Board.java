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
public class Board implements Cloneable {

    private final int size;
    private final Peg[][] gameBoard;
    private int row;
    private int col;

    /**
     * Board constructor
     * <p> This constructor initializes the game board with Peg objects.
     * <p> Each Peg object is initialized with a default constructor.
     * <p> The game board is a 4x4 array of Peg objects.
     * <p> The Peg class is used to represent the pegs on the game board.
     */
    public Board() {

        size = 4;
        gameBoard = new Peg[size][size];
        for (Peg[] gameBoard1 : gameBoard) {
            for (int j = 0; j < gameBoard1.length; j++) {
                gameBoard1[j] = new Peg();
            }
        }
    }

    /**
     * single parameter constructor that takes in a specified size as and 
     * integer and creates a square board (size x size)
     * @param size int the desired board size
     */
    public Board(int size) {

        this.size = size;
        gameBoard = new Peg[size][size];
        for (Peg[] gameBoard1 : gameBoard) {
            for (int j = 0; j < gameBoard1.length; j++) {
                gameBoard1[j] = new Peg(size);
            }
        }
    }

    /**
     * gets the peg at a given location
     * @param row int row
     * @param col int column
     * @return Peg the peg you wish to get
     * @throws IllegalArgumentException if row or column is out of bounds
     */
    public Peg getPeg(int row, int col) {

        if(!checkInBounds(row, col)){
            throw new IllegalArgumentException("peg is out of bound (" 
                + row + " x " + col + " is not within " + size + " x " + size +" )");
        }
        return gameBoard[row][col];
    }

    /**
     * gets pegs x location on the board
     * @return int peg location in the x 
     */
    public int getRow() {

        return row;
    }

    /**
     * gets pegs y location on the board
     * @return int peg location in the y
     */
    public int getColumn() {

        return col;
    }

    /**
     * gets the bead at a given location
     * @param row int row
     * @param col int column
     * @param hieght int height
     * @return Bead bead
     * @throws IllegalArgumentException if row, column, or height is out of bounds
     */
    public Bead getBeadAt(int row, int col, int hieght) {

        if(checkInBounds(row, col) && getPeg(row, col).checkInBounds(hieght)) 
            return getPeg(row, col).getBead(hieght);
        throw new IllegalArgumentException("peg is out of bound ("
            + row + " x " + col + "x" + hieght + " is not within " + size + " x " + size + " x " + size + " )");
    }

    /**
     * takes in a string and returns the location of the peg to play on
     * @param input A String denoting the peg you wish to play on
     * @throws IllegalArgumentException if input is invalid
     */
    public void realMove(String input) {

        switch(input){
            case "A1" -> {
                row = 0;
                col = 0;
            }
            case "A2" -> {
                row = 0;
                col = 1;
            }
            case "A3" -> {
                row = 0;
                col = 2;
            }
            case "A4" -> {
                row = 0;
                col = 3;
            }
            case "B1" -> {
                row = 1;
                col = 0;
            }
            case "B2" -> {
                row = 1;
                col = 1;
            }
            case "B3" -> {
                row = 1;
                col = 2;
            }
            case "B4" -> {
                row = 1;
                col = 3;
            }
            case "C1" -> {
                row = 2;
                col = 0;
            }
            case "C2" -> {
                row = 2;
                col = 1;
            }
            case "C3" -> {
                row = 2;
                col = 2;
            }
            case "C4" -> {
                row = 2;
                col = 3;
            }
            case "D1" -> {
                row = 3;
                col = 0;
            }
            case "D2" -> {
                row = 3;
                col = 1;
            }
            case "D3" -> {
                row = 3;
                col = 2;
            }
            case "D4" -> {
                row = 3;
                col = 3;
            }
            default -> {
                
                throw new IllegalArgumentException("Oi! what made you think this input was okay? " + input);
            }
        }
    }  

    /**
     * checks to see if a specified row and column is inbounds.
     * if it is inbounds it sets the row and column to the specified
     * row and column respectively.
     * @throws IllegalArgumentException if out of bounds
     * @param row int the row index
     * @param col int the column index
     */
    public void realMove(int row, int col) {

        if(checkInBounds(row, col)) {

            this.row = row;
            this.col = col;
        } else {

            throw new IllegalArgumentException("Oi! what made you think this input was okay? " + row + " x " + col);
        }
    }

    /**
     * checks to see if a specified row and column is inbounds.
     * @param row int the row index 
     * @param col int the column index 
     * @return Boolean true if inbounds false otherwise
     */
    private boolean checkInBounds(int row, int col) {
        
        return (row>=0 && row<=size-1) && (col>=0 && col<=size-1);
    }

    /**
     * clones the board
     * @return Board clone
     * @throws CloneNotSupportedException if the board cannot be cloned
     */
    @Override
    public Board clone() throws CloneNotSupportedException {

        return (Board) super.clone();
    }
}
