package asciiversion;

public class InputValidator {
    private int x;
    private int y;

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void realMove(String input){
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