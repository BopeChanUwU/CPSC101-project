package score4.model.player;

import score4.model.game_state.GameState;

public class AIPlayer implements Player {
    
    private Colour beadColour;
    //private Bead bead;
    private int numWins = 0;
    private boolean isCurrentPlayer;

    /**
     * AIPlayer constructor
     * @param n int representing the player number
     * @throws IllegalArgumentException if n is not 1 or 2
     */
    public AIPlayer(int n) {

        if(n<=0 || n>2){

            throw new IllegalArgumentException("this is a 2 player dumby not a " + n + " player game");
        } else if( n == 1) {

            beadColour = Colour.White;
            isCurrentPlayer = true;
        } else {

            beadColour = Colour.Black;
            isCurrentPlayer = false;
        }
        
    }

    public void setnumWin(){

        numWins += 1;
    }
    /**
     * gets the bead colour
     * @return Colour beadColour
     */
    public Colour getColour(){

        return beadColour;
    }

    /**
     * updates the model with the AI's move 
     */
    @Override
    public void move(int x, int y, GameState currentState) {
        
        isCurrentPlayer = false;
        //should update model stuff
    }

    /**
     * gets a Move recommended by the AI
     */
    @Override
    public int getMove() {
        
        return 1;
    }

}
