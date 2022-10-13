package goose.squares;

import goose.Board;
import goose.Goose;

public class GooseSquare extends SpecialSquare{

    public GooseSquare(int id, String type, String indicator) {
        super(id, type, indicator);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println("A magical goose rests on space " + goose.getPosition());
        goose.move(totalValue, board, geese, origin);
        if(!checkBump(geese, origin, board, goose)) {
            System.out.println("You goose'd to space " + goose.getPosition());
        }
    }

}
