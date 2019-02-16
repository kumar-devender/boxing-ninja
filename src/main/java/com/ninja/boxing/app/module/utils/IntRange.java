package com.ninja.boxing.app.module.utils;

/**
 * @author DEVENDER
 * IntRange class check weather a number is between a range or not.
 */
public final class IntRange {

    /**
     * range Min number
     */
    private final int min;

    /**
     * range Max number 
     */
    private final int max;

    private IntRange(final int min, final int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * @param min
     * @param max
     * @return
     * 
     * return object of this class if min<max otherwise
     *  throw illegal argument exception
     */
    public static IntRange of(final int min, final int max) {
        if (min > max) {
            throw new IllegalStateException("Min value is greater than max. Min: " + min + ", Max: " + max);
        }
        return new IntRange(min, max);
    }

    /**
     * @param value
     * @return
     * verify if given number is in the range or not.
     */
    public boolean contains(final int value) {
        return value >= min && value <= max;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     *  String representation of this class.
     */
    @Override
    public String toString() {
        return "IntRange{min=" + min + ", max=" + max + '}';
    }
}
