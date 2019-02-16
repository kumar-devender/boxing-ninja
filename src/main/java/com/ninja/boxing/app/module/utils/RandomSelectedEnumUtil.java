package com.ninja.boxing.app.module.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author DEVENDER
 * Utility to get any random value from set of enum values.
 */
public final class RandomSelectedEnumUtil{
    private RandomSelectedEnumUtil(){
    
    }
    /**
     * @param options
     * @return
     * Return any random enum from the set
     */
    public static <T extends Enum<T>> T  menu(final Set<T> options) {
        final List<T> list = new ArrayList<>(options);
        return list.get(RandomIntInRangeUtil.get(0, list.size()));
    }
}
