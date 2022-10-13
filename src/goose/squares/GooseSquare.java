package goose.squares;

import goose.Board;
import goose.Goose;

public class GooseSquare extends SpecialSquare{

    public GooseSquare(int id) {
        super(id, BIRD);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(SPARKLES + " A magical goose rests on space " + goose.getPosition());
        goose.move(totalValue, board, geese, origin);
        if(!checkBump(geese, origin, board, goose)) {
            System.out.println(SPARKLES + BIRD + SPARKLES + "You goose'd to space " + goose.getPosition());
        }
    }

}
