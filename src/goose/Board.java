package goose;

import goose.squares.*;

public class Board implements Emoji {

    int size = 64;
    Square[] squares = new Square[size];

    public Board(){

        for (int i = 0; i < size; i++) {
            switch (i){
                case 63:
                    squares[i] = new FinishSquare(i);
                    break;
                case 0:
                    squares[i] = new StartSquare(i);
                    break;
                case 5,9,14,18,23,27,32,36,41,45,50,54,59:
                    squares[i] = new GooseSquare(i);
                    break;
                case 6:
                    squares[i] = new BridgeSquare(i);
                    break;
                case 19:
                    squares[i] = new InnSquare(i);
                    break;
                case 31:
                    squares[i] = new WellSquare(i);
                    break;
                case 42:
                    squares[i] = new MazeSquare(i);
                    break;
                case 52:
                    squares[i] = new PrisonSquare(i);
                    break;
                case 58:
                    squares[i] = new DeathSquare(i);
                    break;
                default:
                    squares[i] = new Square(i);
                    break;
            }

        }
    }


    public Square[] getSquares() {
        return squares;
    }

    public Square getSquare(int i) {
        return squares[i];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
