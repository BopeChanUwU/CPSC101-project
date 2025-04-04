package asciiversion;

import java.util.Scanner;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a AsciiBoard class that runs the game in ASCII format.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class AsciiBoard {
    public static void main(String[] args) {

        AsciiBeads game = new AsciiBeads();

        try (Scanner keyboard = new Scanner(System.in)) {

            InputValidator validator = new InputValidator();
            
            boolean loopstop = false;
            boolean flag = false;
            
            String token1 = "W";
            String token2 = "B";
            
            int x = 5;
            int y = 5;
            int count1 = 0;
            
            do {
                //prints current board
                game.showBoard();

                //prompt for move
                System.out.println("White enter your move (add White bead to B3.):");
                String input = keyboard.nextLine();
                String realInput = input.substring(18, 20);
                validator.realMove(realInput);
                int z = game.getZ(validator.getX(), validator.getY());
                game.setWhite(validator.getX(),validator.getY());
                
                //checks to see if game should end
                if(count1 >= 3) {
                    
                    flag = game.checkWinner(x, y, z,token1);
                }
                
                //ends game
                if(flag == true) {

                    game.showBoard();
                    System.out.println("White Wins!");
                    System.exit(0);
                }
                
                do {
                    //prints current game board
                    game.showBoard();
                    
                    //prompt for move
                    System.out.println("Black enter your move (add Black bead to B3.):");
                    input = keyboard.nextLine();
                    realInput = input.substring(18, 20);
                    validator.realMove(realInput);
                    z = game.getZ(validator.getX(), validator.getY());
                    game.setBlack(validator.getX(),validator.getY());
                    
                    //checks to see if game should end
                    if(count1 >= 3) {
                        
                        flag = game.checkWinner(x, y, z,token2);
                    }
                    
                    //ends game
                    if(flag == true) {
                        
                        game.showBoard();
                        System.out.println("Black Wins!");
                        System.exit(0);
                    }
                } while (loopstop == true);
                
                count1++;
            } while (count1 <= 32);
        }
    }
}
