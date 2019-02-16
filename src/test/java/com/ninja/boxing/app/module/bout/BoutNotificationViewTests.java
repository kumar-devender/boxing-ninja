package com.ninja.boxing.app.module.bout;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ninja.boxing.app.module.helper.BuilderHelper;
import com.ninja.boxing.app.module.model.Playable;

public class BoutNotificationViewTests {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    
    @Test
    public void testShowWinnerNotification() {
        Playable player = BuilderHelper.createUserPlayer();
        BoutNotificationView notificationView = new BoutNotificationView();
        notificationView.showWinnerNotification(player);
        assertThat(outContent.toString(),startsWith(player.getName()));
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testDrawShouldThrowUnsupportedOperationException() {
        BoutNotificationView notificationView = new BoutNotificationView();
        notificationView.draw();
    }
    
    @Test
    public void testShowPausedNotification() {
        BoutNotificationView notificationView = new BoutNotificationView();
        notificationView.showPausedNotification();
        assertThat(outContent.toString()  ,startsWith("This fight has been paused for now."));
    }
    
}
