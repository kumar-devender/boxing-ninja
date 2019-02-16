package com.ninja.boxing.app.module.ui.component;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ninja.boxing.app.module.constant.CommonEnum.FightStatus;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Menu.class)
public class MenuTests {

    @Test
    public void testDrawShouldPrintItemsWhenItemsListSizeGreaterThanZero() {
      //Prepare to redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        Menu<FightStatus> menu = new Menu<>("Fight Status", FightStatus.values());
        menu.draw();
        assertThat(os.toString(),startsWith("Fight Status"));
        assertThat(os.toString(), containsString(FightStatus.COMPLETED.toString()));
      //Restore normal output
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    
    @Test
    public void testchooseItemShouldReturnFirstItem() throws Exception {
        Menu<FightStatus> menu = PowerMockito.spy(new Menu<>("Fight Status", FightStatus.values()));
        PowerMockito.doReturn(0).when(menu,"readItemIndex");
        FightStatus fightStatus = menu.chooseItem();
        assertThat(fightStatus, is(FightStatus.IN_PROGRESS));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testDrawShouldThrowExceptionWhenItemsListSizeLessThanOne() {
        new Menu<>("Test Enum", TestEnum.values());
    }
    
    
    public enum TestEnum{
        
    }

}
