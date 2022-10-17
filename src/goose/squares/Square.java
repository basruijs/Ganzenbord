package goose.squares;

import goose.*;

public class Square {

    int id;

    public Square(int id) {
        this.id = id;
    }

    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        checkBump(geese, origin, board, goose);
    }

    public boolean checkBump(Goose[] geese, int origin, Board board, Goose goose) {
        if (!Config.shareSpaces) {
            for (Goose value : geese) {
                if (board.getSquare(goose.getPosition()) == board.getSquare(value.getPosition()) && goose != value) {
                    goose.setPosition(origin);
                    System.out.println(Emoji.HURT + " You bumped into the " + value.getName() + " goose! ");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (id < 10) {
            return "0" + id;
        } else {
            return Integer.toString(id);
        }
    }
}
