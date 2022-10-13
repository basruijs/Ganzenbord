package goose;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game{

    public static boolean keepGoing = true;
    Goose[] geese;
    Die d1 = new Die();
    Die d2 = new Die();
    Board board;

    int player;
    Goose goose;

    public static void main(String[] args) throws InterruptedException, IOException {
        Config config = new Config();
    }

    void startup() {
        board = new Board(Config.boardSize);

        System.out.println("Amount of players: ");
        Scanner s1 = new Scanner(System.in);
        geese = new Goose[s1.nextInt()];

        System.out.println("How many are bots?");
        Scanner s2 = new Scanner(System.in);
        int botAmount = s2.nextInt();

        for (int i = 0; i < geese.length; i++) {
            if (i < botAmount) {
                System.out.println(Emoji.ROBOT + " Bot " + (i + 1) + " pick a color: ");
                Scanner s3 = new Scanner(System.in);
                String name = s3.nextLine();
                if (Objects.equals(name, "")) {
                    name = String.valueOf((i + 1));
                }
                geese[i] = new Goose(name);
                geese[i].setBot(true);
            } else {
                System.out.println(Emoji.DUCK + " Goose " + (i + 1) + " pick a color: ");
                Scanner s3 = new Scanner(System.in);
                String name = s3.nextLine();
                if (Objects.equals(name, "")) {
                    name = String.valueOf((i + 1));
                }
                geese[i] = new Goose(name);
            }
        }

        for (Goose value : geese) {
            value.setPosition(0);
        }

        player = determinePlayer(geese);
        goose = geese[player];
        System.out.println(goose.getName() + " is the first goose");

        for (Goose value : geese) {
            System.out.print(value);
        }

        System.out.println();
    }

    private int determinePlayer(Goose[] geese) {
        int highestRoll = 0;
        int highestGoose = 0;
        boolean finished = true;
        ArrayList<Goose> geeses = new ArrayList<>();
        for (int i = 0; i < geese.length; i++) {
            System.out.println(geese[i].getName() + ", Press ENTER to roll: ");
            Scanner s = new Scanner(System.in);

            if (!geese[i].isBot()) {
                s.nextLine();
            }

            d1.roll();

            System.out.println("You rolled " + d1.getValue());

            if (highestRoll < d1.getValue()) {
                highestRoll = d1.getValue();
                highestGoose = i;
                finished = true;
            } else if (highestRoll == d1.getValue()) {
                finished = false;
                geeses.add(geese[i]);
            }
        }
        if (!finished) {
            Goose[] gooses = new Goose[geeses.size()];
            for (int j = 0; j < gooses.length; j++) {
                gooses[j] = geeses.get(j);
            }
            determinePlayer(gooses);
        }
        return highestGoose;
    }

    void playGame() throws InterruptedException {
        while (keepGoing) {
            if (!goose.isWon()) {
                int origin = goose.getPosition();

                Scanner s = new Scanner(System.in);
                System.out.println("Press ENTER to roll dice, honorable " + goose.printColor + goose.getName() +
                        Color.ANSI_RESET + " goose. Your current position is " + goose.getPosition());
                String input = "";

                if (goose.isBot()) {
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    input = s.nextLine();
                }

                int totalValue = rollDice();

                totalValue=cheat(input, totalValue);


                System.out.println("Goose " + goose.getName() + " departs from space " + goose.getPosition());
                //TODO move to GooseSquare
                if (goose.isFirstRoll() || (goose.position == 0 && Config.antiInstaWin)) {
                    goose.setFirstRoll(false);
                    if(totalValue == 9){
                        firstRollMove(board,goose,geese,origin);
                    } else {
                        goose.move(totalValue, board, geese, origin);
                    }
                } else {
                    goose.walk(totalValue, board, geese);
                }

                System.out.println("Goose " + goose.getName() + " is on " + goose.getPosition());
                board.printBoard(origin, geese, goose);
            }
            //TODO gans for loop maken
            nextPlayer();
        }
    }

    private int rollDice() {
        d1.roll();
        d2.roll();

        int totalValue = d1.getValue() + d2.getValue();

        System.out.println(Emoji.DICE + d1.getValue() + " + " + Emoji.DICE + d2.getValue() + " = " + totalValue);

        return totalValue;
    }

    private int cheat(String input, int totalValue) {
        try {
            if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < 64) {
                totalValue = Integer.parseInt(input);
                System.out.println("You cheated, position is now " + totalValue);
            }
        } catch (Exception ignored) {

        }
        return totalValue;
    }

    private void firstRollMove(Board board, Goose goose, Goose[] geese, int origin) {
        if ((d1.getValue() == 5 || d2.getValue() == 5) && (d1.getValue() == 4 || d2.getValue() == 4)) {
            goose.move(53, board, geese, origin);
            System.out.println("You jumped to 53");
        } else if ((d1.getValue() == 6 || d2.getValue() == 6) && (d1.getValue() == 3 || d2.getValue() == 3)) {
            goose.move(26, board, geese, origin);
            System.out.println("You jumped to 26");
        }
    }

    private void nextPlayer() {
        player++;
        if (player >= geese.length) {
            player = 0;
        }
        goose = geese[player];
    }
}
