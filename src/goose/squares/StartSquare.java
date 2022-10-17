package goose.squares;

import goose.Board;
import goose.Emoji;
import goose.Goose;

public class StartSquare extends SpecialSquare {
    public StartSquare(int id) {
        super(id, Emoji.ARROW_RIGHT);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {

    }
}
