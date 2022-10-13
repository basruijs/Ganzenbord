package goose.squares;

import goose.Board;
import goose.Goose;

public class InnSquare extends SpecialSquare{
    public InnSquare(int id) {
        super(id, HOUSE);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(HOUSE + " A warm inn has set up business on space " + goose.getPosition());
        if(!checkBump(geese, origin, board, goose)){
            goose.trap();
            System.out.println(BEER + " You drink at the inn, spending a turn getting drunk and getting into a barfight");
        }
    }
}
