package goose.squares;

import goose.Board;
import goose.Goose;

public class BridgeSquare extends SpecialSquare{
    public BridgeSquare(int id) {
        super(id, RAINBOW);
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println(BRIDGE + " An ancient bridge spans from space 6 to space 12");
        goose.move(6, board, geese, origin);
        if(!checkBump(geese, origin, board, goose)) {
            System.out.println(BRIDGE + " You crossed the bridge built by your ancestor geese from 6 to " + goose.getPosition());
        }
    }
}
