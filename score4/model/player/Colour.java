package score4.model.player;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements a Colour enum that represents the colours of the beads in the game.
 *
 * @author Tristen Sandhu

 * @version 1
 */
public enum Colour {

    White,
    Black,
    Null;

    /**
     * Returns the opposite colour of the current colour.
     * If the current colour is White, it returns Black.
     * If the current colour is Black, it returns White.
     * If the current colour is Null, it returns Null.
     *
     * @return Colour the opposite colour
     */
    public Colour opposite() {
        switch (this) {
            case White -> {
                return Black;
            }
            case Black -> {
                return White;
            }
            default ->  {
                {
                    return Null;
                }
            }
        }
    }

    @Override
    public String toString() {
        return switch (this) {
            case White -> "White";
            case Black -> "Black";
            case Null -> "Null";
        };
    }
}
