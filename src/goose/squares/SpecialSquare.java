package goose.squares;

import goose.Board;
import goose.Emoji;
import goose.Game;
import goose.Goose;

import java.util.Objects;

public class SpecialSquare extends Square implements Emoji {
    String type;
    String indicator;

    public SpecialSquare(int id, String indicator){
        super(id);
        this.indicator = indicator;
    }

    @Override
    public String toString() {
        return indicator;
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        System.out.println("Unknown space, please perish.");

    }


}
