package goose;

import goose.squares.Square;

public class Goose {
    public boolean won;

    String color;
    String printColor;
    int position;
    boolean firstRoll;
    boolean skipTurn;
    boolean isImprisoned;
    boolean bot;

    public Goose(String color) {
        this.color = color;
        firstRoll = true;
        skipTurn = false;
        isImprisoned = false;
        printColor = makePrintColor();
    }

    private String makePrintColor() {
        return ColorPicker.makePrintColor(this, color);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return this + " " + printColor + color + Color.ANSI_RESET;
    }

    public void walk(int movement, Board board, Goose[] geese) {
        int origin = this.position;
        move(movement, board, geese, origin);
    }

    public void move(int movement, Board board, Goose[] geese, int origin) {
        if (skipTurn) {
            System.out.println("Skip turn, " + this.getName() + " goose");
        }
        if (isImprisoned) {
            System.out.println("You are stuck, " + this.getName() + " goose");
        }
        if (!skipTurn && !isImprisoned) {
            try {
                this.position += movement;
                if(Game.d1.getValue()==Game.d2.getValue() && Config.murder) {
                    for (int i = 0; i < geese.length; i++) {
                        if (geese[i].position == this.position && geese[i] != this && !geese[i].isWon()) {
                            geese[i].position = 0;
                            System.out.println(Emoji.KNIFE + " The " + this.getName() +
                                    " goose murdered the " + geese[i].getName() + " goose!");
                        }
                    }
                }
                freeGoose(origin, position, geese);
                checkPosition(this, geese, origin, movement, board);
            } catch (Exception e) {
                int overflow = this.position - (board.size - 1);
                this.position = (board.size - 1);
                System.out.println(Emoji.LIGHTNING + "The " + this.getName() + " goose went too far! They must move " + overflow + " back");
                this.move(-(overflow), board, geese, origin);
            }

            freeGoose(origin, position, geese);

        } else if (skipTurn && !isImprisoned) {
            this.skipTurn = false;
        } else {
            if (lastPlayer(geese) && ((Config.lastPlayerFreedWell && this.position == 31)
                    || (Config.lastPlayerFreedPrison && this.position == 52))) {
                free();
            }
        }
    }

    private void freeGoose(int origin, int position, Goose[] geese) {
        if ((hasPassed(origin, position, 31) && Config.passToFreeWell) || this.position == 31) {
            for (Goose goose : geese) {
                if (goose != this && (goose.isImprisoned && goose.getPosition() == 31)) {
                    System.out.println("The " + this.getName() + " goose has helped the "
                            + goose.getName() + " goose out of the well");
                    goose.free();

                }
            }
        }

        if ((hasPassed(origin, position, 52) && Config.passToFreePrison) || this.position == 52) {
            for (Goose goose : geese) {
                if (goose != this && (goose.isImprisoned && goose.getPosition() == 52)) {
                    System.out.println("The " + this.getName() + " goose has broken the "
                            + goose.getName() + " goose out of prison");
                    goose.free();

                }
            }
        }
    }

    private boolean hasPassed(int origin, int position, int square) {
        return (square < origin && square > position) || (square > origin && square < position) ;
    }

    public void checkPosition(Goose goose, Goose[] geese, int origin, int totalValue, Board board) {
        Square[] squares = board.getSquares();
        squares[position].run(totalValue, this, board, geese, origin);

    }

    public boolean lastPlayer(Goose[] geese) {
        for (Goose goose : geese) {
            if (goose.getPosition() < position) {
                return false;
            }
        }
        return true;
    }

    public void skip() {
        this.skipTurn = true;
    }

    public void imprison() {
        isImprisoned = true;
    }

    public void free() {
        System.out.println("The " + getName() + " goose has been freed");
        isImprisoned = false;
        skipTurn = false;
    }

    @Override
    public String toString() {
        return printColor + Emoji.DUCK + Color.ANSI_RESET;
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