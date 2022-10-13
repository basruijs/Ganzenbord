package goose.squares;

import goose.*;

public class PrisonSquare extends SpecialSquare{
    public PrisonSquare(int id) {
        super(id, Emoji.CASTLE);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(Emoji.SIREN + " The " + goose.getName() + " goose has been imprisoned for their crimes on square " + goose.getPosition());

        if(goose.lastPlayer(geese) && Config.lastPlayerFreedPrison){
            goose.skip();
        } else {
            goose.imprison();
        }

        System.out.println(Emoji.CASTLE + " You will have to serve your time.");
    }
}
