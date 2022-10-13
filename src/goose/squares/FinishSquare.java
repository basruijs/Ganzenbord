package goose.squares;

import goose.Board;
import goose.Game;
import goose.Goose;

import java.util.Objects;

public class FinishSquare extends SpecialSquare {

    public FinishSquare(int id) {
        super(id, CHECKERED_FLAG);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        goose.setWon(true);
        System.out.println(goose.getName() +
                " goose won!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (Game.continueAfterWin) {
            int winningGeese = 0;
            for (int i = 0; i < geese.length; i++) {

                if (geese[i].isWon()) {
                    winningGeese++;
                    if (winningGeese >= geese.length) {
                        Game.keepGoing = false;
                    }
                }
            }
        } else {
            Game.keepGoing = false;
        }

    }

}
