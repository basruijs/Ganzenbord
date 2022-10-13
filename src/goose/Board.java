package goose;

import goose.squares.*;

public class Board {

    int size;
    Square[] squares;

    public Board(int size){
        this.size = size;
        squares = new Square[size];
        for (int i = 0; i < size; i++) {
            switch (i) {
                case 63 -> squares[i] = new FinishSquare(i);
                case 0 -> squares[i] = new StartSquare(i);
                case 5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59 -> squares[i] = new GooseSquare(i);
                case 6 -> squares[i] = new BridgeSquare(i);
                case 19 -> squares[i] = new InnSquare(i);
                case 31 -> squares[i] = new WellSquare(i);
                case 42 -> squares[i] = new MazeSquare(i);
                case 52 -> squares[i] = new PrisonSquare(i);
                case 58 -> squares[i] = new DeathSquare(i);
                default -> squares[i] = new Square(i);
            }

        }
    }

    public void printBoard(int origin, Goose[] geese, Goose goose) {
        String print = "";
        System.out.print("|");
        for (int i = 0; i < 64; i++) {
            print = String.valueOf(squares[i]);
            if ((i > origin && i < goose.getPosition()) || (i < origin && i > goose.getPosition())) {
                print = goose.printColor + squares[i] + Color.ANSI_RESET;
            }

            StringBuilder printedGeese = new StringBuilder();

            for (int j = 0; j < geese.length; j++) {
                if (geese[j].getPosition() == i) {
                    printedGeese.append(geese[j]);
                }
            }

            if (!printedGeese.toString().equals("")) {
                print = printedGeese.toString();
            }

            System.out.print(print);
            System.out.print("|");

            if (i%7 == 0) {
                System.out.println();
                System.out.print("|");
            }

        }

        System.out.println();
        System.out.println();
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
