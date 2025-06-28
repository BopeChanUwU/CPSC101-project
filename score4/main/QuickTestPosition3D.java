
package score4.main;

import score4.model.game_state.board.Position3D;

public class QuickTestPosition3D {

    public static void main(String[] args) {
        
        Position3D pos1 = new Position3D(1, 1, 1);
        Position3D pos2 = new Position3D(2, 1, 1);
        Position3D pos3 = new Position3D(3, 1, 1);
        Position3D pos4 = new Position3D(1, 2, 3);

        System.out.println("pos1: " + pos1);
        System.out.println("pos2: " + pos2);
        System.out.println("pos3: " + pos3);
        System.out.println("pos4: " + pos4);

        System.out.println(pos1.areCollinear(pos2, pos3));
        System.out.println(pos1.areCollinear(pos2, pos4));

    }
}
