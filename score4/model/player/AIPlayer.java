package score4.model.player;

import score4.model.board.Colour;

public class AIPlayer implements Player {
    
    private Colour beadColour;

    /**
     * AIPlayer constructor
     * @param n int representing the player number
     * @throws IllegalArgumentException if n is not 1 or 2
     */
    public AIPlayer(int n) {

        if(n<0 || n>2){

            throw new IllegalArgumentException("this is a 2 player dumby not a " + n + " player game");
        } else if( n == 1) {

            beadColour = Colour.White;
        } else {

            beadColour = Colour.Black;
        }
        
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
    public void move(int x) {
        
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
