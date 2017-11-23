package com.pesegato.gibbondice;

import java.util.Random;

public class RNG {

    private static final Random rng = new Random();

    public static int nextInt(int i) {
        return rng.nextInt(i);
    }

    public static boolean nextBool() {
        return rng.nextBoolean();
    }

    public static float getRandomFloat() {
        return rng.nextFloat();
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = rng.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static Class randomClass(Class[] classes) {
        int x = rng.nextInt(classes.length);
        return classes[x];
    }
}
