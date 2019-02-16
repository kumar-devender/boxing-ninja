package com.ninja.boxing;


import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ninja.boxing.app.module.config.AppConfiguration;
@RunWith(PowerMockRunner.class)
@PrepareForTest(AppConfiguration.class)
public class BoxingNinjaApplicationTests {
    @Test
    public void applicationStarts() {
        PowerMockito.mockStatic(AppConfiguration.class);
        //PowerMockito.mockStatic(BoxingNinjaApplication.class);
        BoxingNinjaApplication.main(new String[] {});
        //PowerMockito.verifyStatic(Mockito.times(1));
                //AppConfiguration.welcome();/*
        verify(BoxingNinjaApplication.class);
    }
}
