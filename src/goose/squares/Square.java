package goose.squares;

import goose.Board;
import goose.Emoji;
import goose.Game;
import goose.Goose;

public class Square implements Emoji {

    int id;

    public Square(int id){
        this.id=id;
    }

    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        checkBump(geese, origin, board, goose);
    }

    public boolean checkBump(Goose[] geese, int origin, Board board, Goose goose) {
        if(!Game.shareSpaces) {
            for (int i = 0; i < geese.length; i++) {
                if (board.getSquare(goose.getPosition()) == board.getSquare(geese[i].getPosition()) && goose != geese[i]) {
                    goose.setPosition(origin);
                    System.out.println(HURT + " You bumped into the " + geese[i].getName() + " goose! ");
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
