package goose.squares;

import goose.Board;
import goose.Emoji;
import goose.Game;
import goose.Goose;

import java.util.Objects;

public class SpecialSquare extends Square implements Emoji {
    String type;
    String indicator;

    public SpecialSquare(int id, String type, String indicator){
        super(id);
        this.type=type;
        this.indicator = indicator;
    }

    @Override
    public String toString() {
        return indicator;
    }

    @Override
    public void run(int totalValue, Goose goose, Board board, Goose[] geese, int origin) {
        if(Objects.equals(type, "Finish")){
            System.out.println(goose.getColor() +
                    " won!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            if(Game.continueAfterWin){
                int winningGeese = 0;
                for (int i = 0; i < geese.length; i++) {
                    goose.setWon(true);
                    if(geese[i].isWon()){
                        winningGeese++;
                        if(winningGeese>=geese.length){
                            Game.keepGoing=false;
                        }
                    }
                }
            } else {
                Game.keepGoing=false;
            }

        }

//        if(Objects.equals(type, "Goose")){
//            System.out.println("A magical goose rests on space " + goose.getPosition());
//            goose.move(totalValue, board, geese, origin);
//            if(!checkBump(geese, origin, board, goose)) {
//                System.out.println("You goose'd to space " + goose.getPosition());
//            }
//        }

        if(Objects.equals(type, "Bridge")){
            System.out.println(BRIDGE + "An ancient bridge spans from space 6 to space 12");
            goose.move(6, board, geese, origin);
            if(!checkBump(geese, origin, board, goose)) {
                System.out.println(BRIDGE + "You crossed the bridge built by your ancestor geese from 6 to " + goose.getPosition());
            }
        }

        if(Objects.equals(type, "Maze")){
            System.out.println(GHOST + "A haunted maze rests on space 42");
            goose.move(-5, board, geese, origin);
            if(!checkBump(geese, origin, board, goose)) {
                checkBump(geese, origin, board, goose);
                System.out.println(FOG + "You got lost and ended up on space " + goose.getPosition());
            }
        }

        if(Objects.equals(type, "Inn")){
            System.out.println(HOUSE + " A warm inn has set up business on space " + goose.getPosition());
            if(!checkBump(geese, origin, board, goose)){
                goose.trap();
                System.out.println(BEER + " You drink at the inn, spending a turn getting drunk and getting into a barfight");
            }

        }

        if(Objects.equals(type, "Well")){

            System.out.println(HOLE + " You fell into a well in space " + goose.getPosition());
            if(goose.lastPlayer(geese) && Game.lastPlayerFreedWell){
                goose.trap();
            } else {
                goose.imprison();
            }
            System.out.println(HOLE + " You will have to wait until you are able to get out");
        }

        if(Objects.equals(type, "Prison")){

            System.out.println(SIREN + "The " + goose.getColor() + " goose has been imprisoned for their crimes on square " + goose.getPosition());

            if(goose.lastPlayer(geese) && Game.lastPlayerFreedPrison){
                goose.trap();
            } else {
                goose.imprison();
            }

            System.out.println("You will have to serve your time.");
        }

        if(Objects.equals(type, "Death")){
            System.out.println(SKULL + "The " + goose.getColor() + " has fallen in battle on " + goose.getPosition());
            goose.move(-58, board, geese, origin);
            System.out.println(HATCHING_EGG+"A new " + goose.getColor() + " goose has risen to fulfill the prophecy on space "
                    + goose.getPosition());
        }



    }


}
