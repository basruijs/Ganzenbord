package goose;

import java.util.Random;

public interface ColorPicker {
    static String makePrintColor(Goose goose, String color) {
        switch (color.toLowerCase()) {
            case "red":
            case "rood":
            case "1":
                goose.color = "red";
                return Color.ANSI_RED;
            case "green":
            case "groen":
            case "2":
                goose.color = "green";
                return Color.ANSI_GREEN;
            case "blue":
            case "blauw":
            case "3":
                goose.color = "blue";
                return Color.ANSI_BRIGHT_BLUE;
            case "yellow":
            case "geel":
            case "4":
                goose.color = "yellow";
                return Color.ANSI_YELLOW;
            case "purple":
            case "paars":
            case "5":
                goose.color = "purple";
                return Color.ANSI_PURPLE;
            case "white":
            case "wit":
            case "6":
                goose.color = "white";
                return Color.ANSI_WHITE;
            case "gray":
            case "grijs":
            case "grey":
            case "7":
                goose.color = "grey";
                return Color.ANSI_GRAY;
            case "lime":
            case "8":
                goose.color = "lime";
                return Color.ANSI_LIME;
            case "cyan":
            case "cyaan":
            case "9":
                goose.color = "cyan";
                return Color.ANSI_CYAN;
            case "pink":
            case "roze":
            case "10":
                goose.color = "pink";
                return Color.ANSI_PINK;
            case "black":
            case "zwart":
            case "11":
                goose.color = "black";
                return Color.ANSI_BLACK;
            case "brown":
            case "bruin":
            case "12":
                goose.color = "brown";
                return Color.ANSI_BROWN;
            case "teal":
            case "13":
                goose.color = "teal";
                return Color.ANSI_TEAL;

            case "light gray":
            case "light grey":
            case "licht grijs":
            case "14":
                goose.color = "light grey";
                return Color.ANSI_LIGHT_GRAY;
            case "dark blue":
            case "donkerblauw":
            case "15":
                goose.color = "dark blue";
                return Color.ANSI_BLUE;
            case "dark red":
            case "donkerrood":
            case "16":
                goose.color = "dark red";
                return Color.ANSI_DARK_RED;


            default:
                return randomizeColor();
        }
    }

    private static String randomizeColor() {
        int color;
        Random random = new Random();
        color = random.nextInt(1, 8);
        return switch (color) {
            case 1 -> Color.ANSI_BLACK;
            case 2 -> Color.ANSI_RED;
            case 3 -> Color.ANSI_GREEN;
            case 4 -> Color.ANSI_YELLOW;
            case 5 -> Color.ANSI_BLUE;
            case 6 -> Color.ANSI_PURPLE;
            case 7 -> Color.ANSI_CYAN;
            default -> Color.ANSI_WHITE;
        };
    }
}
