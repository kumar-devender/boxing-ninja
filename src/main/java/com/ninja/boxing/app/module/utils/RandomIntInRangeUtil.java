package com.ninja.boxing.app.module.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author DEVENDER
 * Utility to get any number between given range
 */
public final class RandomIntInRangeUtil {

    private RandomIntInRangeUtil() {

    }

    /**
     * @param min
     * @param max
     * @return
     * 
     * Return any number between a range.
     */
    public static int get(final int min, final int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
