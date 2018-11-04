package ru.nntu.vst.gorbatovskii.genetics.utils;

import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM_INSTANCE = new Random();

    public static int getPositiveRandom(int top) {
        return getPositiveRandom(0, top);
    }

    public static int getPositiveRandom(int bottom, int top) {
        return Math.abs(RANDOM_INSTANCE.nextInt() % top) + bottom;
    }

    public static boolean getBoolean(double rate) {
        return RANDOM_INSTANCE.nextDouble() < rate;
    }

    public static <T> T[] shuffleArray(T[] ar) {
        for (int i = ar.length - 1; i > 0; i--) {
            int index = RANDOM_INSTANCE.nextInt(i + 1);
            // Simple swap
            T a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
}
