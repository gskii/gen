package ru.nntu.vst.gorbatovskii.genalg.utils;

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
}
