package score4.model;

public class Bead {

    Colour beadColour;

    /**
     * 
     * @param colour
     */
    public Bead(Colour colour) {

        beadColour = colour;
    }

    /**
     * 
     * @return
     */
    public Colour getColour() {

        return beadColour;
    }

    /**
     * 
     * @param colour
     */
    public void setColour(Colour colour){

        beadColour = colour;
    }
}
