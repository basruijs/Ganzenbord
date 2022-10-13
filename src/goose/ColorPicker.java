package goose;

import java.util.Random;

public interface ColorPicker extends Color {
    static String makePrintColor(Goose goose, String color) {
    switch (color.toLowerCase()){
        case "red":
        case "rood":
        case "1":
            goose.color="red";
            return ANSI_RED;
        case "green":
        case "groen":
        case "2":
            goose.color="green";
            return ANSI_GREEN;
        case "blue":
        case "blauw":
        case "3":
            goose.color="blue";
            return ANSI_BRIGHT_BLUE;
        case "yellow":
        case "geel":
        case "4":
            goose.color="yellow";
            return ANSI_YELLOW;
        case "purple":
        case "paars":
        case "5":
            goose.color="purple";
            return ANSI_PURPLE;
        case "pink":
        case "roze":
        case "6":
            goose.color="pink";
            return ANSI_PINK;
        case "gray":
        case "grijs":
        case "grey":
        case "7":
            goose.color="grey";
            return ANSI_GRAY;
        case "lime":
        case "8":
            goose.color="lime";
            return ANSI_LIME;
        case "cyan":
        case "cyaan":
        case "9":
            goose.color="cyan";
            return ANSI_CYAN;
        case "white":
        case "wit":
        case "10":
            goose.color="white";
            return ANSI_WHITE;
        case "black":
        case "zwart":
        case "11":
            goose.color="black";
            return ANSI_BLACK;
        case "brown":
        case "bruin":
        case "12":
            goose.color="brown";
            return ANSI_BROWN;
        case "light blue":
        case "lichtblauw":
        case "13":
            goose.color="light blue";
            return ANSI_LIGHT_BLUE;
        case "light gray":
        case "light grey":
        case "licht grijs":
        case "14":
            goose.color="light grey";
            return ANSI_LIGHT_GRAY;

        case "dark blue":
        case "donkerblauw":
        case "15":
            goose.color="dark blue";
            return ANSI_BLUE;
        case "bright red":
        case "fel rood":
        case "16":
            goose.color="bright red";
            return ANSI_BRIGHT_RED;

        default:
            return randomizeColor();
    }
}

    private static String randomizeColor(){
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
}
