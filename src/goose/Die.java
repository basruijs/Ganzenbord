package goose;

import java.util.Random;

public class Die {

    int value;

    public void roll(){
        Random random = new Random();

        this.value = random.nextInt(1,6);
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
