package utils;

import java.util.Random;

public class GeneratorValue {

    public static int getRandomNumber(int start, int end) {
        return start + (int) (Math.random() * end);
    }
    public static String getRandomChar() {
        return String.valueOf((char) (new Random().nextInt(25) + 'a'));
    }
}
