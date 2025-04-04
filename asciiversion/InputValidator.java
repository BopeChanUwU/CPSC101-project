package asciiversion;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a InputValidator class that validates user input for the game.
 *
 * @author Tristen Sandhu
 * Student Number: 230165842
 * @version 1
 */
public class InputValidator {
    
    private int x;
    private int y;

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public void realMove(String input) {

        switch(input) {

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
                
                throw new IllegalArgumentException("Oi thats not a vaild move mate! " + input);
            }
        }
    }   
}