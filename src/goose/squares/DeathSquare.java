package goose.squares;

import goose.Board;
import goose.Goose;

public class DeathSquare extends SpecialSquare {
    public DeathSquare(int id) {
        super(id, SKULL);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(SKULL + " The " + goose.getName() + " has fallen in battle on " + goose.getPosition());
        goose.move(-58, board, geese, origin);
        System.out.println(HATCHING_EGG+" A new " + goose.getName() + " goose has risen to fulfill the prophecy on space "
                + goose.getPosition());
    }
}
