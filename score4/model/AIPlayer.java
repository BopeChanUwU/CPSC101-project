package score4.model;

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
     * gets a Move recommended by the AI
     */
    @Override
    public void move() {
        
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    /**
     * gets a Move recommended by the AI
     */
    @Override
    public void getMove() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getMove'");
    }

}
