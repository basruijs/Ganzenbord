package goose.squares;

import goose.*;

public class Square{

    int id;

    public Square(int id){
        this.id=id;
    }

    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        checkBump(geese, origin, board, goose);
    }

    public boolean checkBump(Goose[] geese, int origin, Board board, Goose goose) {
        if(!Config.shareSpaces) {
            for (int i = 0; i < geese.length; i++) {
                if (board.getSquare(goose.getPosition()) == board.getSquare(geese[i].getPosition()) && goose != geese[i]) {
                    goose.setPosition(origin);
                    System.out.println(Emoji.HURT + " You bumped into the " + geese[i].getName() + " goose! ");
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
        if(id<10){
            return "0" + id;
        } else {
            return Integer.toString(id);
        }
    }
}
