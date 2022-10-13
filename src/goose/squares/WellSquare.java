package goose.squares;

import goose.*;

public class WellSquare extends SpecialSquare{
    public WellSquare(int id) {
        super(id, Emoji.HOLE);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(Emoji.HOLE + " You fell into a well in space " + goose.getPosition());
        if(goose.lastPlayer(geese) && Config.lastPlayerFreedWell){
            goose.skip();
        } else {
            goose.imprison();
        }
        System.out.println(Emoji.HOLE + " You will have to wait until you are able to get out");
    }
}
