package com.ninja.boxing.app.module.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RandomIntInRangeUtilTests {

    @Test
    public void test() {
       int number = RandomIntInRangeUtil.get(5, 10);
       assertTrue(number>=5);
       assertTrue(number<=10);
    }

}
