package goose;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game implements Emoji{
    public static final String ANSI_RESET = "\u001B[0m";

    public static boolean keepGoing = true;
    Goose[] geese;
    Die d1 = new Die();
    Die d2 = new Die();
    Board board = new Board();
    int player;
    Goose goose;

    public static boolean antiInstaWin = false;
    public static boolean continueAfterWin = false;
    public static boolean shareSpaces = false;

    public static boolean lastPlayerFreedWell = false;
    public static boolean passToFreeWell = false;
    public static boolean lastPlayerFreedPrison = false;
    public static boolean passToFreePrison = false;

    public static void main(String[] args) throws InterruptedException, IOException {
        String configFilePath = "src/config.properties";
        FileInputStream propsInput = new FileInputStream(configFilePath);
        Properties prop = new Properties();
        prop.load(propsInput);

        antiInstaWin = Boolean.parseBoolean(prop.getProperty("antiInstaWin"));
        continueAfterWin = Boolean.parseBoolean(prop.getProperty("continueAfterWin"));
        shareSpaces = Boolean.parseBoolean(prop.getProperty("shareSpaces"));

        lastPlayerFreedWell = Boolean.parseBoolean(prop.getProperty("lastPlayerFreedWell"));
        passToFreeWell = Boolean.parseBoolean(prop.getProperty("passToFreeWell"));
        lastPlayerFreedPrison = Boolean.parseBoolean(prop.getProperty("lastPlayerFreedPrison"));
        passToFreePrison = Boolean.parseBoolean(prop.getProperty("passToFreePrison"));

        Game game = new Game();
        game.startup();
        game.playGame();
    }

    private void startup() {
        System.out.println("Amount of players: ");
        Scanner s1 = new Scanner(System.in);
        geese = new Goose[s1.nextInt()];
        System.out.println("How many are bots?");
        Scanner s2 = new Scanner(System.in);
        int botAmount = s2.nextInt();
        for (int i = 0; i < geese.length; i++) {
            if(i<botAmount){
                System.out.println("Bot " + (i + 1) + " pick a color: ");
                Scanner s3 = new Scanner(System.in);
                String name = s3.nextLine();
                if(Objects.equals(name, "")){
                    name= String.valueOf((i+1));
                }
                geese[i] = new Goose(name);
                geese[i].setBot(true);
            } else {
                System.out.println("Goose " + (i + 1) + " pick a color: ");
                Scanner s3 = new Scanner(System.in);
                String name = s3.nextLine();
                if(Objects.equals(name, "")){
                    name= String.valueOf((i+1));
                }
                geese[i] = new Goose(name);
            }

        }

        for (int i = 0; i < geese.length; i++) {

            geese[i].setPosition(0);
        }
        player = determinePlayer(geese);
        goose = geese[player];
        for (int i = 0; i < geese.length; i++) {
            System.out.print(geese[i]);
        }
        System.out.println();
    }

    private int determinePlayer(Goose[] geese) {
        int highestRoll = 0;
        int highestGoose = 0;
        boolean finished = true;
        ArrayList<Goose> geeses = new ArrayList<>();
        for (int i = 0; i < geese.length; i++) {
            System.out.println(geese[i].getColor() + ", Press ENTER to roll: ");
            Scanner s = new Scanner(System.in);
            if(geese[i].isBot()){

            } else {
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

        System.out.println(geese[highestGoose].getColor() + " is the first goose");

        return highestGoose;
    }

    private void playGame() throws InterruptedException {
        while (keepGoing) {
            if(!goose.isWon()) {
                int origin = goose.getPosition();
                Scanner s = new Scanner(System.in);
                System.out.println("Press ENTER to roll dice, honorable " + goose.printColor + goose.getColor() +
                        ANSI_RESET + " goose. Your current position is " + goose.getPosition());
                String input = "";
                if (goose.isBot()) {
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    input = s.nextLine();
                }
                d1.roll();
                d2.roll();

                int totalValue = d1.getValue() + d2.getValue();
                System.out.println(DICE + d1.getValue() + " + " + DICE + d2.getValue() + " = " + totalValue);
                try {
                    if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < 64) {
                        totalValue = Integer.parseInt(input);
                        System.out.println("You cheated, position is now " + totalValue);
                    }
                } catch (Exception e) {

                }


                System.out.println("Goose " + goose.getColor() + " departs from space " + goose.getPosition());
                if (goose.isFirstRoll() || (goose.position == 0 && antiInstaWin)) {
                    goose.setFirstRoll(false);
                    if ((d1.getValue() == 5 || d2.getValue() == 5) && (d1.getValue() == 4 || d2.getValue() == 4)) {
                        goose.setPosition(53);
                        System.out.println("You jumped to 53");
                    } else if ((d1.getValue() == 6 || d2.getValue() == 6) && (d1.getValue() == 3 || d2.getValue() == 3)) {
                        goose.setPosition(26);
                        System.out.println("You jumped to 26");

                    } else {
                        goose.move(totalValue, board, geese, origin);
                    }
                } else {
                    goose.walk(totalValue, board, geese);
                }

                System.out.println("Goose " + goose.getColor() + " is on " + goose.getPosition());
                printBoard(origin);
            }
            nextPlayer();
        }
    }

    private void printBoard(int origin) {

        String print = "";
        for (int i = 0; i < 64; i++) {
            print = String.valueOf(board.getSquare(i));
            if((i>origin && i<goose.getPosition()) || (i<origin && i>goose.getPosition())){
                print = goose.printColor + board.getSquare(i) + ANSI_RESET;
            }


            String printedGeese = "";
            for (int j = 0; j < geese.length; j++) {
                if (geese[j].getPosition() == i) {
                    printedGeese += String.valueOf(geese[j]);
                }
            }
            if(!printedGeese.equals("")) {
                print = printedGeese;
            }

            System.out.print(print);

            System.out.print("|");
            if (i == 31) {
                System.out.println();
                System.out.print("|");
            }
        }

        System.out.println();
        System.out.println();
    }

    private void nextPlayer() {
        player++;
        if (player >= geese.length) {
            player = 0;
        }
        goose = geese[player];
    }
}
