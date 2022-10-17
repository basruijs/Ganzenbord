package goose.squares;

import goose.*;

public class GooseSquare extends SpecialSquare{

    public GooseSquare(int id) {
        super(id, Emoji.BIRD);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(Emoji.SPARKLES + " A magical goose rests on space " + goose.getPosition());
        firstRollMove(board, goose, geese, origin, totalValue);
        if(!checkBump(geese, origin, board, goose)) {
            System.out.println(Emoji.SPARKLES + Emoji.BIRD + Emoji.SPARKLES + " You goose'd to space " + goose.getPosition());
        }
    }

    private void firstRollMove(Board board, Goose goose, Goose[] geese, int origin, int totalValue) {
        if ((Game.d1.getValue() == 5 || Game.d2.getValue() == 5)
                && (Game.d1.getValue() == 4 || Game.d2.getValue() == 4)) {

            goose.move(44, board, geese, origin);
            System.out.println("You goose'd all the way to 53");

        } else if ((Game.d1.getValue() == 6 || Game.d2.getValue() == 6)
                && (Game.d1.getValue() == 3 || Game.d2.getValue() == 3)) {

            goose.move(17, board, geese, origin);
            System.out.println("You goose'd all the way to 26");
        } else {
            goose.move(totalValue, board, geese, origin);
        }
    }
}
