package com.ninja.boxing.app.module.ui.component;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import org.junit.Ignore;
import org.junit.Test;

import com.ninja.boxing.app.module.helper.PrintStreamHelper;

public class TextInputTests extends PrintStreamHelper{
    
    @Test
    public void testDraw() {
        final String expected = "Enter name";
        TextInput input = new TextInput(expected);
        input.draw();
        String actual = outContent.toString();
        assertThat(actual , containsString("Enter name"));
    }

    //Running individually but not in group
    //TODO
    @Test
    @Ignore
    public void testGetValue() {
        ByteArrayInputStream in = new ByteArrayInputStream("devender".getBytes());
        System.setIn(in);
        final String expected = "Enter name";
        TextInput input = new TextInput(expected);
        assertThat(input.getValue(), containsString("devender"));
        System.setIn(System.in);
    }

}
