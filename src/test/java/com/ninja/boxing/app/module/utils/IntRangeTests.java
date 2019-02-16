package com.ninja.boxing.app.module.utils;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IntRangeTests {

    @Test(expected=IllegalStateException.class)
    public void testOfShouldThrowExceptionWhenMingreaterThanMax() {
        IntRange.of(10, 5);
    }
    
    @Test
    public void testOfShouldReturnIntRangeInstanceWhenGivenMaxGreaterThanMin() {
        IntRange instance =  IntRange.of(2, 5);
        assertThat(instance,instanceOf(IntRange.class));
    }
    
    @Test
    public void testContainsShouldReturnTrueWhenGivenInRange() {
        IntRange instance =  IntRange.of(2, 5);
        assertThat(instance.contains(3), is(true));
    }
    
    @Test
    public void testContainsShouldReturnFalseWhenGivenGreaterThanMax() {
        IntRange instance =  IntRange.of(2, 5);
        assertThat(instance.contains(10), is(false));
    }
    
    @Test
    public void testContainsShouldReturnFalseWhenGivenLessThanMin() {
        IntRange instance =  IntRange.of(2, 5);
        assertThat(instance.contains(1), is(false));
    }

}
