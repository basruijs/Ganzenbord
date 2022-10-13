package goose;

import goose.squares.GooseSquare;
import goose.squares.SpecialSquare;
import goose.squares.Square;

public class Board implements Emoji {

    int size = 64;
    Square[] squares = new Square[size];

    public Board(){

        for (int i = 0; i < size; i++) {
            switch (i){
                case 63:
                    squares[i] = new SpecialSquare(i,"Finish", CHECKERED_FLAG);
                    break;
                case 0:
                    squares[i] = new SpecialSquare(i, "Start", ARROW_RIGHT);
                    break;
                case 5,9,14,18,23,27,32,36,41,45,50,54,59:
                    squares[i] = new GooseSquare(i,"Goose", BIRD);
                    break;
                case 6:
                    squares[i] = new SpecialSquare(i, "Bridge", RAINBOW);
                    break;
                case 19:
                    squares[i] = new SpecialSquare(i, "Inn", HOUSE);
                    break;
                case 31:
                    squares[i] = new SpecialSquare(i, "Well", HOLE);
                    break;
                case 42:
                    squares[i] = new SpecialSquare(i, "Maze", EVERGREEN_TREE);
                    break;
                case 52:
                    squares[i] = new SpecialSquare(i, "Prison", CASTLE);
                    break;
                case 58:
                    squares[i] = new SpecialSquare(i, "Death", SKULL);
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
