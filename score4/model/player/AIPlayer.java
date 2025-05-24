package score4.model.player;

public class AIPlayer implements Player {
    
    private Colour beadColour;
    private int wins = 0;
    private final String name;
    private boolean turn;

    /**
     * AIPlayer constructor
     * @param n int representing the player number
     * @throws IllegalArgumentException if n is not 1 or 2
     */
    public AIPlayer(int n) {

        if(n<1 || n>2){

            throw new IllegalArgumentException("this is a 2 player dumby not a " + n + " player game");
        } else if( n == 1) {

            beadColour = Colour.White;
            turn = true;
            name = "QWERTY";
        } else {

            beadColour = Colour.Black;
            turn = false;
            name = "HURTY";
        }
        
    }

    /**
     * increases the number of wins by 1
     */
    @Override
    public void increaseWins(){

        wins += 1;
    }

    /**
     * gets the players current number of wins
     * @return int the total number of wins the player currently has
     */
    @Override
    public int getWins(){

        return wins;
    }
    /**
     * gets current player
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
     * @return String players name
     */
    @Override
    public String getName() {
        
        return name;
    }

    /**
     * updates turn flag
     */
    @Override
    public void updateTurn() {
       
        turn = !turn;
    }

    /**
     * sets the players bead colour to the specified colour
     */
    @Override
    public void setColour(Colour colour) {
        
        beadColour = colour;
    }
}