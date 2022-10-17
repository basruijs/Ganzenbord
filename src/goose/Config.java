package goose;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Config {
    public static boolean antiInstaWin = false;
    public static boolean continueAfterWin = false;
    public static boolean shareSpaces = false;

    public static boolean lastPlayerFreedWell = false;
    public static boolean passToFreeWell = false;
    public static boolean lastPlayerFreedPrison = false;
    public static boolean passToFreePrison = false;

    public static boolean murder = false;

    public static int boardSize = 64;

    public static final int FINISH_SQUARE = boardSize - 1;

    public Config() {
        Game game = new Game();
        String configFilePath = "src/config.properties";
        FileInputStream propsInput = null;
        try {
            propsInput = new FileInputStream(configFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(propsInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Config.antiInstaWin = Boolean.parseBoolean(prop.getProperty("antiInstaWin"));
        Config.continueAfterWin = Boolean.parseBoolean(prop.getProperty("continueAfterWin"));
        Config.shareSpaces = Boolean.parseBoolean(prop.getProperty("shareSpaces"));

        Config.lastPlayerFreedWell = Boolean.parseBoolean(prop.getProperty("lastPlayerFreedWell"));
        Config.passToFreeWell = Boolean.parseBoolean(prop.getProperty("passToFreeWell"));
        Config.lastPlayerFreedPrison = Boolean.parseBoolean(prop.getProperty("lastPlayerFreedPrison"));
        Config.passToFreePrison = Boolean.parseBoolean(prop.getProperty("passToFreePrison"));
        Config.murder = Boolean.parseBoolean(prop.getProperty("murder"));


        game.startup();
        try {
            game.playGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
