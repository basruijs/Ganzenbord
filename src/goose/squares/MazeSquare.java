package goose.squares;

import goose.Board;
import goose.Game;
import goose.Goose;

public class MazeSquare extends SpecialSquare{
    public MazeSquare(int id) {
        super(id, EVERGREEN_TREE);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(GHOST + " A haunted maze rests on space 42");
        goose.move(-5, board, geese, origin);
        if(!checkBump(geese, origin, board, goose)) {
            checkBump(geese, origin, board, goose);
            System.out.println(FOG + " You got lost and ended up on space " + goose.getPosition());
        }
    }
}
