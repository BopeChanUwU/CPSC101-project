package score4.model.player;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a HumanPlayer class that represents a human player in the game.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class HumanPlayer implements Player {

    private Colour beadColour;
    private String name;
    private int wins = 0;
    private boolean turn;
    
    /**
     * HumanPlayer constructor
     * @param n int representing the player number
     * @throws IllegalArgumentException if n is not 1 or 2
     */
    public HumanPlayer(int playerNumber) {

        if(playerNumber<1 || playerNumber>2){

            throw new IllegalArgumentException("this is a 2 player game dumb dumb not a " + playerNumber + " player game");
        } else if( playerNumber == 1) {

            beadColour = Colour.White;
            turn = true;
            name = "Player1";
        } else {

            beadColour = Colour.Black;
            turn = false;
            name = "player2";
        }
    }

    public HumanPlayer(int playerNumber, String playerName) {

        if(playerNumber<=0 || playerNumber>2){

            throw new IllegalArgumentException("this is a 2 player game dumb dumb not a " + playerNumber + " player game");
        } else if( playerNumber == 1) {

            beadColour = Colour.White;
            turn = true;
            name = playerName;
        } else {

            beadColour = Colour.Black;
            turn = false;
            name = playerName;
        }
    }

    /**
     * sets the number of wins
     */
    @Override
    public void increaseWins(){

        wins += 1;
    }

    /**
     * gets the number of wins
     * @return int numWins
     */
    @Override
    public int getWins(){

        return wins;
    }

    /**
     * gets the current player
     * @return boolean isCurrentPlayer
     * true if player is current player
     * false if player is not current player
     */
    @Override
    public boolean getTurn() {

        return turn;
    }

    /**
     * gets the bead colour
     * @return Colour beadColour
     */
    @Override
    public Colour getColour(){

        return beadColour;
    }

    /**
     * gets the players name
     * @return String player name
     */
    @Override
    public String getName() {
        
        return name;
    }

    /**
     * flips the turn status
     */
    @Override
    public void updateTurn() {
        
        turn = !turn;
    }

    /**
     * sets the colour of the player to the specified colour
     * @param colour the colour to change the players beads to
     */
    @Override
    public void setColour(Colour colour) {
        
        beadColour = colour;
    }
}
