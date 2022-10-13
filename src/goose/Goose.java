package goose;

import goose.squares.Square;

import java.util.Random;

public class Goose implements Color, Emoji{
    public boolean won;

    String color;
    String printColor;
    int position;
    boolean firstRoll;
    boolean isTrapped;
    boolean isImprisoned;
    boolean bot;

    public Goose(String color){
        this.color=color;
        firstRoll=true;
        isTrapped=false;
        isImprisoned=false;
        printColor = makePrintColor();
    }

    private String makePrintColor() {
        switch (color.toLowerCase()){
            case "red":
            case "rood":
            case "1":
                this.color="red";
                return ANSI_RED;
            case "green":
            case "groen":
            case "2":
                this.color="green";
                return ANSI_GREEN;
            case "blue":
            case "blauw":
            case "3":
                this.color="blue";
                return ANSI_BRIGHT_BLUE;
            case "yellow":
            case "geel":
            case "4":
                this.color="yellow";
                return ANSI_YELLOW;
            case "purple":
            case "paars":
            case "5":
                this.color="purple";
                return ANSI_PURPLE;
            case "pink":
            case "roze":
            case "6":
                this.color="pink";
                return ANSI_PINK;
            case "gray":
            case "grijs":
            case "grey":
            case "7":
                this.color="grey";
                return ANSI_GRAY;
            case "lime":
            case "8":
                this.color="lime";
                return ANSI_LIME;
            case "cyan":
            case "cyaan":
            case "9":
                this.color="cyan";
                return ANSI_CYAN;
            case "white":
            case "wit":
            case "10":
                this.color="white";
                return ANSI_WHITE;
            case "black":
            case "zwart":
            case "11":
                this.color="black";
                return ANSI_BLACK;
            case "brown":
            case "bruin":
            case "12":
                this.color="brown";
                return ANSI_BROWN;
            case "light blue":
            case "lichtblauw":
            case "13":
                this.color="light blue";
                return ANSI_LIGHT_BLUE;
            case "light gray":
            case "light grey":
            case "licht grijs":
            case "14":
                this.color="light grey";
                return ANSI_LIGHT_GRAY;

            case "dark blue":
            case "donkerblauw":
            case "15":
                this.color="dark blue";
                return ANSI_BLUE;
            case "bright red":
            case "fel rood":
            case "16":
                this.color="bright red";
                return ANSI_BRIGHT_RED;

            default:
                return randomizeColor();
        }
    }

    private String randomizeColor(){
        int color;
        Random random = new Random();
        color = random.nextInt(1,8);
        switch (color){
            case 1:
                return ANSI_BLACK;
            case 2:
                return ANSI_RED;
            case 3:
                return ANSI_GREEN;
            case 4:
                return ANSI_YELLOW;
            case 5:
                return ANSI_BLUE;
            case 6:
                return ANSI_PURPLE;
            case 7:
                return ANSI_CYAN;
            case 8:
                return ANSI_WHITE;
            default:
                return ANSI_WHITE;
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getColor() {
        return  printColor + color + ANSI_RESET;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void walk(int movement, Board board, Goose[] geese){
        int origin=this.position;
        move(movement, board, geese, origin);
    }
    public void move(int movement, Board board, Goose[] geese, int origin){
        if(isTrapped){
            System.out.println("Skip turn, " + this.getColor() + " goose");
        }
        if(isImprisoned){
            System.out.println("You are stuck, " + this.getColor() + " goose");
        }
        if(!isTrapped && !isImprisoned) {
            this.position += movement;
            if (this.position > (board.size - 1)) {
                int overflow = this.position - (board.size - 1);
                this.position = (board.size - 1);
                System.out.println(overflow);
                this.move(-(overflow), board, geese, origin);
            }
            freeGoose(origin, position, geese);
            checkPosition(this, geese, origin, movement, board);
        } else if (!isImprisoned){
            this.isTrapped=false;
        } else {
            if(lastPlayer(geese) && Game.lastPlayerFreedWell && this.position==31){
                free();
            }
            if(lastPlayer(geese) && Game.lastPlayerFreedPrison && this.position==52){
                free();
            }
        }
    }

    private void freeGoose(int origin, int position, Goose[] geese) {
        if((hasPassed(origin, position, 31) && Game.passToFreeWell) || this.position==31){
            for (Goose goose : geese) {
                if (goose!=this && (goose.isImprisoned && goose.getPosition() == 31)) {
                    System.out.println("the " + this.getColor() + " goose has helped the "
                            + goose.getColor() + "out of the well");
                    goose.free();

                }
            }
        }

        if((hasPassed(origin, position, 52) && Game.passToFreePrison) || this.position==52){
            for (Goose goose : geese) {
                if (goose!=this && (goose.isImprisoned && goose.getPosition() == 52)) {
                    System.out.println("the " + this.getColor() + " goose has broken the "
                            + goose.getColor() + " out of prison");
                    goose.free();

                }
            }
        }
    }

    private boolean hasPassed(int origin, int position, int square){
        return (square<origin && square>position);
    }

    public boolean isFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(boolean firstRoll) {
        this.firstRoll = firstRoll;
    }

    public void checkPosition(Goose goose, Goose[] geese, int origin, int totalValue, Board board) {
        Square[] squares=board.getSquares();
        squares[position].run(totalValue,this, board, geese, origin);

    }

    public boolean lastPlayer(Goose[] geese) {
        for (int i = 0; i < geese.length; i++) {
            if(geese[i].getPosition()<position){
                return true;
            }
        }
        return false;
    }

    public void trap(){
        this.isTrapped = true;
    }

    public void imprison() {
        isImprisoned=true;
    }

    public void free(){
        System.out.println("The " + getColor() + " has been freed");
        isImprisoned = false;
        isTrapped = false;
    }

    @Override
    public String toString() {
        return printColor + DUCK + ANSI_RESET;
    }

    public boolean isBot() {
        return bot;
    }


    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}
