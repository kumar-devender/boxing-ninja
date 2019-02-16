package com.ninja.boxing.app.module.reader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.function.Predicate;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ninja.boxing.app.module.utils.IntRange;

public class InputReaderTests {

    @Test
    public void testReadStringShouldReturnHello() {
        ByteArrayInputStream in = new ByteArrayInputStream("hello".getBytes());
        System.setIn(in);
        String actual = InputReader.getInstance().readString();
        System.setIn(System.in);
        assertThat(actual, is("hello"));
    }

    @Test
    public void testSingletonBehavior() {
        InputReader actual = InputReader.getInstance();
        assertThat(actual, is(InputReader.getInstance()));
    }
    
    // Running individually but not with all tests
    //TODO
    @Test
    @Ignore
    public void testReadIntegerUntil() {
        Runnable runnable = ()->{};
        IntRange acceptableItems = IntRange.of(1, 5);
        Predicate<String> predicate = line -> acceptableItems.contains(Integer.parseInt(line));
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);
        int actual = InputReader.getInstance().readIntegerUntil(predicate, runnable);
        System.setIn(System.in);
        assertThat(actual, is(3));
    }
    
}
