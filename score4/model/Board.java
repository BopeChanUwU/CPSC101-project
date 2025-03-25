package score4.model;

public class Board {

    private Peg[][] gameBoard = new Peg[4][4];
    private int x;
    private int y;

    public Board() {

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {

                gameBoard[i][j] = new Peg();
            }
        }
    }

    public Peg getPeg(int row, int col) {

        return gameBoard[row][col];
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public Bead getColourAt(int row, int col, int hieght) {

        return getPeg(row, col).getBead(hieght);
    }

    /**
     * takes in a string and returns the location of the peg to play on
     * @param input A String denoting the peg you wish to play on
     */
    public void realMove(String input) {

        switch(input){
            case "A1":
                x = 0;
                y = 0;
                break;
            case "A2":
                x = 0;
                y = 1;
                break;
            case "A3":
                x = 0;
                y = 2;
                break;
            case "A4":
                x = 0;
                y = 3;
                break;
            case "B1":
                x = 1;
                y = 0;
                break;
            case "B2":
                x = 1;
                y = 1;
                break;
            case "B3":
                x = 1;
                y = 2;
                break;
            case "B4":
                x = 1;
                y = 3;
                break;
            case "C1":
                x = 2;
                y = 0;
                break;
            case "C2":
                x = 2;
                y = 1;
                break;
            case "C3":
                x = 2;
                y = 2;
                break;
            case "C4":
                x = 2;
                y = 3;
                break;
            case "D1":
                x = 3;
                y = 0;
                break;
            case "D2":
                x = 3;
                y = 1;
                break;
            case "D3":
                x = 3;
                y = 2;
                break;
            case "D4":
                x = 3;
                y = 3;
                break;
            default:
                x = -1;
                y = -1;
                break;
        }
    }  

}
