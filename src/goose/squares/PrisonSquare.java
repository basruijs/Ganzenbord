package goose.squares;

import goose.Board;
import goose.Game;
import goose.Goose;

public class PrisonSquare extends SpecialSquare{
    public PrisonSquare(int id) {
        super(id, CASTLE);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(SIREN + " The " + goose.getName() + " goose has been imprisoned for their crimes on square " + goose.getPosition());

        if(goose.lastPlayer(geese) && Game.lastPlayerFreedPrison){
            goose.trap();
        } else {
            goose.imprison();
        }

        System.out.println(CASTLE + " You will have to serve your time.");
    }
}
