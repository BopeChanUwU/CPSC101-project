package score4.model;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Position3D class
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
 * @version 1
 */
public class Board {

    private Peg[][] gameBoard = new Peg[4][4];
    private int x;
    private int y;

    /**
     * 
     */
    public Board() {

        for (Peg[] gameBoard1 : gameBoard) {

            for (int i = 0; i < 4; i++) {
                
            gameBoard1[i] = new Peg();
            }
            
        }
    }

    /**
     * 
     * @param row
     * @param col
     * @return
     */
    public Peg getPeg(int row, int col) {

        return gameBoard[row][col];
    }

    /**
     * 
     * @return
     */
    public int getX() {

        return x;
    }

    /**
     * 
     * @return
     */
    public int getY() {

        return y;
    }

    /**
     * 
     * @param row
     * @param col
     * @param hieght
     * @return
     */
    public Bead getColourAt(int row, int col, int hieght) {

        return getPeg(row, col).getBead(hieght);
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
