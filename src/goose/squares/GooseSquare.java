package goose.squares;

import goose.*;

public class GooseSquare extends SpecialSquare{

    public GooseSquare(int id) {
        super(id, Emoji.BIRD);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        if (goose.isFirstRoll() || (goose.getPosition() == 0 && Config.antiInstaWin)) {
            goose.setFirstRoll(false);
            if(totalValue == 9){
                firstRollMove(board,goose,geese,origin);
            } else {
                goose.move(totalValue, board, geese, origin);
            }
        } else {
            goose.walk(totalValue, board, geese);
        }
        System.out.println(Emoji.SPARKLES + " A magical goose rests on space " + goose.getPosition());
        goose.move(totalValue, board, geese, origin);
        if(!checkBump(geese, origin, board, goose)) {
            System.out.println(Emoji.SPARKLES + Emoji.BIRD + Emoji.SPARKLES + "The " + goose.getName() +
                    " goose'd to space " + goose.getPosition());
        }
    }

    private void firstRollMove(Board board, Goose goose, Goose[] geese, int origin) {
        if ((Game.d1.getValue() == 5 || Game.d2.getValue() == 5) &&
                (Game.d1.getValue() == 4 || Game.d2.getValue() == 4)) {
            goose.move(53, board, geese, origin);
            System.out.println("You jumped to 53");
        } else if ((Game.d1.getValue() == 6 || Game.d2.getValue() == 6) &&
                (Game.d1.getValue() == 3 || Game.d2.getValue() == 3)) {
            goose.move(26, board, geese, origin);
            System.out.println("You jumped to 26");
        }
    }

}
