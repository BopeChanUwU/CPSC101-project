package score4.model.player;

import score4.model.game_state.GameState;
import score4.model.game_state.board.Peg;
import score4.model.game_state.board.Position3D;

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
    private int numWins = 0;
    private boolean isCurrentPlayer;
    
    /**
     * HumanPlayer constructor
     * @param n int representing the player number
     * @throws IllegalArgumentException if n is not 1 or 2
     */
    public HumanPlayer(int n) {

        if(n<=0 || n>2){

            throw new IllegalArgumentException("this is a 2 player game dumb dumb not a " + n + " player game");
        } else if( n == 1) {

            beadColour = Colour.White;
            isCurrentPlayer = true;
        } else {

            beadColour = Colour.Black;
            isCurrentPlayer = false;
        }
    }

    /**
     * sets the number of wins
     */
    public void setnumWin(){

        numWins += 1;
    }

    /**
     * gets the number of wins
     * @return int numWins
     */
    public int getnumWin(){

        return numWins;
    }
    /**
     * gets the current player
     * @return boolean isCurrentPlayer
     * true if player is current player
     * false if player is not current player
     */
    public boolean isCurrentPlayer() {

        return isCurrentPlayer;
    }
    /**
     * gets the bead colour
     * @return Colour beadColour
     */
    public Colour getColour(){

        return beadColour;
    }

    /**
     * updates the model with the players selected move
     */
    @Override
    public Position3D move(int x, int y, GameState currentState) {

        Peg peg = currentState.getBoard().getPeg(x,y);
        if(x < 0 || x > 3) {

            throw new IllegalArgumentException("x is out of bounds");
        }
        if(y < 0 || y > 3) {

            throw new IllegalArgumentException("y is out of bounds");
        }
        if(peg.getPegZ() < 4) {
            
            switch (beadColour) {
                case White -> {
                        Bead[] pegHeight = peg.getPegHeight();
                        pegHeight[peg.getPegZ()] = Bead.createBead(Colour.White, new Position3D(x, y, peg.getPegZ()));
                    }
                case Black -> {
                        Bead[] pegHeight = peg.getPegHeight();
                        pegHeight[peg.getPegZ()] = Bead.createBead(Colour.Black, new Position3D(x, y, peg.getPegZ()));
                    }
                default -> throw new IllegalArgumentException("height is out of bounds");
            }
            peg.increasePegZ();
        }
        isCurrentPlayer = false;
        //TODO: apply the move to the model
        //how do i change other players isCurrentPlayer to true?
        return peg.getPegHeight()[peg.getPegZ()-1].getPosition3D();
    }

    /**
     * gets a Move recommended by the AI
     */
    public Position3D getMove() {
       
        //TODO: implement getMove
        return null;
    }
}
