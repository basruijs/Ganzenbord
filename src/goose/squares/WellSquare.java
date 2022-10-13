package goose.squares;

import goose.Board;
import goose.Game;
import goose.Goose;

public class WellSquare extends SpecialSquare{
    public WellSquare(int id) {
        super(id, HOUSE);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(HOLE + " You fell into a well in space " + goose.getPosition());
        if(goose.lastPlayer(geese) && Game.lastPlayerFreedWell){
            goose.trap();
        } else {
            goose.imprison();
        }
        System.out.println(HOLE + " You will have to wait until you are able to get out");
    }
}
