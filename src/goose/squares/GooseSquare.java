package goose.squares;

import goose.Board;
import goose.Emoji;
import goose.Goose;

public class GooseSquare extends SpecialSquare{

    public GooseSquare(int id) {
        super(id, Emoji.BIRD);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(Emoji.SPARKLES + " A magical goose rests on space " + goose.getPosition());
        goose.move(totalValue, board, geese, origin);
        if(!checkBump(geese, origin, board, goose)) {
            System.out.println(Emoji.SPARKLES + Emoji.BIRD + Emoji.SPARKLES + " You goose'd to space " + goose.getPosition());
        }
    }

}
